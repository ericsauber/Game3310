package com.example.ericsauber.game3310;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Settings extends AppCompatActivity {
    MyDBHandler dbHandler;
    TextView t;
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscoretable);
        t=(TextView) findViewById(R.id.DBtest_text);
        ListView listscore=(ListView)findViewById(R.id.listscore);
        ListView listname=(ListView)findViewById(R.id.listsname);
        Typeface font=Typeface.createFromAsset(getAssets(),"fonts/MOUSETRAP!.otf");
        t.setTypeface(font);
        dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayList<String> arrname= new ArrayList<>();
        ArrayList<String> arrscore= new ArrayList<>();
        Cursor recordSet = dbHandler.getCursor();



        if(recordSet.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }
        else{
            int i = 0;
            while(recordSet.moveToNext()){
                String Name_num;
                 String num;
                if(i==0) {
                    Name_num = "PLAYER";
                    arrname.add(Name_num);
                    ListAdapter listAdaptername = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrname);
                    listname.setAdapter(listAdaptername);
                    Name_num = "HIGHSCORE";
                    arrscore.add(Name_num);
                    ListAdapter listAdapterscore  = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrscore);
                    listscore.setAdapter(listAdapterscore);
                }

                num = Integer.toString(recordSet.getInt(1));
                Name_num = recordSet.getString(0);
                arrname.add(Name_num);
                arrscore.add(num);
                ListAdapter listAdaptername = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrname);
                listname.setAdapter(listAdaptername);

                ListAdapter listAdapterscore  = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrscore);
                listscore.setAdapter(listAdapterscore);

                i++;
            }
        }
        recordSet.close();

    }


    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }


/*/
}