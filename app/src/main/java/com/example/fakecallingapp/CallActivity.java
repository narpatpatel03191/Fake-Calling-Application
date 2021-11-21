package com.example.fakecallingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Intent intent=getIntent();
        TextView name=findViewById(R.id.caller_name);
        name.setText(intent.getStringExtra("name"));
        TextView number=findViewById(R.id.caller_number);
        number.setText(intent.getStringExtra("number"));
        ImageView callAccept=findViewById(R.id.call_accept);
        ImageView callRejectIncoming=findViewById(R.id.call_reject_incoming);
        ImageView callReject=findViewById(R.id.call_reject);
        MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.ring);
        mediaPlayer.start();
        saveToDatabase(intent.getStringExtra("name"),intent.getStringExtra("number"));


        callAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAccept.setVisibility(View.GONE);
                callRejectIncoming.setVisibility(View.GONE);
                callReject.setVisibility(View.VISIBLE);

                Chronometer chronometer=findViewById(R.id.chronometer);
                chronometer.setVisibility(View.VISIBLE);
                chronometer.start();
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });

        callRejectIncoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });

        callReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveToDatabase(String name,String number) {
        CallLog callLog;
        Time t = new Time(Time.getCurrentTimezone());
        t.setToNow();
        String date1 = t.format("%Y/%m/%d");

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        String time = dateFormat.format(date);
//        String horafecha = time+ " - " + date1;
//        Toast.makeText(getApplicationContext(),horafecha,Toast.LENGTH_LONG).show();
          callLog=new CallLog(name,number,time,date1);
          CallLogDatabase callLogDatabase=new CallLogDatabase(getApplicationContext());
          callLogDatabase.addToLogs(callLog);
    }

}