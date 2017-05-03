package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Level3e extends AppCompatActivity {

    int score;
    int lives;
    int level = 3;
    EditText typeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3e);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        lives = extras.getIntExtra("lives", 1);

    }

    public void gotoLevel3f(View view) {

        typeView = (EditText) findViewById(R.id.editText2);
        String word = typeView.getText().toString();

        if(word.equals("13")) {

            Intent intent = new Intent(this, Level3f.class);
            intent.putExtra("score", score);
            intent.putExtra("lives", lives);
            startActivity(intent);
            finish();
        }
        else if(word.equals(""))
        {

        }
        else {

            Intent intent = new Intent(this, Wrong.class);
            intent.putExtra("score", score);
            intent.putExtra("level", level);
            intent.putExtra("lives", lives);
            startActivity(intent);
            finish();
        }
    }
}
