package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Wrong extends AppCompatActivity {

    int level;
    int lives;
    int score;
    int index;
    MyDBHandler dbHandler;

    TextView livesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);

        Intent extras = getIntent();
        score = extras.getIntExtra("score",1);
        level = extras.getIntExtra("level", 1);
        lives = extras.getIntExtra("lives", 1);

        lives--;

        if (lives == 0) {
//            dbHandler = new MyDBHandler(this, null, null, 1);
//            index = dbHandler.searchHighScore(score);
//            if(index>1){
//                Cursor setData = dbHandler
//            }
            Intent intent = new Intent(this, GameOver.class);
            startActivity(intent);
        }

        TextView scoreView = (TextView) findViewById(R.id.wrong_text);
        scoreView.setText("Lives Remaining : " + lives);
    }

    public void tryAgain(View view) {

        if (level == 1) {
            Intent intent = new Intent(this, Level1a.class);

            intent.putExtra("lives", lives);
            startActivity(intent);
        }
        if (level == 2) {
            Intent intent = new Intent(this, Level2a.class);
            startActivity(intent);
        }
        if (level == 3) {
            Intent intent = new Intent(this, Level3a.class);
            startActivity(intent);
        }

    }
}
