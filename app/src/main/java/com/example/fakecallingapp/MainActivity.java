package com.example.fakecallingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name= findViewById(R.id.name);
        EditText number=findViewById(R.id.number);
        CardView call=findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CallActivity.class);
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("number",number.getText().toString());
                startActivity(intent);
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
}