package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Level1b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1b);
    }

    public void gotoLevel1c(View view) {


        Intent intent = new Intent(this, Level1c.class);
        //intent.putExtra("score", score);
        startActivity(intent);
    }
}
