package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Level3b extends AppCompatActivity {

    int score=0;
    int lives;
    int level = 3;
    Button b;
    TextView round;
    TextView acc;
    int error=0;
    String arr[] = new String[5];
    int num[]= new int[5];
    int x;
    int y=0;
    int zA=0;
    int zB=0;
    EditText ed;
    Button button;
    TextView pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3b);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        lives = extras.getIntExtra("lives", 1);
        round = (TextView) findViewById(R.id.Level3b_progress);
        round.setText("0/5 Score: " + score);
        x=0;
        arr[0]= "C";
        arr[1]= "D";
        arr[2]= "E";
        arr[3]= "F";
        arr[4]= "G";
        num[0]= 3;
        num[1]= 4;
        num[2]= 5;
        num[3]= 6;
        num[4]= 7;
    }
    public void buttonL(View view)
    {
        b = (Button) view;
        round = (TextView) findViewById(R.id.Level3b_progress);
        //acc = (TextView) findViewById(R.id.textView17);
        String letter = (String) b.getText();
        if(zB==0)
        {




                Intent intent = new Intent(this, Wrong.class);
                intent.putExtra("score", score);
                intent.putExtra("lives", lives);
                intent.putExtra("level", level);
                startActivity(intent);
                finish();




        }
        else {
            if (arr[x].equals(letter)) {
                score = score + 20;
                zB=0;
                x++;
                y++;
                if (x == 5) {
                    Intent intent = new Intent(this, Level3c.class);
                    intent.putExtra("score", score);
                    intent.putExtra("lives", lives);
                    startActivity(intent);
                    finish();
                }
            } else {
                Intent intent = new Intent(this, Wrong.class);
                intent.putExtra("score", score);
                intent.putExtra("lives", lives);
                intent.putExtra("level", level);
                startActivity(intent);
                finish();
            }
            round.setText(y + "/5" + " Score: " + score);
            //acc.setText("Correct!");

        }
    }
    public void buttonN(View view) {
        b = (Button) view;
        round = (TextView) findViewById(R.id.Level3b_progress);
        //acc = (TextView) findViewById(R.id.textView17);
        int bt = Integer.parseInt((String) b.getText());

        if (num[x] == bt) {
            if (zB == 0) {

                pattern = (TextView) findViewById(R.id.Level3b_text1);
                pattern.setText("");
                zB++;
            }
            else {
                Intent intent = new Intent(this, Wrong.class);
                intent.putExtra("score", score);
                intent.putExtra("level", level);
                intent.putExtra("lives", lives);
                startActivity(intent);
                finish();
            }
        }
        else
        {

                Intent intent = new Intent(this, Wrong.class);
                intent.putExtra("score", score);
                intent.putExtra("lives", lives);
                intent.putExtra("level", level);
                startActivity(intent);
                finish();


        }
    }

}
