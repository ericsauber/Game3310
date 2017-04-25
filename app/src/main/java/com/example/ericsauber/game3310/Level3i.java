package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Level3i extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3i);
        Intent extras = getIntent();
        int score = extras.getIntExtra("score", 1);
        TextView scoreView = (TextView) findViewById(R.id.Level3i_score);
        scoreView.setText("Score : " + score);
    }
}
