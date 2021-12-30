package com.example.fakecallingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.format.Time;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CallActivity extends AppCompatActivity {

    MediaPlayer mediaPlayerRingtone;
    MediaPlayer mediaPlayerTalk;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        mediaPlayerRingtone =MediaPlayer.create(getApplicationContext(),R.raw.ring);
        mediaPlayerRingtone.setLooping(true);
        mediaPlayerRingtone.start();
        saveToDatabase(intent.getStringExtra("name"),intent.getStringExtra("number"));

        enableVibration();


        callAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAccept.setVisibility(View.GONE);
                callRejectIncoming.setVisibility(View.GONE);
                callReject.setVisibility(View.VISIBLE);

                Chronometer chronometer=findViewById(R.id.chronometer);
                chronometer.setVisibility(View.VISIBLE);
                chronometer.start();
                mediaPlayerRingtone.stop();
                mediaPlayerRingtone.release();
                vibrator.cancel();
                findViewById(R.id.call_elements_upper).setVisibility(View.VISIBLE);
                findViewById(R.id.call_elements_lower).setVisibility(View.VISIBLE);

                mediaPlayerTalk=MediaPlayer.create(getApplicationContext(),R.raw.call_talk);
                mediaPlayerTalk.setLooping(true);
                mediaPlayerTalk.start();
            }
        });

        callRejectIncoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayerRingtone.stop();
                mediaPlayerRingtone.release();
                vibrator.cancel();
            }
        });

        callReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayerTalk.stop();
                mediaPlayerTalk.release();
            }
        });
    }
    private void enableVibration() {
        long[] VIBRATE_PATTERN = {1000, 1000};
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // API 26 and above
            vibrator.vibrate(VibrationEffect.createWaveform(VIBRATE_PATTERN, 0));
        } else {
            // Below API 26
            vibrator.vibrate(VIBRATE_PATTERN, 0);
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(mediaPlayerRingtone.isPlaying()) {
            mediaPlayerRingtone.stop();
            mediaPlayerRingtone.release();
        }
        if(mediaPlayerTalk.isPlaying()){
            mediaPlayerTalk.stop();
            mediaPlayerTalk.release();
        }
        if(vibrator.hasVibrator())
             vibrator.cancel();
    }
}