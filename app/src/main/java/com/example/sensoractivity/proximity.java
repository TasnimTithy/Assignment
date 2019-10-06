package com.example.sensoractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class proximity extends AppCompatActivity implements SensorEventListener {

    public TextView txt1,txt2,txt3;
    private SensorManager sm;
    private Sensor mySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.values[0]<mySensor.getMaximumRange()) {
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            Vibrator v =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
        }
        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        sm.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause()
    {
        super.onPause();
        sm.unregisterListener(this);
    }
}
