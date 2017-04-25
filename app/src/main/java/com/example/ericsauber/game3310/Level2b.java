package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Level2b extends AppCompatActivity {

    int score=0;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2b);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        round = (TextView) findViewById(R.id.Level2b_progress);
        round.setText("0/5 Score: " + score);
        x=0;
        arr[0]= "10";
        arr[1]= "9";
        arr[2]= "8";
        arr[3]= "7";
        arr[4]= "6";
    }
    public void button(View view)
    {
        b = (Button) view;
        //round = (TextView) findViewById(R.id.Level1c_progress);
        //acc = (TextView) findViewById(R.id.textView17);
        String letter = (String) b.getText();
        if(arr[x].equals(letter)) {
            score = score + 10;
            x++;
            y++;

            if (x == 5) {
                Intent intent = new Intent(this, Level2c.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
            round.setText(y+ "/5" + " Score: " + score);
            //acc.setText("Correct!");

        }else if (error == 2) {
            Intent intent = new Intent(this, Level2b.class);
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


    }



}
