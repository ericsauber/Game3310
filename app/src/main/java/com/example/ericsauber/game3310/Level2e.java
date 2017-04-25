package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Level2e extends AppCompatActivity {
int score;
    EditText typeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2e);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
    }

    public void gotoLevel2f(View view) {
        typeText =(EditText) findViewById(R.id.editText);
        String word = typeText.getText().toString();
        if(word.equals("20")) {
            Intent intent = new Intent(this, Level2f.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
        else
        {
            typeText.setHint("Type the Answer");
        }
    }
}
