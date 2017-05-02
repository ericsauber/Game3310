package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Level3h extends AppCompatActivity {
    int score;
    int lives;
    int level = 3;
    EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3h);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        lives = extras.getIntExtra("lives", 1);
    }

    public void gotoLevel3i(View view) {

        textView = (EditText)findViewById(R.id.Level3h_x);
        String word = textView.getText().toString();

        if(word.equals("4")) {

            Intent intent = new Intent(this, Level3j.class);
            intent.putExtra("score", score);
            intent.putExtra("lives", lives);
            startActivity(intent);
        }
        else if(word.equals(""))
        {

        }
        else {

            Intent intent = new Intent(this, Wrong.class);
            intent.putExtra("lives", lives);
            intent.putExtra("level", level);
            startActivity(intent);
        }
    }
}
