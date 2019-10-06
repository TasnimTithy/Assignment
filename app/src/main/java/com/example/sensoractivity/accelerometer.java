package com.example.sensoractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class accelerometer extends AppCompatActivity implements SensorEventListener {

    public TextView txt1,txt2,txt3;
    private SensorManager sm;
    private Sensor mySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);

        sm =(SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor= sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int x=(int) (sensorEvent.values[0]);
        int y=(int) (sensorEvent.values[1]);
        int z=(int) (sensorEvent.values[2]);

        txt1.setText("X is" +x);
        txt2.setText("Y is" +y);
        txt3.setText("Z is" +z);
        if(y>=9) {
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        }
        {
            getWindow().getDecorView().setBackgroundColor(Color.RED);
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
