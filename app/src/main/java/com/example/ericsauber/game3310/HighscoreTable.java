package com.example.ericsauber.game3310;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HighscoreTable extends AppCompatActivity {
    MyDBHandler dbHandler;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscoretable);
        t=(TextView) findViewById(R.id.DBtest_text);

        ListView listview=(ListView)findViewById(R.id.listView);
        Typeface font=Typeface.createFromAsset(getAssets(),"fonts/MOUSETRAP!.otf");
        t.setTypeface(font);
        dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayList<String> thelist= new ArrayList<>();
        Cursor recordSet = dbHandler.getCursor();



        if(recordSet.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }
        else{
            int i = 0;
            while(recordSet.moveToNext()){
                String Name_num;
                if(i==0) {
                    Name_num = "PLAYER          SCORE";
                    thelist.add(Name_num);
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);
                    listview.setAdapter(listAdapter);
                }
                int num = recordSet.getInt(1);
                String name = recordSet.getString(0);
                Name_num = name + "    :    " + Integer.toString(num);
                thelist.add(Name_num);

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);

                listview.setAdapter(listAdapter);

                i++;
            }
        }
        recordSet.close();

    }


    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

}

