package com.example.ericsauber.game3310;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class HighscoreTable extends AppCompatActivity {
    MyDBHandler dbHandler;
    //private ArrayList<String> results = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscoretable);

        ListView listview=(ListView)findViewById(R.id.listView);
        dbHandler = new MyDBHandler(this, null, null, 1);

        ArrayList<String> thelist= new ArrayList<>();
        Cursor recordSet = dbHandler.getCursor();

        if(recordSet.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }
        else{
            while(recordSet.moveToNext()){
                String name = recordSet.getString(0);
                int num = recordSet.getInt(1);
                String Name_num = name + "    :    " + Integer.toString(num);
                thelist.add(Name_num);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);
                listview.setAdapter(listAdapter);

            }
        }
        recordSet.close();

    }
//    public void displayResultsList(){
//        TextView tview= new TextView(this);
//        tview.setText("This data is retrieved from the database and only 4 " +
//                "of the results are displayed");
//        //getListView().addHeaderView(tview);
//    }
    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

}

