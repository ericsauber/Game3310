package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Level1a extends AppCompatActivity {

    static int lives = 2;
    static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1a);
        Intent extras = getIntent();
        lives = extras.getIntExtra("lives", 2);
    }

    public void gotoLevel1b(View view) {

        Intent intent = new Intent(this, Level1b.class);
        intent.putExtra("lives", lives);
        startActivity(intent);
        finish();
    }
}
