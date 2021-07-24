package com.trystar.geopose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SosSelecter extends AppCompatActivity {

    String Number1,Number2,Number3;
    EditText Number01,Number02,Number03;
    Button SaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_selecter);

        Number01 = findViewById(R.id.firstNumber);
        Number02 = findViewById(R.id.secondNumber);
        Number03 = findViewById(R.id.thirdNumber);

        SaveBtn = findViewById(R.id.save_btn);

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Number1 = "+91"+Number01.getText().toString();
                Number2 = "+91"+Number02.getText().toString();
                Number3 = "+91"+Number03.getText().toString();


                Intent intent = new Intent(SosSelecter.this,MainActivity.class);
                intent.putExtra("Number1",Number1);
                intent.putExtra("Number2",Number2);
                intent.putExtra("Number3",Number3);
                startActivity(intent);
            }
        });

    }
}