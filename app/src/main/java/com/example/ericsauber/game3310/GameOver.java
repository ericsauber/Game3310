package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void gotoLevel1a(View view) {


        Intent intent = new Intent(this, Level1a.class);
        //intent.putExtra("score", score);
        startActivity(intent);
    }

    public void gotoMain(View view) {


        Intent intent = new Intent(this, Main.class);
        //intent.putExtra("score", score);
        startActivity(intent);
    }
}
