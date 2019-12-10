package com.example.torch;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;

//Using CameraManager Class, Control the Flash Function
public class FlashLight {
    Context context;
    String cameraID;
    CameraManager cameraManager;

    public FlashLight(Context context) throws CameraAccessException {
        this.context = context;
        //getSystemService() Device Components Service
        cameraManager = (CameraManager)context.getSystemService(context.CAMERA_SERVICE);
        cameraID = getCameraID();
    }

    public String getCameraID() throws CameraAccessException{
        String cameraIDs[] = cameraManager.getCameraIdList();
        for (String id : cameraIDs) {
            //Get the Camera Characteristics of each cameraID
            CameraCharacteristics info = cameraManager.getCameraCharacteristics(id);
            //Get the Camera Character of available for Using Flash.
            Boolean flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            //Direction of Camera to Facing
            Integer lensFacing = info.get(CameraCharacteristics.LENS_FACING);

            if(flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id;
            }
        }
        return null;
    }

    public void flashOn() throws CameraAccessException{
        cameraManager.setTorchMode(cameraID, true);
    }

    public  void flashOff() throws CameraAccessException{
        cameraManager.setTorchMode(cameraID, false);
    }
}
