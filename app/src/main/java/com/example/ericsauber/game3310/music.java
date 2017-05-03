//package com.example.ericsauber.game3310;
//
//import android.media.MediaPlayer;
//import android.widget.Toast;
//
///**
// * Created by nohemi on 5/1/17.
// */
//public class music{
//
//
//    music(){
//    }
//    public void musiconoff(int x){
//        MediaPlayer ring = MediaPlayer.create(this,R.raw.mmsong);
//        if(x==1){
//            Toast pass = Toast.makeText(this, "Inside turn on", Toast.LENGTH_LONG);
//            pass.show();
//            ring.start();
//            ring.setLooping(true);
//        }else{
//            Toast pass = Toast.makeText(this, "inside turn off", Toast.LENGTH_LONG);
//            pass.show();
//            ring.release();
//
//        }
//    }
//
//}