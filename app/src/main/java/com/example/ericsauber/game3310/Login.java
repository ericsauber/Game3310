package com.example.ericsauber.game3310;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userInput,passwordInput;
    MyDBHandler dbHandler;
   // music Music;

    //MyDBHandler helper = new MyDBHandler(this);
String namestr;

    static int x = 0, b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHandler = new MyDBHandler(this, null, null, 1);
        Cursor recordSet = dbHandler.getCursorPref();
        String name="";
        if(recordSet.moveToFirst()) {
            x = recordSet.getInt(recordSet.getColumnIndex("music"));
            name = recordSet.getString(2);
        }
        else{
            dbHandler.createpref();
        }


        if (x == 0 && b==0) {
             MediaPlayer ring = MediaPlayer.create(this,R.raw.mmsong);
            ring.start();
            ring.setLooping(true);
            b++;
        }
        if(!name.equals("")){
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        }



        final EditText editText1 = (EditText) findViewById(R.id.Login_username);
        final EditText editText2 = (EditText) findViewById(R.id.Login_password);
        editText1.requestFocus();

        userInput = (EditText) findViewById(R.id.Login_username);
        passwordInput = (EditText) findViewById(R.id.Login_password);

    }


    public void loginButtonClicked(View view){
        namestr = userInput.getText().toString();
        String passwordstr = passwordInput.getText().toString();
        if(!namestr.equals("")) {
            String getpassword = dbHandler.searchPassWord(namestr);

            if (passwordstr.equals(getpassword)) {
                dbHandler.setPrefname(namestr);
                Intent i = new Intent(this, Main.class);
                startActivity(i);
            } else {
                //popup messsage passwords do not match
                Toast pass = Toast.makeText(this, "Invalid user name or password!", Toast.LENGTH_LONG);
                pass.show();
                userInput.setText("");
                passwordInput.setText("");
            }
        }
        else{
            Toast pass = Toast.makeText(this, "Must provide a username and password", Toast.LENGTH_LONG);
            pass.show();
            userInput.setText("");
            passwordInput.setText("");
        }
    }

    public void gotoMain(View view) {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    public void gotoRegistration(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }


}
