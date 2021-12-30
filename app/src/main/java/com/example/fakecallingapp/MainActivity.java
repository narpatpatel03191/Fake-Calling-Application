package com.example.fakecallingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.name);
        number=findViewById(R.id.number);
        CardView call=findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             validateandcall();
            }
        });

        findViewById(R.id.call_log_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CallLogActivity.class));


            }

        });
        findViewById(R.id.contacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ContactActivity.class));
            }
        });
    }

    private void validateandcall() {
        String phoneregex="\"^[6789]\\\\d{9}$\"";
        if(!TextUtils.isEmpty(number.getText().toString())||number.getText().toString().trim().matches(phoneregex)) {

            Intent intent=new Intent(getApplicationContext(),CallActivity.class);
            if(name.getText().toString().isEmpty())
                intent.putExtra("name",number.getText().toString());
            else{
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("number",number.getText().toString());
            }
            startActivity(intent);
        }
        else{
            number.setError("Please enter a valid phone number!!");
            number.requestFocus();
        }

    }
}