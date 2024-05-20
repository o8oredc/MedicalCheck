package com.example.medicalcheck.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalcheck.ProfileFragment;
import com.example.medicalcheck.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Option extends AppCompatActivity {
    private Button StepCounter,Workout,Details,BmiActivity,LogOut, profile;
    ConstraintLayout linearmain;
    private FirebaseAuth mAuth;
    private Button Verify;
    private TextView VerifyMessage;
    private String userID;
    String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);

        StepCounter=findViewById(R.id.StepCounterO);
        Workout=findViewById(R.id.WorkoutO);
        profile=findViewById(R.id.ProfileFragment);
        linearmain = findViewById(R.id.linearmain); linearmain.setVisibility(View.VISIBLE);
        /*Details=findViewById(R.id.Details);*/
        BmiActivity=findViewById(R.id.BmiActivity);
        LogOut=findViewById(R.id.Logout);
        Verify=findViewById(R.id.verify);
        VerifyMessage=findViewById(R.id.EmailVerf);
        mAuth = FirebaseAuth.getInstance();
        userID=mAuth.getCurrentUser().getUid();
        final FirebaseUser user=mAuth.getCurrentUser();
        if(!user.isEmailVerified())
        {
            Verify.setVisibility(View.VISIBLE);
            VerifyMessage.setVisibility(View.VISIBLE);

            Verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Option.this,"Vertification Email Has been Sent",Toast.LENGTH_SHORT ).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("tag", "onFailure: Email not sent"+e.getMessage() );


                        }
                    });

                }
            });

        }

        StepCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Option.this, StepCounter.class);
                startActivity(intent);

            }
        });

        Workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Option.this, RecyclerView.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                from = "profile";
                linearmain.setVisibility(View.GONE);

                FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameLayoutMain, new ProfileFragment());
                ft.commit();
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();

            }
        });
        BmiActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Option.this, BmiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(from.equals("profile")) {
            Intent intent = new Intent(Option.this, Option.class);
            startActivity(intent);
        }else super.onBackPressed();

    }
}






