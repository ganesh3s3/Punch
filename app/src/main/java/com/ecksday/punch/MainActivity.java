package com.ecksday.punch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;
import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,Settings_Activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        final ImageButton Img = (ImageButton) findViewById(R.id.imageButton);
        final TextView ClickHere = (TextView) findViewById(R.id.textView);

        final Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);
        Img.setAnimation(fadeOut);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Img.setVisibility(View.VISIBLE);
                ClickHere.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ClickHere.setVisibility(View.VISIBLE);
                Img.setImageResource(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        Img.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       SecureRandom r = new SecureRandom();
                                       int i = r.nextInt(2);
                                       if (i == 0) {
                                           Img.setImageResource(R.drawable.thumbsup);
                                           Img.startAnimation(fadeOut);
                                       } else {
                                           Boolean vib_switch = settings.getBoolean("switch_vib",true);
                                           if(vib_switch){
                                           Img.setImageResource(R.drawable.punch);
                                           Img.startAnimation(fadeOut);}
                                       }
                                   }
                               }
        );
    }


}