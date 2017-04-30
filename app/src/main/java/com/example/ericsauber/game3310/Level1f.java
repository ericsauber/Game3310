package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Level1f extends AppCompatActivity {
    int score;
    int lives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1f);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        lives = extras.getIntExtra("lives", 1);
        TextView scoreView = (TextView) findViewById(R.id.Level1f_score);
        scoreView.setText("Score : " + score);
    }

    public void gotoLevel2a(View view) {

        Intent intent = new Intent(this, Level2a.class);
        intent.putExtra("score", score);
        intent.putExtra("lives", lives);
        startActivity(intent);
    }
}
