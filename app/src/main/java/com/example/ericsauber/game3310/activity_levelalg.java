package com.example.ericsauber.game3310;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class activity_levelalg extends AppCompatActivity {

    /* max variables to control gameplay and allow changes to gamedata creation class through parameters */
    int maxWrong=2;              // maximum number of incorrect answers allowed
    int maxSequences=3;          // maximum number of sequence columns that can be generated, pass to gamedata
    int maxSequenceValues=5;     // maximum number of sequence values in each column that can be generated, pass to gamedata (increasing requires layout change)
    int maxGroceryListItems=5;   // maximum number of items on a grocery list, pass to gamedata

    int curLevel=4;              // The current level, default to 4 as first 3 levels are set. display and pass to gamedata
    int curItem=0;               // The current item that is to be selected.  It will be compared to the Sequence attribute of the gamedata object
    int curWrongAnswers=0;       // The current number of wrong answers.  Will be reset with each sequence
    int curDifficulty=1;         // The current difficulty level. default to 1 unless available from user data. passed to gamedata
    int progress=0;              // The current number of series items correctly entered. each series item consists of a value from each sequence column from the same row

    GameData levelData;          // Object containing generated game play data
    Button seqBtnArray[][];      // 2-D array containing all of the button items so that they can be easily manipulated for game play
    Dialog newlvl_diag;          // Custom dialog to spash new level information up between

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelalg);
        resetActivity();
    }

    private void resetActivity(){
        initializeBtnArray();
        TextView tempText = (TextView) findViewById(R.id.txtViewLvlNum);
        tempText.setText(String.format("%d",curLevel));
        levelData = new GameData(curLevel,curDifficulty,maxSequences,maxSequenceValues,maxGroceryListItems);
        newlvl_diag=new Dialog(this,R.style.newlvl_diag);
        newlvl_diag.setContentView(R.layout.newlvl_dialogue);
        configureDialog(newlvl_diag,curLevel);
        updateProgress(0);
        updateWrongAnswers(0);
        newlvl_diag.show();

        refreshButtons();
    }

    private void updateProgress(int incProgress){
        TextView tempText = (TextView) findViewById(R.id.txtProgress);
        tempText.setText(String.format("Progress: %d of 5 .",incProgress));
    }

    private void updateWrongAnswers(int incWrong){
        TextView tempText = (TextView) findViewById(R.id.txtWrongAnswers);
        tempText.setText(String.format("Incorrect: %d of 2 .",incWrong));
    }

    private void configureDialog(final Dialog incDialog, int incLevel){
        TextView tempText = (TextView) incDialog.findViewById(R.id.txtLvlWelc);
        tempText.setText(String.format("Welcome to level %d !",incLevel));
        TextView tempText2 = (TextView) incDialog.findViewById(R.id.txtLvlPattern);
        tempText2.setText(String.format("%s",levelData.descString));
        Button contBtn = (Button) incDialog.findViewById(R.id.btnContinue);
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incDialog.dismiss();
            }
        });
        Button mainBtn = (Button) incDialog.findViewById(R.id.btnMainMenu);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incDialog.dismiss();
                Intent intent = new Intent(incDialog.getContext(),Main.class);
                startActivity(intent);
            }
        });
    }

    private void initializeBtnArray(){
        setContentView(R.layout.activity_levelalg);
        seqBtnArray = new Button[maxSequences][maxSequenceValues];
        for(int seqCounter=0;seqCounter<maxSequences;seqCounter++)
            for(int valCounter=0;valCounter<maxSequenceValues;valCounter++) {
                String btnID = String.format("btnSeq%dVal%d",seqCounter+1,valCounter+1);
                int tempID = getResources().getIdentifier(btnID,"id",getPackageName());
                Button tempBtn = (Button)findViewById(tempID);
                tempBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                            public void onClick(View v){
                        Button temp = (Button)findViewById(v.getId());
                        if(validateChoice(temp.getText().toString())) {
                            if(curItem%levelData.numSequences==levelData.numSequences-1) {
                                progress++;
                                updateProgress(progress);
                                if(progress==maxSequenceValues) sequenceSuccess();
                            }
                            curItem++;
                        }
                        else{
                            curWrongAnswers++;
                            updateWrongAnswers(curWrongAnswers);
                            if(curWrongAnswers==maxWrong) sequenceFailed();
                        }
                    }
                });
                seqBtnArray[seqCounter][valCounter] =(Button)findViewById(tempID);
            }
    }

    private void refreshButtons(){
        for(int seqCounter=0;seqCounter<maxSequences;seqCounter++)
            for(int valCounter=0;valCounter<maxSequenceValues;valCounter++){
                if(this.levelData.seqDescriptor[seqCounter][valCounter].equals("-1")) {
                    seqBtnArray[seqCounter][valCounter].setVisibility(View.INVISIBLE);
                    seqBtnArray[seqCounter][valCounter].setEnabled(false);
                }
                else{
                    seqBtnArray[seqCounter][valCounter].setVisibility(View.VISIBLE);
                    seqBtnArray[seqCounter][valCounter].setText(levelData.seqDescriptor[seqCounter][valCounter]);
                }
            }
    }

    public boolean validateChoice(String incItem){
        if(levelData.Sequences[curItem%levelData.numSequences][curItem/levelData.numSequences].equals(incItem)) return true;
        else return false;
    }

    public void gotoMain(View view) {

        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    private void sequenceSuccess(){

    }

    private void sequenceFailed(){

    }
}
