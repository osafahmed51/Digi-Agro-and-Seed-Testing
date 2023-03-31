package com.example.digiagro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    Animation topanim,bottomanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.botum_anim);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        imageView.setAnimation(topanim);
        textView.setAnimation(bottomanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}