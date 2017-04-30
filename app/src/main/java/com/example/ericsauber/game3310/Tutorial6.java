package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tutorial6 extends AppCompatActivity {

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
        setContentView(R.layout.activity_tutorial6);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
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

    public void buttonL(View view) {

        b = (Button) view;
        String letter = (String) b.getText();

        if(zB==0) {

            if (error == 2) {

                Intent intent = new Intent(this, Tutorial6.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        }
        else {

            if (arr[x].equals(letter)) {

                score = score + 20;
                zB=0;
                x++;
                y++;

                if (x == 5) {

                    Intent intent = new Intent(this, Tutorial7.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
            else {

                Intent intent = new Intent(this, Tutorial6.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        }
    }
    public void buttonN(View view) {

        b = (Button) view;
        int bt = Integer.parseInt((String) b.getText());

        if (num[x] == bt) {

            if (zB == 0)
                zB++;

            else  {
                Intent intent = new Intent(this, Tutorial6.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        }
        else {

            error++;

            if (error >= 2) {

                Intent intent = new Intent(this, Tutorial6.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }

            if(zB!=0)
                zB=0;
        }
    }

    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}
