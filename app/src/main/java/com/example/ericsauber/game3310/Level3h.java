package com.example.ericsauber.game3310;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Level3h extends AppCompatActivity {
int score;
    EditText textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3h);
        Intent extras = getIntent();
        score = extras.getIntExtra("score", 1);
        int score = extras.getIntExtra("score", 1);


    }

    public void gotoLevel3i(View view) {
        textView = (EditText)findViewById(R.id.Level3h_x);
        String word = textView.getText().toString();
        if(word.equals("4")) {
            Intent intent = new Intent(this, Level3i.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
        else
        {
            textView.setHint("Enter the Number.");
        }
    }
}
