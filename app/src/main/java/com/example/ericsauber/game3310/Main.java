package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main extends AppCompatActivity {
    //private static final String TAG = "Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-db-1.db",MODE_PRIVATE,null);

//        String sql = "DROP TABLE IF EXISTS Contacts";
//        Log.d(TAG, "onCreate: sql = " + sql);
//        sqLiteDatabase.execSQL(sql);
//        sql = "CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER);";
//        Log.d(TAG, "onCreate: sql is " + sql);
//        sqLiteDatabase.execSQL(sql);
//
//        sql= "INSERT INTO contacts VALUES('nohemi','1234567890');";
//        Log.d(TAG, "onCreate: sql is " + sql);
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO contacts VALUES('test','213233333');";
//        Log.d(TAG, "onCreate: sql is " + sql);
//        sqLiteDatabase.execSQL(sql);


//        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts",null);
//        if(query .moveToFirst()){
//            do{
//                String name = query.getString(0);
//                int phone = query.getInt(1);
//                Toast.makeText(this, "Name = " +name + " phone = " + phone, Toast.LENGTH_LONG).show();
//            }while(query.moveToNext());
//
//        }
//        query.close();
//        sqLiteDatabase.close();
    }

    public void gotoHighscoreTable(View view) {

        Intent intent = new Intent(this, HighscoreTable.class);
        startActivity(intent);
    }

    public void gotoLevel1a(View view) {

        //Intent intent = new Intent(this, Level1a.class);
        Intent intent = new Intent(this, activity_levelalg.class);
        startActivity(intent);
    }

    public void gotoTutorial1(View view) {

        Intent intent = new Intent(this, Tutorial1.class);
        startActivity(intent);
    }


}
