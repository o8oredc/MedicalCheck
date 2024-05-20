package com.example.medicalcheck.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.medicalcheck.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StepCounter extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private TextView textViewStepCounter,TextViewStepDetector;
    private Sensor mstepcounter;
    private boolean iscountersensor ;
    private int stepsAtReset;
    private Button btnreset;

    boolean init_sensor = true;
    int stepCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepcounter);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // Check if the ACTIVITY_RECOGNITION permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            // Ask for permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 666);
        }
        textViewStepCounter = (TextView)findViewById(R.id.tv_steps);
        btnreset=(Button)findViewById(R.id.resetsteps);
        TextViewStepDetector = (TextView)findViewById(R.id.tv_info);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            mstepcounter=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            iscountersensor=true;

        }else{
            textViewStepCounter.setText("counter Sensor   is not Present");
            iscountersensor=false;

        }
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepsAtReset = stepCount;
                textViewStepCounter.setText(String.valueOf(0));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.registerListener(this, mstepcounter,SensorManager.SENSOR_DELAY_NORMAL);

        }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this,mstepcounter);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (init_sensor == true ){
            stepsAtReset = (int) sensorEvent.values[0];
            init_sensor = false;
        }
   if(sensorEvent.sensor == mstepcounter){
       //textViewStepCounter.setText(String.valueOf(0));
       stepCount= (int) sensorEvent.values[0];
       int stepsSinceReset = stepCount - stepsAtReset;
       textViewStepCounter.setText(String.valueOf(stepsSinceReset));

   }else{
       sensorEvent.values[0] = 0;
   }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
