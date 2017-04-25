package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Level1e extends AppCompatActivity {
int score;

    EditText typeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1e);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);



    }

    public void gotoLevel1f(View view) {
        typeText =(EditText) findViewById(R.id.Level1e_x);
        String word = typeText.getText().toString();
        if (word.equals("1"))
        {
            Intent intent = new Intent(this, Level1f.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
        else
        {
            typeText.setHint("Type the Anwser");
        }
    }
}
