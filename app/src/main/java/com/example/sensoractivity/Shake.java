package com.example.sensoractivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.seismic.ShakeDetector;

public class Shake extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;
    private Sensor mySensor;
    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);


        SensorManager SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        ShakeDetector SD=new ShakeDetector((ShakeDetector.Listener) this);

        SD.start(SM);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        makePhoneCall();

    }

    private void makePhoneCall() {

        String number = "123344";
        if(ContextCompat.checkSelfPermission(Shake.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Shake.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }
        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else
            {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
