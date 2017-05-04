package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText userInput,passwordInput,password2Input;
    TextView recordsTextView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userInput = (EditText) findViewById(R.id.Registration_username);
        passwordInput = (EditText) findViewById(R.id.Registration_password);
        password2Input = (EditText) findViewById(R.id.Registration_password2);

        recordsTextView = (TextView) findViewById(R.id.records_TextView);
        /* Can pass nulls because of the constants in the helper.
         * the 1 means version 1 so don't run update.
         */
        dbHandler = new MyDBHandler(this, null, null, 1);
        //printDatabase();
    }

    //add your elements onclick methods.
    //Add a product to the database
    public void addButtonClicked(View view){

        //passwordInput.getText().toString().isEmpty()
        if(passwordInput.getText().toString().equals("") || userInput.getText().toString().isEmpty()){
            Toast pass = Toast.makeText(Registration.this,"ALL FIELDS MUST BE FILED OUT", Toast.LENGTH_SHORT);
            pass.show();
        }else{
            if(!passwordInput.getText().toString().equals(password2Input.getText().toString())){
                //popup messsage passwords do not match
                Toast pass = Toast.makeText(Registration.this,"Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
                passwordInput.setText("");
                password2Input.setText("");
            }
            else {
                String found = dbHandler.searchPassWord(userInput.getText().toString());
                if (found.equals("")) {
                    int i =1;
                    Contact contact = new Contact();
                    contact.setName(userInput.getText().toString());
                    contact.setPassword(passwordInput.getText().toString());
                    contact.setLoginStatus(i);
                    dbHandler.addContact(contact);
                    dbHandler.setPrefname(userInput.getText().toString());
                    Intent intent = new Intent(this, Main.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast pass = Toast.makeText(Registration.this, "Username already exists", Toast.LENGTH_SHORT);
                    pass.show();
                    userInput.setText("");
                    passwordInput.setText("");
                    password2Input.setText("");
                }
            }

        }


    }
    
    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
        finish();
    }

//    public void deleteButtonClicked(View view){
//        // dbHandler delete needs string to find in the db
//        String inputText = userInput.getText().toString();
//        dbHandler.deleteProduct(inputText);
//        //printDatabase();
//    }

}



