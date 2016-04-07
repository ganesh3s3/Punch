package com.ecksday.punch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    Vibrator vibrator;
    public static int p1score=0,p2score=0,p1hs,p2hs,roundno=1,p1streak=0,p2streak=0,winner, playerturn=1;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Intent intent = new Intent(MainActivity.this,Settings_Activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        final Button P1Button = (Button) findViewById(R.id.button);
        final Button P2Button = (Button) findViewById(R.id.button2);
        final Button RoundButton = (Button) findViewById(R.id.button3);

        final ImageButton Img = (ImageButton) findViewById(R.id.imageButton);
        final TextView ClickHere = (TextView) findViewById(R.id.textView);
        P1Button.setText(getResources().getString(R.string.player1)+p1score);
        P2Button.setText(getResources().getString(R.string.player2) + p2score);
        RoundButton.setText(getResources().getString(R.string.roundno) + roundno);

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
                                       if (roundno < 10) {
                                           SecureRandom r = new SecureRandom();
                                           int i = r.nextInt(2);
                                           if (playerturn == 1) {
                                               if (i>0) {
                                                   p1streak++;
                                                   p2streak = 0;
                                                   p1score += p1streak;
                                                   RoundButton.setText(getResources().getString(R.string.roundno) + roundno);
                                                   Img.setImageResource(R.drawable.thumbsup);
                                                   Img.startAnimation(fadeOut);
                                               }
                                               else {
                                                   p1streak = 0;
                                                   p1score--;
                                                   playerturn = 2;
                                                   roundno++;
                                                   RoundButton.setText(getResources().getString(R.string.roundno) + roundno);
                                                   Boolean vib_switch = settings.getBoolean("switch_vib", true);
                                                   if (vib_switch) {
                                                       vibrator.vibrate(1000);
                                                   }
                                                   Img.setImageResource(R.drawable.punch);
                                                   Img.startAnimation(fadeOut);
                                               }
                                               P1Button.setText(getResources().getString(R.string.player1) + p1score);
                                           }
                                           else if (playerturn == 2) {
                                               if (i>0) {
                                                   p2streak++;
                                                   p1streak = 0;
                                                   p2score += p2streak;
                                                   RoundButton.setText(getResources().getString(R.string.roundno) + roundno);
                                                   Img.setImageResource(R.drawable.thumbsup);
                                                   Img.startAnimation(fadeOut);
                                               }
                                               else {
                                                   p2streak = 0;
                                                   p2score--;
                                                   playerturn = 1;
                                                   roundno++;
                                                   RoundButton.setText(getResources().getString(R.string.roundno) + roundno);
                                                   Boolean vib_switch = settings.getBoolean("switch_vib", true);
                                                   if (vib_switch) {
                                                       vibrator.vibrate(1000);
                                                   }
                                                   Img.setImageResource(R.drawable.punch);
                                                   Img.startAnimation(fadeOut);
                                               }
                                               P2Button.setText(getResources().getString(R.string.player2) + p2score);
                                           }
                                       }

                                       else {
                                           if (p1score > p2score)
                                               winner = 1;
                                           else if (p2score > p1score)
                                               winner = 2;
                                           else
                                               winner = 0;
                                           GameOverDialog dialog = new GameOverDialog();
                                           dialog.show(getFragmentManager(), "tag");
                                           roundno = 1;
                                           p1score = 0;
                                           p2score = 0;
                                           p1streak = 0;
                                           p2streak = 0;
                                           playerturn=1;
                                       }
                                   }
                               }
        );
    }
}

