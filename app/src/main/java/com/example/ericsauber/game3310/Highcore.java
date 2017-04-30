package com.example.ericsauber.game3310;

/**
 * Created by nohemi on 4/29/17.
 */

public class Highcore {

    private int HI_id, HI_score;
    String HI_name;

    public Highcore(){}

    public Highcore(int id, int high, String name) {
        this.HI_id = id;
        this.HI_score= high;
        this.HI_name = name;
    }
    public void setHI_id(int id) {
        this.HI_id = id;
    }
    public int getHI_id(){
        return HI_id;
    }
    public void setHI_score(int score) {
        this.HI_score = score;
    }
    public int getHI_score(){
        return HI_score;
    }
    public void setHI_name(String name) {
        this.HI_name = name;
    }
    public String getHI_Name(){
        return HI_name;
    }

}
