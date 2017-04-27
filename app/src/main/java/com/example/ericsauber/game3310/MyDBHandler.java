package com.example.ericsauber.game3310;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_PASSWORD + " TEXT" +
                ");";
        db.execSQL(query);
    }
    //Lesson 51
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add a new row of username to the database
    public void addContact(Contact contacts){
        ContentValues values = new ContentValues();
        String newname=contacts.getName();
        //boolean found = searchName(newname);
        //if()

        values.put(COLUMN_NAME, contacts.getName());
        values.put(COLUMN_PASSWORD, contacts.getPassword());
       // values.put(COLUMN_ID,count);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
//    public boolean searchName(String newname){
//
//    }

    //Delete a product from the database
    public void deleteProduct(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    // this is goint in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";// why not leave out the WHERE  clause?

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
        db.close();
        return dbString;
    }


    public String searchPass(String uname){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT password FROM " + TABLE_NAME + " WHERE  username= '"+uname+"'";
        return query;
//        Cursor recordSet = db.rawQuery(query, null);
//        String dbString = "";
//        String n, p= "not found";
//        int index = recordSet.getColumnIndex("name");
//        p= recordSet.getString(recordSet.getColumnIndex("password"));
//        return p;
//  while (!recordSet.isAfterLast()) {
//            // null could happen if we used our empty constructor
//            dbString="";
//            if (recordSet.getString(recordSet.getColumnIndex("name")) != null) {
//                dbString += recordSet.getString(recordSet.getColumnIndex("name"));
//                dbString += "\n";
//
//            }
//            recordSet.moveToNext();
//        }
//        db.close();
//        return dbString;
        //Cursor recordSet = db.rawQuery(query, null);
       // String dbString = "";
      //  String n, p= "not found";
//
//        while (!recordSet.isAfterLast()) {
//            // null could happen if we used our empty constructor
//            dbString="";
//            if (recordSet.getString(recordSet.getColumnIndex("name")) != null) {
//                dbString += recordSet.getString(recordSet.getColumnIndex("name"));
//                dbString += "\n";
//
//            }
//            recordSet.moveToNext();
//        }
//        db.close();
//        return dbString;
//    }
    }
//        while (!recordSet.isAfterLast()) {
//
//               // n = recordSet.getString(0);
//            dbString="";
//           // if (recordSet.getString(recordSet.getColumnIndex("name")) != null) {
//            if (recordSet.getString(recordSet.getColumnIndex("name")) != null) {
//                dbString += recordSet.getString(recordSet.getColumnIndex("name"));
//                dbString += "\n";
//                if(dbString.equals(uname)){
//                    p += recordSet.getString(recordSet.getColumnIndex("password"));
//                    db.close();
//                    return p;
//                }
//
//            }
//            recordSet.moveToNext();
//
//        }


        //p= "not found";
//
//        db.close();
//        return p;
//
//
//    }

}


//    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null, " + "name text not null, password text not null);";
//
//    public MyDBHandler(Context context){
//        super(context , DATABASE_NAME, null, DATABASE_VERSION);
//
//    }
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,password INTEGER)");
//
//       // String CREATE_CONTACTS_TABLE ="create table "+ TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
//
//        //String CREATE_CONTACTS_TABLE ="CREATE TABLE" + TABLE_NAME +"("+COLUMN_ID + " id INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_Name + "TEXT," + COLUMN_PASSWORD + "TEXT" + ")";
//        //db.execSQL(CREATE_CONTACTS_TABLE);
//        //this.db = db;
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String query = "DROP TABLE OF EXISTS"+TABLE_NAME;
//        db.execSQL(query);
//        this.onCreate(db);
//    }
//
//    public boolean insertContact(String name, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        String query = "select * from contracts";
//        //Cursor cursor = db.rawQuery(query, null);
//        //int count = cursor.getCount();
//
//        //contentValues.put(COLUMN_ID,count);
//        contentValues.put(COLUMN_Name, name);
//        contentValues.put(COLUMN_PASSWORD,password);
//        long result = db.insert(TABLE_NAME, null, contentValues);
//        if (result == -1)
//            return false;
//        else
//            return true;
//
//        //db.close();
//    }
//
//    public String searchPass(String uname){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "select uname, pass from "+TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
//        String n, p= "not found";
//        if(cursor.moveToFirst())
//        {
//            do{
//                n = cursor.getString(0);
//                if(n.equals(uname))
//                {
//                    p = cursor.getString(1);
//                    break;
//
//                }
//
//            }while(cursor.moveToNext());
//
//
//
//        }
//        return p;
//
//
//    }

//}
