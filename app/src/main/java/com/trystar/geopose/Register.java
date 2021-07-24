package com.trystar.geopose;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class Register extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText number,name;
    Button btn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        number=(EditText)findViewById(R.id.number);
        name = findViewById(R.id.name);
        ccp=(CountryCodePicker)findViewById(R.id.cpp);
        btn = (Button) findViewById(R.id.btn);

        ccp.registerCarrierNumberEditText(number);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Intent intent = new Intent(Register.this,Otp.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);





            }
        });
    }
}