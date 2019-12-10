package com.example.torch;

import android.app.Service;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.IBinder;

public class TorchService extends Service {

    FlashLight flashLight;
    Boolean isRunning = false;

    public TorchService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            flashLight = new FlashLight(this);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //When Called by .startService(), onStartCommand will be Start
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //When Using this Function on App
        if(intent.getAction() == "on"){
            try {
                flashLight.flashOn();
                isRunning = true;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else if (intent.getAction() =="off"){
            try {
                flashLight.flashOff();
                isRunning = false;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            //When Using this Function on Service
            isRunning = !isRunning;
            if(isRunning){
                try {
                    flashLight.flashOn();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else{
                try {
                    flashLight.flashOff();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}
