package com.ecksday.punch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView thumbs = (ImageView) findViewById(R.id.imageView1);
        final ImageView punch = (ImageView) findViewById(R.id.imageView2);
        final Button ClickHere = (Button) findViewById(R.id.button);

        final Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);
        thumbs.setAnimation(fadeOut);
        punch.setAnimation(fadeOut);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                thumbs.setVisibility(View.GONE);
                punch.setVisibility(View.GONE);
                ClickHere.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ClickHere.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                                    SecureRandom r = new SecureRandom();
                                                    int i = r.nextInt(2);
                                                    ClickHere.setVisibility(View.GONE);
                                                    if(i==0) {
                                                        thumbs.setVisibility(View.VISIBLE);
                                                        thumbs.startAnimation(fadeOut);
                                                    } else {
                                                        punch.setVisibility(View.VISIBLE);
                                                        punch.startAnimation(fadeOut);
                                                    }
                                         }
                                     }
        );
    }
}