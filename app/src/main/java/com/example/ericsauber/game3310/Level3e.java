package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Level3e extends AppCompatActivity {
int score;
    EditText typeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3e);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
    }

    public void gotoLevel3f(View view) {
    typeView = (EditText) findViewById(R.id.editText2);
        String word = typeView.getText().toString();
        if(word.equals("13")) {
            Intent intent = new Intent(this, Level3f.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
        else
        {
            typeView.setHint("Type the Number.");
        }
    }
}
