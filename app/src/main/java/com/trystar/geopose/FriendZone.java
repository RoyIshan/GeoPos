package com.trystar.geopose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import static android.widget.Toast.LENGTH_LONG;

public class FriendZone extends AppCompatActivity {


    CountryCodePicker ccp;
    EditText eNumber;
    TextView dNumber;
    Button send,search;
    String latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_zone);


        eNumber = findViewById(R.id.frPhonenumber);
        dNumber = findViewById(R.id.NumberDisplay);
        search = findViewById(R.id.search);
        send = findViewById(R.id.send);

        ccp=(CountryCodePicker)findViewById(R.id.cpp);
        ccp.registerCarrierNumberEditText(eNumber);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    String sNum = ccp.getFullNumberWithPlus().replace(" ","");
                    //String sNum = dNumber.toString().trim();

                DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child(sNum);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            latitude = snapshot.child("latitude").getValue().toString();
                            longitude = snapshot.child("longitude").getValue().toString();

                            dNumber.setText(sNum);
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(FriendZone.this,e.getMessage(), LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FriendZone.this,"Data not Found", LENGTH_LONG).show();
                    }
                });}
                catch (Exception e)
                {
                    Toast.makeText(FriendZone.this,e.getMessage(), LENGTH_LONG).show();
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(FriendZone.this, ShowFriendLocation.class);
                    intent.putExtra("Lat", latitude);
                    intent.putExtra("Lon", longitude);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(FriendZone.this,e.getMessage(), LENGTH_LONG).show();
                }
            }
        });
    }
}