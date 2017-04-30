package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tutorial2 extends AppCompatActivity {

    int score = 0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial2);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        x=0;
        arr[0]= "4";
        arr[1]= "5";
        arr[2]= "6";
        arr[3]= "7";
        arr[4]= "8";
    }

    public void button(View view) {

        b = (Button) view;
        String letter = (String) b.getText();

        if(arr[x].equals(letter)) {

            score = score + 10;
            x++;
            y++;

            if (x == 5) {
                Intent intent = new Intent(this, Tutorial3.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }

        } else {

            Intent intent = new Intent(this, Tutorial2.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}
