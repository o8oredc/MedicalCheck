package com.example.medicalcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BmiActivity extends AppCompatActivity {
         Button btn;
         EditText heightb,weightB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        btn=findViewById(R.id.calcbmi);
        heightb=findViewById(R.id.heightb);
        weightB=findViewById(R.id.weightb);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heightbm = heightb.getText().toString();
                String weightbm = weightB.getText().toString();




                if (TextUtils.isEmpty(heightbm) ||  !TextUtils.isDigitsOnly(heightbm))
                    heightb.setError("Please put a real number!");
               else if (TextUtils.isEmpty(weightbm)  || !TextUtils.isDigitsOnly(weightbm))
                    weightB.setError("Please put a real number!");
               else {
                   double w=Double.parseDouble(weightbm);
                   double h=Double.parseDouble(heightbm)/100;
                    double bmi = w /(h*h);
                    double roundoff = Math.round(bmi * 100.0) / 100.0;
                    if (bmi >= 18.5 && bmi < 25) {
                       Toast.makeText(BmiActivity.this,"Your Bmi is "+roundoff+"You Are Healthy! Keep It up!",Toast.LENGTH_SHORT).show();

                    }
                    if (bmi >= 25 && bmi < 30) {
                        Toast.makeText(BmiActivity.this,"Your Bmi is "+roundoff+"You Are OverWeight! Do A Diet!",Toast.LENGTH_SHORT).show();

                    }
                    if (bmi > 30) {
                        Toast.makeText(BmiActivity.this,"Your Bmi is "+roundoff+"You Are Obese! Check A Doctor!",Toast.LENGTH_SHORT).show();


                    }
                    if (bmi < 18.5) {
                        Toast.makeText(BmiActivity.this,"Your Bmi is "+roundoff+ "You Are UnderWeight! Do A Diet!",Toast.LENGTH_SHORT).show();


                    }
                    if (bmi < 16) {
                        Toast.makeText(BmiActivity.this,"Your Bmi is "+roundoff+"You Are Severly UnderWeight ! Check A Doctor!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }

}
