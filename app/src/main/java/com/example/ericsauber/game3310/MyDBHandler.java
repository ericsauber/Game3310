package com.example.ericsauber.game3310;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

/**
 * Created by nohemi on 4/25/17.
 */

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "cont.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOGINSTATUS = "loginStatus";

    public static final String TABLE_HIGH = "high";
    public static final String COLUMN_HIID = "hi_id";
    public static final String COLUMN_HISCORE = "highscore";

    public static final String TABLE_PREF = "pref";
    public static final String COLUMN_MUSIC = "music";

    ListView listview;

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_LOGINSTATUS + " INTEGER NOT NULL" +
                ");";
        db.execSQL(query);
        query = "CREATE TABLE " + TABLE_HIGH +"("+ COLUMN_HIID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_HISCORE + " INTEGER NOT NULL" +
                  ");";
        db.execSQL(query);
        query =  "CREATE TABLE " + TABLE_PREF +"("+ COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_MUSIC + " INTEGER, " + COLUMN_LOGINSTATUS + " TEXT);";
        db.execSQL(query);

    }

    //DROP=DELETE TABLE IF UPGRADE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREF);
        onCreate(db);
    }

    //Add a new row of username to CONTACTS Table
    public void addContact(Contact contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contacts.getName());
        values.put(COLUMN_PASSWORD, contacts.getPassword());
        values.put(COLUMN_LOGINSTATUS, contacts.getLoginStatus());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
       // this.setPrefname(contacts.getName());
    }
    public void logout(String uid){
        int i = 0;
        String query = "UPDATE " +TABLE_NAME +
                " SET " + COLUMN_LOGINSTATUS +
                " = "+i+ " WHERE " +
                COLUMN_NAME + " = \"" + uid +"\";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        uid="";
        this.setPrefname(uid);

    }
    public int checklogstatus(){
        int i = 0;
        SQLiteDatabase db = getReadableDatabase();
        String query ="SELECT count(*) FROM " + TABLE_NAME+" WHERE " +
                COLUMN_LOGINSTATUS + " " +i+ ";";
        Cursor recordSet = db.rawQuery(query, null);
        i= recordSet.getInt(0);

        if (i == 0){
            i = -1;
        }
        else{
            i = recordSet.getInt(recordSet.getColumnIndex("id"));
        }
        recordSet.close();
        return i;
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
    //Adds HIGHSCORE to HIGHSCORE table
    public void addHighScore(Highcore highcore){
        ContentValues values = new ContentValues();
        values.put(COLUMN_HISCORE, highcore.getHI_score());
        values.put(COLUMN_NAME, highcore.getHI_Name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_HIGH, null, values);
        db.close();
    }
//    SELECT hi_id, highscore WHERE highscore = (SELECT min(highscore) FROM high);
//    Error: no such column: hi_id

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

    //UPDATE HIGHSCORE where MIN highscore is;
    public void updateHighScore(Highcore highcore, int index){

        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " +TABLE_HIGH +
                " SET " + COLUMN_NAME+
                " = \"" + highcore.getHI_Name() + "\", " +COLUMN_HISCORE+ " =  " +highcore.getHI_score()+ " WHERE " +
                COLUMN_HIID + " = " + index +";";
        db.execSQL(query);

    }

    //returns -1 norows exist, 0 if it is not
    public int getindexMIN(int highscore){
        int index = 0;
        String query = "SELECT " +COLUMN_HIID+", " +COLUMN_HISCORE+ " FROM " +TABLE_HIGH+" WHERE " +
                COLUMN_HISCORE +" = (SELECT min(" +COLUMN_HISCORE + ") FROM "+TABLE_HIGH+ ");";
        SQLiteDatabase db = getReadableDatabase();
        Cursor recordSet = db.rawQuery(query, null);
        if(recordSet.moveToFirst()){
            if(highscore >= recordSet.getInt(1)){
                index = recordSet.getInt(0);
             }
        }
        recordSet.close();
        return index;
    }
//    SELECT hi_id, highscore FROM high WHERE highscore = (SELECT min(highscore) FROM high
    //returns total COUNT of Highscore recorded
    public int getCountHighScore(){
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
        //ArrayList<String> listItems = new ArrayList<String>();
        String query = "SELECT " + COLUMN_NAME+ ", "+ COLUMN_HISCORE+ " FROM "+TABLE_HIGH+" ORDER BY " + COLUMN_HISCORE +
                " DESC;";
       Cursor recordSet = db.rawQuery(query, null);
        return recordSet;

    }
    public Cursor getCursorPref() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PREF + ";";
        Cursor recordSet = db.rawQuery(query, null);
        return recordSet;

    }
    public void createpref(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO " +TABLE_PREF+ "  VALUES('1','0','');";
        db.execSQL(query);

    }
    public void setPrefname(String name){

        SQLiteDatabase db = getWritableDatabase();
        String query= "UPDATE " +TABLE_PREF +
                " SET " + COLUMN_LOGINSTATUS+
                " = \"" +name+ "\" WHERE " +
                COLUMN_ID+ " = 1;";
        db.execSQL(query);
    }
    public void setPrefmusic(int i){
        SQLiteDatabase db = getWritableDatabase();
        String query= "UPDATE " +TABLE_PREF +
                " SET " + COLUMN_MUSIC+
                " = \"" +i+ "\" WHERE " +
                COLUMN_ID+ " = 1;";
        db.execSQL(query);
    }
}



