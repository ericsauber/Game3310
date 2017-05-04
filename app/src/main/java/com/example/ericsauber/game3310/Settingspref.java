package com.example.ericsauber.game3310;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Settingspref extends AppCompatActivity{
    MyDBHandler dbHandler;
    TextView t;
    public static Button  btn;
    String user,password, newpassword, conpassword;
    private EditText result;
    private EditText newresult;
    MediaPlayer ring;
    Button logout;



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
        logout = (Button) findViewById(R.id.setting_music);
        logout.setVisibility(View.GONE);
        Cursor cursor = dbHandler.getCursorPref();
        cursor.moveToFirst();
        user =  cursor.getString(2);
        if(!user.equals("")) {
            logout.setVisibility(View.VISIBLE);
        }

    }
    public void gotoMain(View view  ){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
        finish();
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
                            logout = (Button) findViewById(R.id.setting_music);
                            logout.setVisibility(View.GONE);
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
            alert.setTitle("LOGOUT");
            alert.show();
        }
    }





    public void changemusic(View view) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.acitivity_changepass, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        final EditText userInputNew = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInputNew);
        final EditText userInputconf = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInputNewcon);

        Cursor recordSet = dbHandler.getCursorPref();
        recordSet.moveToFirst();
        user = recordSet.getString(2);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input
                                password = userInput.getText().toString();
                                newpassword = userInputNew.getText().toString();
                                conpassword= userInputconf.getText().toString();
                                if(!password.equals("") && !newpassword.equals("") && !conpassword.equals("")) {
                                    String getpassword = dbHandler.searchPassWord(user);
                                    if (getpassword.equals(password)){
                                      if(newpassword.equals(conpassword)) {
                                        dbHandler.updatePassword(user, newpassword);
                                        Toast pass1 = Toast.makeText(getApplicationContext(), "Password has been changed!", Toast.LENGTH_LONG);
                                        pass1.show();
                                      }
                                      else{
                                          Toast pass1 = Toast.makeText(getApplicationContext(), "new password do not match", Toast.LENGTH_LONG);
                                          pass1.show();
                                      }
                                    }
                                    else{
                                        Toast pass1 = Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG);
                                        pass1.show();
                                    }
                                }else {
                                    //dialog.cancel();
                                    Toast pass = Toast.makeText(getApplicationContext(), "all fields must be filed out", Toast.LENGTH_LONG);
                                    pass.show();

                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle("Change Password");
        // show it
        alertDialog.show();


   }

}