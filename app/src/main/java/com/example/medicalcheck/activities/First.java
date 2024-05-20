package com.example.medicalcheck.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalcheck.R;


public class First extends AppCompatActivity{
private Button SignupS,LoginS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        SignupS=findViewById(R.id.SignUpS);
        LoginS=findViewById(R.id.LogInS);
        SignupS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(First.this, Signup.class);
                startActivity(intent);

            }
        });

        LoginS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(First.this, Login.class);
                startActivity(intent);
            }
        });
    }





}
