package com.ecksday.punch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class GameOverDialog extends DialogFragment
{
    public GameOverDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        if(MainActivity.winner!=0) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Game Over!")
                    .setMessage("Player " + MainActivity.winner + " wins the game!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            com.ecksday.punch.MainActivity.roundno = 1;
                            com.ecksday.punch.MainActivity.p1score = 0;
                            com.ecksday.punch.MainActivity.p2score = 0;
                            com.ecksday.punch.MainActivity.p1streak = 0;
                            com.ecksday.punch.MainActivity.p2streak = 0;
                        }
                    })
                    .create();
        }
        else {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Game Over!")
                    .setMessage("It's a Tie!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            com.ecksday.punch.MainActivity.roundno = 1;
                            com.ecksday.punch.MainActivity.p1score = 0;
                            com.ecksday.punch.MainActivity.p2score = 0;
                            com.ecksday.punch.MainActivity.p1streak = 0;
                            com.ecksday.punch.MainActivity.p2streak = 0;
                        }
                    })
                    .create();
        }
    }
}