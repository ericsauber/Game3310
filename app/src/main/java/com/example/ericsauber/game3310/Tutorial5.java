package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tutorial5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial5);
    }

    public void gotoTutorial6(View view) {

        Intent intent = new Intent(this, Tutorial6.class);
        startActivity(intent);
    }
}