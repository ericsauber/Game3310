package com.example.ericsauber.game3310;

/**
 * Created by nohemi on 5/1/17.
 */

public class preference {
    int _id;
    int music;
    String loginname;



    public void setId(int id) {
        this._id = id;
    }

    public int getId() {
        return _id;
    }

    public void setMusic(int i) {
        this.music = i;
    }
    public int getMusic() {
        return music;
    }
    public void setLogindex(String i) {
        this.loginname = i;
    }
    public String getLogindex() {
        return loginname;
    }


}
