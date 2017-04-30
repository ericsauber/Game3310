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

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        String created = "Welcome ";
        dbString = created +dbString;
        recordsTextView.setText(dbString);
        userInput.setText("");
        passwordInput.setText("");
        password2Input.setText("");
        Toast pass = Toast.makeText(Registration.this,"Username created!", Toast.LENGTH_SHORT);
        pass.show();
        recordsTextView.setText("");

    }

    //add your elements onclick methods.
    //Add a product to the database
    public void addButtonClicked(View view){

        //passwordInput.getText().toString().isEmpty()
        if(passwordInput.getText().toString().equals("") || userInput.getText().toString().isEmpty()){
            Toast pass = Toast.makeText(Registration.this,"ALL FIELDS MUST BE FILED OUT", Toast.LENGTH_SHORT);
            pass.show();

        }
        else if(!passwordInput.getText().toString().equals(password2Input.getText().toString())){
                //popup messsage passwords do not match
                Toast pass = Toast.makeText(Registration.this,"Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();

            passwordInput.setText("");
            password2Input.setText("");
            }
        else{
            String found = dbHandler.searchPassWord(userInput.getText().toString());
            if(found == null) {

                Contact contact = new Contact();
                contact.setName(userInput.getText().toString());
                contact.setPassword(passwordInput.getText().toString());

                dbHandler.addContact(contact);
                printDatabase();
            }
            else{
                Toast pass = Toast.makeText(Registration.this,"Username already exists", Toast.LENGTH_SHORT);
                pass.show();
                userInput.setText("");
                passwordInput.setText("");
                password2Input.setText("");
            }

        }

    }
    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

//    public void deleteButtonClicked(View view){
//        // dbHandler delete needs string to find in the db
//        String inputText = userInput.getText().toString();
//        dbHandler.deleteProduct(inputText);
//        //printDatabase();
//    }

}



//    MyDBHandler myDb;
//    EditText editName, editPassword, editPassword2;
//    Button regsignupbtn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registration);
//        myDb = new MyDBHandler(this);
//
//        editName = (EditText)findViewById(R.id.Registration_username);
//        //String namestr = editName.getText().toString();
//
//        editPassword = (EditText)findViewById(R.id.Registration_password);
//        //String passwordstr = editPassword.getText().toString();
//
//        editPassword2 = (EditText)findViewById(R.id.Registration_password2);
//        //String password2str = editPassword2.getText().toString();
//        regsignupbtn = (Button)findViewById(Registration_signup);
//        AddData();
//
//        if(!passwordstr.equals(password2str)){
//            //popup messsage passwords do not match
//            Toast pass = Toast.makeText(Registration.this,"Passwords don't match!", Toast.LENGTH_SHORT);
//            pass.show();
//        }
//
//        else{
//            //insert into database
//            Contact c = new Contact();
//            c.setName(namestr);
//            c.setPassword(passwordstr);
//
//            //helper.insertContact();
//        }
//
//
//    }
//
//    public void AddData() {
//
//        regsignupbtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void OnClick(View v) {
//                        boolean isInserted = myDb.insertContact(editName.getText().toString(), editPassword.getText().toString());
//
//                        if (isInserted == true) {
//                            Toast.makeText(Registration.this, "succesfully created", Toast.LENGTH_LONG).show();
//                        } else
//                            Toast.makeText(Registration.this, "error not created", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//        );
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
//
////    public void onRegistrationClick(View v)
////    {
////        if(v.getId() == R.id.Registration_signup)
////        {
////            EditText name = (EditText)findViewById(R.id.Registration_username);
////            String namestr = name.getText().toString();
////
////            EditText password = (EditText)findViewById(R.id.Registration_password);
////            String passwordstr = password.getText().toString();
////
////            EditText password2 = (EditText)findViewById(R.id.Registration_password2);
////            String password2str = password2.getText().toString();
////
////            if(!passwordstr.equals(password2str)){
////                //popup messsage passwords do not match
////                Toast pass = Toast.makeText(Registration.this,"Passwords don't match!", Toast.LENGTH_SHORT);
////                pass.show();
////            }
////
////            else{
////                //insert into database
////                Contact c = new Contact();
////                c.setName(namestr);
////                c.setPassword(passwordstr);
////
////                helper.insertContact();
////
////            }
//////            Intent i = new Intent(Registration.this, Main.class);
//////            i.putExtra("name", str);
////
////        }


