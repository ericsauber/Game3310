package com.example.ericsauber.game3310;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nohemi on 4/25/17.
 */

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "contactsDB.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    public static final String TABLE_HIGH = "high";
    public static final String COLUMN_HIID = "hi_id";
    public static final String COLUMN_HISCORE = "highscore";
    ListView listview;

    //public static final String COLUMN_NAME = "userid";



    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_PASSWORD + " TEXT NOT NULL" +
                ");";
        db.execSQL(query);
        query = "CREATE TABLE " + TABLE_HIGH +"("+ COLUMN_HIID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_HISCORE + " INTEGER NOT NULL" +
                  ");";
        db.execSQL(query);

    }

    //DROP=DELETE TABLE IF UPGRADE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGH);
        onCreate(db);
    }

    //Add a new row of username to CONTACTS Table
    public void addContact(Contact contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contacts.getName());
        values.put(COLUMN_PASSWORD, contacts.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    //Adds HIGHSCORE to HIGHSCORE table
    public void addHighScore(Highcore highcore){
        ContentValues values = new ContentValues();
        values.put(COLUMN_HISCORE, highcore.getHI_score());
        values.put(COLUMN_NAME, highcore.getHI_Name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    //updatePassword
    public void updatePassword(String uname, String newpassword){
        String query = "UPDATE " +TABLE_NAME +
                " SET " + COLUMN_PASSWORD +
                " =\"" + newpassword + "\" WHERE " +
                COLUMN_NAME + " = \"" + uname +"\";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
    //Delete a user from the CONTACTS table
    public void deleteUser(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    // this RETURNS NAME JUST ADDED in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1;";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            dbString="";
            if (recordSet.getString(recordSet.getColumnIndex("name")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("name"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        recordSet.close();
       // db.close();
        return dbString;
    }

    //Returns Password from CONTACTS Table
    public String searchPassWord(String uname){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT password FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=\"" + uname + "\";";
        String password="";
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        if (recordSet.moveToFirst()) {
            password = recordSet.getString(recordSet.getColumnIndex("password"));
        }
        recordSet.close();
        return password;
    }
    //Returns UserID from CONTACTS Table
    public int searchUserId(String uname){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT password FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=\"" + uname + "\";";
        int id=-1;
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        if (recordSet.moveToFirst()) {
            id = recordSet.getInt(recordSet.getColumnIndex("id"));
        }
        recordSet.close();
        return id;
    }
    //RETURNS USERID from HIGHSCORE Table
    public String searchHighScore(int score){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT password FROM " + TABLE_HIGH + " WHERE " + COLUMN_HISCORE + "=\"" + score + "\";";
        String id= "";
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        if (recordSet.moveToFirst()) {
            id = recordSet.getString(recordSet.getColumnIndex("name"));
        }
        recordSet.close();
        return id;
    }
    /*
    //DELETE last 5th HIGHSCORE from HIGHSCORE table
    public void deleteHighScore(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_HIGH + " WHERE " + COLUMN_ID + "= 5;");
    }
    */
    //UPDATE HIGHSCORE where MIN highscore is;
    public void updateHighScore(Highcore highcore, int index){

        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " +TABLE_HIGH +
                " SET " + COLUMN_NAME+
                " =\"" + highcore.getHI_Name() + "\", " +COLUMN_HISCORE+ " = \" " +highcore.getHI_score()+ " WHERE " +
                COLUMN_HIID + " = \"" + index +"\";";
        db.execSQL(query);

    }


    public int getindexMIN(int highscore){
        int index = -1;
        String query = "SELECT min(" +COLUMN_HISCORE + ") FROM "+TABLE_NAME+ ";";
        SQLiteDatabase db = getWritableDatabase();
        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        if(highscore > recordSet.getInt(recordSet.getColumnIndex(COLUMN_HISCORE))){
            index = recordSet.getInt(recordSet.getColumnIndex(COLUMN_HIID));
        }
        return index;
    }

    //returns total COUNT of Highscore recorded
    public int getCountHighScore(int highscore){
        int count = 0;

        SQLiteDatabase db = getReadableDatabase();
        String query ="SELECT count(*) FROM " + TABLE_HIGH+";";
        Cursor recordSet = db.rawQuery(query, null);
        if (recordSet.moveToFirst()) {
            count = recordSet.getInt(0);
        }
        recordSet.close();
        return count;

    }
    public Cursor getCursor(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> listItems = new ArrayList<String>();
//        String query = "SELECT * FROM " + TABLE_HIGH +";";
//                ; //= "CREATE INDEX " +COLUMN_HISCORE + " ON (" + TABLE_HIGH +

        String query = "SELECT " + COLUMN_NAME+ ", "+ COLUMN_HISCORE+ " FROM "+TABLE_HIGH+" ORDER BY " + COLUMN_HISCORE +
                " DESC;";
       Cursor recordSet = db.rawQuery(query, null);
       // recordSet.moveToFirst();
        return recordSet;

    }
    /*
    public ArrayList<String> getHighList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> listItems = new ArrayList<String>();
        String query = "CREATE INDEX " +COLUMN_HISCORE + " ON (" + TABLE_HIGH +
                ");";
        db.execSQL(query);
        Cursor recordSet = db.rawQuery(query,null);

        query = "SELECT * FROM "+ TABLE_HIGH+ " INDEXED BY " +COLUMN_HISCORE +
                ";";
        recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();
        if(recordSet != null){
            do{
                String name = recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                int num=recordSet.getInt(recordSet.getColumnIndex(COLUMN_HISCORE));
                String Name_num=name + "    :    " + Integer.toString(num);
                listItems.add(Name_num);
                recordSet.moveToNext();
            }while(!recordSet.isAfterLast());

        }
        recordSet.close();
        return listItems;
    }
    */

}
//SELECT count(*) FROM COMPANY;


