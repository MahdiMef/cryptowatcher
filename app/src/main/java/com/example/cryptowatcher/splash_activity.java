package com.example.cryptowatcher;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class splash_activity extends AppCompatActivity {
    public int SPLASH_SCREEN_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash_activity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);


        TextView txt_title = findViewById(R.id.txt_title);
        TextView txt_info = findViewById(R.id.txt_info);
        Button btn_skip = findViewById(R.id.btn_skip);

        Typeface title = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/ARLRDBD.TTF");
        Typeface info = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/IRANSansMobile_Light.ttf");
        txt_info.setTypeface(info);
        txt_title.setTypeface(title);

    btn_skip.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(splash_activity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    });

    }
}
