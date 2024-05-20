package com.example.medicalcheck;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity {
    androidx.recyclerview.widget.RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    ArrayList<Workout> list;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerView.this));
        list = new ArrayList<>();



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // data retrieval from firestore database;

        db.collection("workouts").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot dataSnapshot: queryDocumentSnapshots.getDocuments()){
                    Workout workout = dataSnapshot.toObject(Workout.class);
                    list.add(workout);
                }

                myAdapter = new MyAdapter(RecyclerView.this, list);
                mRecyclerView.setAdapter(myAdapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}


