package com.example.sensoractivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class gyroscope extends AppCompatActivity implements SensorEventListener {

    private TextView xGyro,yGyro,zGyro;
    private Sensor mySensor;
    private SensorManager SM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        xGyro=(TextView)findViewById(R.id.txt1);
        yGyro=(TextView)findViewById(R.id.txt2);
        zGyro=(TextView)findViewById(R.id.txt3);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        xGyro.setText("X: " + (int)x);
        yGyro.setText("Y: " + (int)y);
        zGyro.setText("Z: " + (int)z);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }
}
