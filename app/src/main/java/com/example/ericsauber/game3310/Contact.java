package com.example.ericsauber.game3310;

/**
 * Created by nohemi on 4/25/17.
 */

public class Contact {
    private int _id;
    private String _name, _password;


    public Contact(){}

    public void setId(int id) {
        this._id = id;
    }
    public int getId(){
        return _id;
    }



    public Contact(String Name, String Password) {
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
