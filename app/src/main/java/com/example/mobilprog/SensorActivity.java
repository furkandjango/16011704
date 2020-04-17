package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    TextView tv1=null;
    long nowT, oldT;
    float x, x0, y, y0, z, z0;
    private SensorManager mSensorManager;
    private Sensor lights,accs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        tv1 = (TextView) findViewById(R.id.sensbtn);
       // Toast.makeText(getApplicationContext(), "Sensör bilgileri alınıyor...", Toast.LENGTH_LONG).show();
        tv1.setVisibility(View.GONE);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_LIGHT);
          List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 0; i < mList.size(); i++) {
            tv1.setVisibility(View.VISIBLE);
            tv1.append("\n" + mList.get(i).getName() +  "\n" + mList.get(i).getType());
            //  if((mList.get(i).getType())
            //    tv1.append("\n" + mList.get(i).getName() +  "\n" + mList.get(i).getVersion());
        }
        lights=mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accs=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            float f = event.values[0];
            if(f < 7000){
                //Toast.makeText(getApplicationContext(), "5000 den küçük", Toast.LENGTH_LONG).show();
                tv1.setBackgroundColor(Color.BLACK);
                tv1.setTextColor(Color.WHITE);
            }else{
               // Toast.makeText(getApplicationContext(), "Büyükkkkk...", Toast.LENGTH_LONG).show();
                tv1.setBackgroundColor(Color.WHITE);
                tv1.setTextColor(Color.BLACK);
            }
        }
        else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            if( x0 != x || y0 != y || z0 != z ){
                nowT = System.currentTimeMillis();
                x0=x;
                y0=y;
                z0=z;
            }else {
                oldT = System.currentTimeMillis() - nowT;
                if (oldT > 5000){
                    Toast.makeText(getApplicationContext(), "5 Saniye Doldu...", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, lights, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, accs, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
