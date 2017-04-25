package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Level2f extends AppCompatActivity {
int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2f);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        int score = extras.getIntExtra("score", 1);
        TextView scoreView = (TextView) findViewById(R.id.textView35);
        scoreView.setText("Score : " + score);
    }

    public void gotoLevel3a(View view) {

        Intent intent = new Intent(this, Level3a.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}
