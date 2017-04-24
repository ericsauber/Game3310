package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Level3g extends AppCompatActivity {

    int score=0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3g);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        round = (TextView) findViewById(R.id.Level3g_progress);
        round.setText("0/5 Score: " + score);
        x=0;
        arr[0]= "D";
        arr[1]= "E";
        arr[2]= "F";
        arr[3]= "G";
        arr[4]= "H";
        num[0]= 3;
        num[1]= 4;
        num[2]= 5;
        num[3]= 6;
        num[4]= 7;
    }
    public void buttonL(View view)
    {
        b = (Button) view;
        round = (TextView) findViewById(R.id.Level3g_progress);

        //acc = (TextView) findViewById(R.id.textView17);

        String letter = (String) b.getText();

        if(zB==0)
        {

            if (error == 2) {
                Intent intent = new Intent(this, Level3g.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
            error++;
            //acc.setText("Incorrect. Do the Number first. " + error + "/2 Mistakes used.");


        }
        else {
            if (arr[x].equals(letter)) {

                score = score + 20;
                zB=0;
                x++;
                y++;
                if (x == 5) {

                    Intent intent = new Intent(this, Level3h.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
            else if (error == 2) {

                Intent intent = new Intent(this, Level3g.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            } else if (error == 1) {
                //acc.setText("Incorrect! 2/2 Mistakes used.");
                error++;
            } else {
                //acc.setText("Incorrect! 1/2 Mistakes used.");
                error++;
            }
            round.setText(y + "/5" + " Score: " + score);
            //acc.setText("Correct!");

        }
    }
    public void buttonN(View view) {
        b = (Button) view;
        round = (TextView) findViewById(R.id.Level3g_progress);
        //acc = (TextView) findViewById(R.id.textView17);
        int bt = Integer.parseInt((String) b.getText());

        if (num[x] == bt) {
            if (zB == 0) {
                zB++;
            }
            else if (error >= 2) {
                Intent intent = new Intent(this, Level3g.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            } else if (error >= 1) {
                //acc.setText("Incorrect! 2/2 Mistakes used.");
                error++;
            } else {
                //acc.setText("Incorrect! 1/2 Mistakes used.");
                error++;
            }
        }
        else
        {
            error++;
            if (error >= 2) {
                Intent intent = new Intent(this, Level3g.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
            //acc.setText("Incorrect! Mistakes used" + error + "/2");
            if(zB!=0)
            {
                zB=0;
            }

        }
    }

}
