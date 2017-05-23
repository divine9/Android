package com.fixpocket.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.fixpocket.R;
import com.github.florent37.viewanimator.ViewAnimator;

//Splash Screen change by hardik..
public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 4000;
    ImageView splashLogo;

    private final int SPLASH_DISPLAY_LENGHT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        splashLogo = (ImageView) findViewById(R.id.logoSpalsh);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LandingPages.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}

