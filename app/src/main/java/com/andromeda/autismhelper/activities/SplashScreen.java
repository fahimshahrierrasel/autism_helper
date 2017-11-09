package com.andromeda.autismhelper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.andromeda.autismhelper.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView textViewAppName = findViewById(R.id.textView_app_name);
        TextView textViewAppMessage = findViewById(R.id.textView_app_message);
        ImageView imageViewLogo = findViewById(R.id.imageView_logo);

        Animation loadAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha_transation);
        textViewAppName.startAnimation(loadAnimation);
        textViewAppMessage.startAnimation(loadAnimation);
        imageViewLogo.startAnimation(loadAnimation);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1500);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();


    }
}
