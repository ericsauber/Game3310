package com.example.ericsauber.game3310;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText editText1 = (EditText) findViewById(R.id.Login_username);
        final EditText editText2 = (EditText) findViewById(R.id.Login_password);
        editText1.requestFocus();



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
