package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tutorial7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial7);
    }

    public void gotoTutorial8(View view) {

        Intent intent = new Intent(this, Tutorial8.class);
        startActivity(intent);
    }
}
