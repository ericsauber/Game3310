package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Level3j extends AppCompatActivity {

    int score;
    int lives;
    int level = 3;
    EditText typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3j);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        lives = extras.getIntExtra("lives", 1);
    }

    public void gotoLevel4a(View view) {

        Intent intent = new Intent(this, activity_levelalg.class);
        intent.putExtra("score", score);
        intent.putExtra("lives", lives);
        startActivity(intent);
        finish();
    }
}