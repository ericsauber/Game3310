package com.example.ericsauber.game3310;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Settingspref extends AppCompatActivity{
    MyDBHandler dbHandler;
    TextView t;
    public static Button  btn;
    String user;

    MediaPlayer ring;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingspref);

        dbHandler = new MyDBHandler(this, null, null, 1);
        t = (TextView) findViewById(R.id.Settings_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/MOUSETRAP!.otf");
        t.setTypeface(font);
        ring = MediaPlayer.create(this,R.raw.mmsong);
        btn=(Button)findViewById(R.id.setting_logout);
    }
    public void gotoMain(View view  ){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    public void changelogout(View view) {
        Cursor recordset = dbHandler.getCursorPref();
        recordset.moveToFirst();
        user = recordset.getString(2);

        if(user.equals("")){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }else {


            AlertDialog.Builder altdial = new AlertDialog.Builder(Settingspref.this);
            altdial.setMessage("Do you realy want to Logout?").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHandler.logout(user);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
            AlertDialog alert = altdial.create();
            alert.setTitle("Dialog Header");
            alert.show();
        }
    }





    public void changemusic(View view) {

        Cursor recordSet= dbHandler.getCursorPref();
        recordSet.moveToFirst();

        int x=recordSet.getInt(1);

        dbHandler.setPrefmusic(x);
        if(x==1){
            Toast pass = Toast.makeText(this, "Inside turn on", Toast.LENGTH_LONG);
            pass.show();
            ring.start();
            ring.setLooping(true);
            dbHandler.setPrefmusic(x);
        }else{
            Toast pass = Toast.makeText(this, "inside turn off", Toast.LENGTH_LONG);
            pass.show();
            ring.release();
            dbHandler.setPrefmusic(x);
        }
   }

}