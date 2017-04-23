package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoDBtest(View view) {

        Intent intent = new Intent(this, DatabaseTest.class);
        startActivity(intent);
    }

    public void gotoLevel1a(View view) {

        Intent intent = new Intent(this, Level1b.class);
        startActivity(intent);
    }

    public void gotoTutorial1(View view) {

        Intent intent = new Intent(this, Tutorial1.class);
        startActivity(intent);
    }


}
