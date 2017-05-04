package com.example.ericsauber.game3310;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main extends AppCompatActivity {
    //private static final String TAG = "Main";
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoSettingspref(View view){
        Intent intent = new Intent(this, Settingspref.class);
        startActivity(intent);
    }
    public void gotoHighscoreTable(View view) {

        Intent intent = new Intent(this, HighscoreTable.class);
        startActivity(intent);
    }

    public void gotoLevel1a(View view) {

        Intent intent = new Intent(this, Level1a.class);
        //Intent intent = new Intent(this, activity_levelalg.class);
        startActivity(intent);
        finish();
    }

    public void exitApplication(View view) {
        System.exit(0);
        finish();
    }

    public void gotoTutorial1(View view) {
        Intent intent = new Intent(this, Tutorial1.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        dbHandler = new MyDBHandler(this, null, null, 1);
        Cursor recordSet = dbHandler.getCursorPref();
        recordSet.moveToFirst();
        String name = "";
        name = recordSet.getString(2);
        if (!name.equals("")) {
            recordSet.close();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        } else{
            recordSet.close();
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        }

    }

}


