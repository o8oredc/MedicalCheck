package com.example.medicalcheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signup extends AppCompatActivity {
    private EditText etEmail, etPassword, etUsername;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseDatabase database;
    private Button btnS;

    private TextView mCreateSignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etEmail =(EditText) findViewById(R.id.etEmailSignup);
        etPassword = (EditText)findViewById(R.id.etPasswordSignup);
        etUsername = (EditText)findViewById(R.id.etUsername);
        btnS=(Button)findViewById(R.id.btnsignup);
        mCreateSignup=(TextView)findViewById(R.id.gotologin) ;




        mAuth = FirebaseAuth.getInstance();
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email=etEmail.getText().toString();
               String password=etPassword.getText().toString();
               String username = etUsername.getText().toString();


               if(TextUtils.isEmpty(email)){
                   etEmail.setError("Email is Required");
                   return;
               }
                if(TextUtils.isEmpty(password)){
                    etPassword.setError("Password is Required");
                    return;
                }
                if(password.length()<6){
                    etPassword.setError("password Must be >= 6 Characters");
                    return;
                }
                if(username.trim().isEmpty()){
                    etUsername.setError("Username is Required");
                    return;
                }



                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user=mAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Signup.this,"Vertification Email Has been Sent",Toast.LENGTH_SHORT ).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Signup.this,"onFailure:Email not sent",Toast.LENGTH_SHORT ).show();

                                }
                            });


                            CreateUserinDataBase(username,email);

                        }else{
                            Toast.makeText(Signup.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
        mCreateSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
    });
    }

    private void CreateUserinDataBase(String email,String username) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        UserProfile user = new UserProfile(username,"");

        db.collection("Users").document(email).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Option.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }


}

