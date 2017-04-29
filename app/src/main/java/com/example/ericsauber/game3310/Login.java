package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userInput,passwordInput;
    MyDBHandler dbHandler;

    //MyDBHandler helper = new MyDBHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInput = (EditText) findViewById(R.id.Login_username);
        passwordInput = (EditText) findViewById(R.id.Login_password);
        dbHandler = new MyDBHandler(this, null, null, 1);
    }


    public void loginButtonClicked(View view){
        String namestr = userInput.getText().toString();
        String passwordstr = passwordInput.getText().toString();
        String getpassword = dbHandler.searchPass(namestr);

        Toast passw = Toast.makeText(this, " "+getpassword+" " +passwordstr+" ", Toast.LENGTH_SHORT);
        passw.show();
        if (passwordstr.equals(getpassword)) {
                Intent i = new Intent(this, Main.class);
                //i.putExtra("Username", namestr);
                startActivity(i);
            } else {
                //popup messsage passwords do not match
                Toast pass = Toast.makeText(this, "Username and Passwords don't match!", Toast.LENGTH_SHORT);
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
