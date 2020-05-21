package com.example.firebaseconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.model.WeatherInfo;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private  DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        addControls();
        addEvents();
    }

    private void addControls() {
        mDatabase= FirebaseDatabase.getInstance().getReference();



    }

    private void addEvents() {
        writeNewUser("1123","tuan","tuankhongngu@");
        myRef=mDatabase.child("user/1123");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WeatherInfo value =dataSnapshot.getValue(WeatherInfo.class);
                Toast.makeText(MapActivity.this,value.toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void writeNewUser(String userID, String name,String email) {
        WeatherInfo weatherInfo=new WeatherInfo(name,email);
        mDatabase.child("user").child(userID).setValue(weatherInfo);

    }
}
