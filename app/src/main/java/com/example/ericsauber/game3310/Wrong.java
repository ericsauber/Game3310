package com.example.ericsauber.game3310;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Wrong extends AppCompatActivity {

    int level;
    int lives;
    int score;
    int index,count,min;
    MyDBHandler dbHandler;
    String name;

    TextView livesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        dbHandler = new MyDBHandler(this, null, null, 1);
        Intent extras = getIntent();
        score = extras.getIntExtra("score",1);
        level = extras.getIntExtra("level", 1);
        lives = extras.getIntExtra("lives", 1);

        lives--;

        if (lives == 0) {
            count = dbHandler.getCountHighScore();
            Highcore highscore = new Highcore();
            Cursor recordSet = dbHandler.getCursorPref();
            recordSet.moveToFirst();
            name = recordSet.getString(2);
            highscore.setHI_name(name);
            highscore.setHI_score(score);

            if(count<=4) {
                //insert
                dbHandler.addHighScore(highscore);

            }else{
                index = dbHandler.getindexMIN(score);

                if(index >= 1){

                   dbHandler.updateHighScore(highscore,index);
                    Toast pass = Toast.makeText(Wrong.this, " Congraulations your score is on the top 5!", Toast.LENGTH_SHORT);
                    pass.show();
                    pass=Toast.makeText(Wrong.this, " score was" + score+  "", Toast.LENGTH_SHORT);
                    pass.show();
                }
                else{

               }
            }

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
