package com.example.ericsauber.game3310;

/**
 * Created by nohemi on 4/25/17.
 */
//loginstatus = currrent player logged in = 1, else =0
//this just creates the contact information that goes into database
public class Contact {
    private int _id, loginStatus;
    private String _name, _password;


    public Contact(){}

    public void setId(int id) {
        this._id = id;
    }
    public int getId(){
        return _id;
    }
    public void setLoginStatus(int i) {
        this.loginStatus = i;
    }
    public int getLoginStatus(){
        return loginStatus;

    }
    public int getlogedId(){
        return _id;
    }

    public Contact(String Name, String Password, int loginStatus) {
        this._name = Name;
        this._password = Password;
    }

    public void setName(String name) {
        this._name = name;
    }
    public String getName(){
        return _name;
    }

    public void setPassword(String password) {
        this._password = password;
    }
    public String getPassword(){
        return _password;
    }

}
