package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Level1c extends AppCompatActivity {
    int score = 0;
    int level = 1;
    int lives;
    int level2 = 22;
    Button b;
    TextView round;
    TextView acc;
    int error;
    String arr[] = new String[5];
    int x;
    int y = 0;
    int z = 0;
    EditText ed;
    Button button;
    CountDownTimer bonus;
    int time = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1c);
        Intent extras = getIntent();
        lives = extras.getIntExtra("lives", 1);
        error = 0;
        x = 0;

        score = extras.getIntExtra("score", 1);
        arr[0] = "4";
        arr[1] = "5";
        arr[2] = "6";
        arr[3] = "7";
        arr[4] = "8";
        bonus = new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                acc = (TextView) findViewById(R.id.textView22);
                acc.setText(String.valueOf(time));
            }

            @Override
            public void onFinish() {
                acc = (TextView) findViewById(R.id.textView22);
                acc.setText("Out of Time.");
            }
        }.start();
    }

    public void button(View view) {
        b = (Button) view;
        String letter = (String) b.getText();

        if (arr[x].equals(letter)) {
            score = score + 10;
            x++;
            y++;

            if (x == 5) {
                if (bonus != null) {
                    score=score+20;
                    bonus.cancel();
                    bonus = null;
                }

                Intent intent = new Intent(this, Level1d.class);
                intent.putExtra("score", score);
                intent.putExtra("lives", lives);
                startActivity(intent);
                finish();

            }
        } else {
            if (bonus != null) {
                bonus.cancel();
                bonus = null;
            }
            Intent intent = new Intent(this, Wrong.class);
            intent.putExtra("level", level);
            intent.putExtra("lives", lives);
            startActivity(intent);
            finish();
        }
    }
}

