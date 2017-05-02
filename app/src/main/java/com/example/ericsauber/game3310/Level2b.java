package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Level2b extends AppCompatActivity {

    int score=0;
    int level=2;
    int lives;
    Button b;
    TextView round;
    TextView acc;
    int error;
    String arr[] = new String[5];
    int x;
    int y=0;
    int z=0;
    EditText ed;
    Button button;
    CountDownTimer bonus;
    int time =10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2b);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        lives = extras.getIntExtra("lives", 1);
        round = (TextView) findViewById(R.id.Level2b_progress);
        round.setText("0/5 Score: " + score);
        x=0;
        arr[0]= "10";
        arr[1]= "9";
        arr[2]= "8";
        arr[3]= "7";
        arr[4]= "6";
        bonus = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                acc = (TextView) findViewById(R.id.Level2b_progress);
                acc.setText(String.valueOf(time));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void button(View view) {

        b = (Button) view;
        String letter = (String) b.getText();

        if(arr[x].equals(letter)) {
            score = score + 10;
            x++;
            y++;

            if (x == 5) {
                if(bonus!=null)
                {
                    bonus.cancel();
                }
                Intent intent = new Intent(this, Level2c.class);
                intent.putExtra("score", score);
                intent.putExtra("lives", lives);
                startActivity(intent);
                finish();
            }

            round.setText(y+ "/5" + " Score: " + score);
        } else {
            Intent intent = new Intent(this, Wrong.class);
            intent.putExtra("level", level);
            intent.putExtra("lives", lives);
            startActivity(intent);
            finish();
        }
    }



}
