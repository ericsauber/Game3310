package com.example.ericsauber.game3310;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    private static final String TAG = "Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-db-1.db",MODE_PRIVATE,null);
        String sql = "CREATE TABLE contacts(name TEXT, phone INTEGER);";
        Log.d(TAG, "onCreate: sql is " + sql);

        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO contacts VALUES('nohemi','1234567890');";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO contacts VALUES('test','213233333');";
        sqLiteDatabase.execSQL(sql);


        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts",null);
        if(query .moveToFirst()){
            String name = query.getString(0);
            int phone = query.getInt(1);
            Toast.makeText(this, "Name = " +name + " phone = " + phone, Toast.LENGTH_LONG).show();

        }
        query.close();
        sqLiteDatabase.close();
    }

    public void gotoDBtest(View view) {

        Intent intent = new Intent(this, DatabaseTest.class);
        startActivity(intent);
    }

    public void gotoLevel1a(View view) {

        Intent intent = new Intent(this, Level1a.class);
        startActivity(intent);
    }

    public void gotoTutorial1(View view) {

        Intent intent = new Intent(this, Tutorial1.class);
        startActivity(intent);
    }


}
