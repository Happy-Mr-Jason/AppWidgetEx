package com.example.torch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch swFlash;
    FlashLight flashLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swFlash = (Switch)findViewById(R.id.swFlash);

        swFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Intent For Service, Using on Service Thread
                Intent intent = new Intent(MainActivity.this, TorchService.class);
                if(isChecked){
                    intent.setAction("on");
                } else {
                    intent.setAction("off");
                }
                // Call the service
                startService(intent);

// Using on Activity
//                try {
//                    flashLight = new FlashLight(MainActivity.this);
//                    if(isChecked){
//                        flashLight.flashOn();
//                    } else {
//                        flashLight.flashOff();
//                    }
//                } catch (CameraAccessException e) {
//                    Toast.makeText(getApplicationContext(),"사용할 카메라가 없습니다. 헐",Toast.LENGTH_SHORT ).show();
//                }
            }
        });
    }
}
