package com.example.fakecallingapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    android.os.Handler handler = new Handler();
    ;
    ImageView iVTop, iVBottom, iVLogo;
    TextView splashText;
    CharSequence charSequence;
    int index;
    long delay = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_new);
        splashText=(TextView)findViewById(R.id.splash_text);
        animateText("Fake Caller");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            splashText.setText(charSequence.subSequence(0, index++));

            if (index <= charSequence.length()) {
                handler.postDelayed(runnable, delay);
            }
        }
    };

    public void animateText(CharSequence cs) {
        charSequence = cs;
        index = 0;
        splashText.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delay);
    }

}