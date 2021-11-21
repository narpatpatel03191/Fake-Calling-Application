package com.example.fakecallingapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    android.os.Handler handler = new Handler();
    ;
    ImageView iVTop, iVBottom, iVLogo;
    TextView textView;
    CharSequence charSequence;
    int index;
    long delay = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        iVTop = findViewById(R.id.iv_top);
        iVBottom = findViewById(R.id.iv_bottom);
        iVLogo = findViewById(R.id.logo);
        textView = findViewById(R.id.text_view);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.top_wave);

        iVTop.startAnimation(animation);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(iVLogo,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));

        objectAnimator.setDuration(1500);

        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        objectAnimator.start();

        animatText("Fake Calling...");


        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.bottom_wave);

        iVBottom.setAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                intent = new Intent(SplashScreen.this, CallActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            textView.setText(charSequence.subSequence(0, index++));

            if (index <= charSequence.length()) {
                handler.postDelayed(runnable, delay);
            }
        }
    };

    public void animatText(CharSequence cs) {
        charSequence = cs;
        index = 0;
        textView.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delay);
    }

}