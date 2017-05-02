package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Level2d extends AppCompatActivity {

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
    TextView pattern;
    CountDownTimer bonus;
    int time =10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2d);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        lives = extras.getIntExtra("lives", 1);
        round = (TextView) findViewById(R.id.Level2d_progress);
        round.setText("0/5 Score: " + score);
        x=0;
        arr[0]= "10";
        arr[1]= "11";
        arr[2]= "12";
        arr[3]= "13";
        arr[4]= "14";
    bonus = new CountDownTimer(10000,1000)
        {
    public void onTick(long millisUntilFinished) {
        time--;
        acc = (TextView) findViewById(R.id.textView22);
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

            if (x == 1) {

                pattern = (TextView) findViewById(R.id.Level2d_text1);
                pattern.setText("");
            }

            y++;

            if (x == 5) {
            if(bonus!=null)
            {
                score=score +20;
                bonus.cancel();
            }
                Intent intent = new Intent(this, Level2e.class);
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
