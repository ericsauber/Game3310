package com.example.ericsauber.game3310;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;



public class activity_levelalg extends AppCompatActivity {


    int score;
    int lives;
    GameData levelData;          // Object containing generated game play data
    Button seqBtnArray[][];      // 2-D array containing all of the button items so that they can be easily manipulated for game play

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelalg);
        startLevel();
    }


    /*first level segment
      this will display the level start dialog and introduce the memx value
     */
    private void startLevel(){

        /* max variables to control gameplay and allow changes to gamedata creation class through parameters */
        int maxWrong=2;              // maximum number of incorrect answers allowed
        int maxSequences=3;          // maximum number of sequence columns that can be generated, pass to gamedata
        int maxSequenceValues=5;     // maximum number of sequence values in each column that can be generated, pass to gamedata (increasing requires layout change)
        int maxGroceryListItems=5;   // maximum number of items on a grocery list, pass to gamedata


        Dialog new_diag;             // Custom dialog to spash new level information up between

        levelData = new GameData();  //create new gamedatainstance for current level
        levelData.setTotalScore(score);

        levelData.setMaxValues(maxSequences,maxSequenceValues,maxGroceryListItems,maxWrong);
        new_diag=new Dialog(this,R.style.newlvl_diag);
        new_diag.setContentView(R.layout.newlvl_dialogue);
        configureNewLevelDialog(new_diag);
        new_diag.show();
    }

    private void configureNewLevelDialog(final Dialog incDialog) {
        TextView tempText = (TextView) incDialog.findViewById(R.id.txtLevelDisplay);
        TextView tempText2 = (TextView) incDialog.findViewById(R.id.txtSeqDisplay);
        EditText tempEdit = (EditText) incDialog.findViewById(R.id.editTxtMemX);
        Button contBtn = (Button) incDialog.findViewById(R.id.btnContinue);
        Button mainBtn = (Button) incDialog.findViewById(R.id.btnMainMenu);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incDialog.dismiss();
                Intent intent = new Intent(incDialog.getContext(), Main.class);
                startActivity(intent);
            }
        });
        tempText.setText(String.format(Locale.getDefault(), "Level %d", levelData.getCurLevel()));
        tempText2.setText(String.format(Locale.getDefault(), "X=%d", levelData.getMemX()));
        tempEdit.setVisibility(View.INVISIBLE);
        tempEdit.setEnabled(false);

        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelData.generateSequenceData();
                levelData.iterateLevelSegment();
                Dialog seq_diag;
                seq_diag = new Dialog(v.getContext(), R.style.newlvl_diag);
                seq_diag.setContentView(R.layout.sequence_dialogue);
                configureSequenceDialog(seq_diag);
                seq_diag.show();
                incDialog.dismiss();
            }
        });
    }

    /**********************************************/
    /* configuration methods for the memXdialogues*/
    /**********************************************/
    /*configuration methods for the dialogue created when sequence completed successfully*/
    /*and segment is 3 or 5 */

    private void configureChangeMemXDialog(final Dialog incDialog) {
        levelData.iterateLevelSegment();

        TextView tempText = (TextView) incDialog.findViewById(R.id.txtLevelDisplay);
        tempText.setVisibility(View.INVISIBLE);

        TextView tempText2 = (TextView) incDialog.findViewById(R.id.txtSeqDisplay);
        tempText2.setText(levelData.getMemXModDesc());

        EditText tempEdit = (EditText) incDialog.findViewById(R.id.editTxtMemX);
        tempEdit.setVisibility(View.INVISIBLE);

        if (tempText2.getText().equals("None")) tempText2.setVisibility(View.INVISIBLE);

        Button mainBtn = (Button) incDialog.findViewById(R.id.btnMainMenu);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incDialog.dismiss();
                Intent intent = new Intent(incDialog.getContext(), Main.class);
                startActivity(intent);
            }
        });

        Button contBtn = (Button) incDialog.findViewById(R.id.btnContinue);
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelData.generateSequenceData();
                Dialog seq_diag;
                seq_diag = new Dialog(v.getContext(), R.style.newlvl_diag);
                seq_diag.setContentView(R.layout.sequence_dialogue);
                configureSequenceDialog(seq_diag);
                seq_diag.show();
                incDialog.dismiss();
            }
        });
    }

    /*configuration methods for the dialogue created when sequence completed successfully*/
    /*and segment is 7*/

    private void configureSolveMemXDialog(final Dialog incDialog) {
        levelData.iterateLevelSegment();

        TextView tempText = (TextView) incDialog.findViewById(R.id.txtLevelDisplay);
        tempText.setVisibility(View.INVISIBLE);

        TextView tempText2 = (TextView) incDialog.findViewById(R.id.txtSeqDisplay);
        tempText2.setVisibility(View.VISIBLE);
        tempText2.setText(String.format(Locale.getDefault(),"Enter the X into the box below:"));

        EditText tempEdit = (EditText) incDialog.findViewById(R.id.editTxtMemX);
        tempEdit.setVisibility(View.VISIBLE);

        Button mainBtn = (Button) incDialog.findViewById(R.id.btnMainMenu);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incDialog.dismiss();
                Intent intent = new Intent(incDialog.getContext(), Main.class);
                startActivity(intent);
            }
        });

        Button contBtn = (Button) incDialog.findViewById(R.id.btnContinue);
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText temp = (EditText) incDialog.findViewById(R.id.editTxtMemX);
                String incString = temp.getText().toString();
                if (incString.equals(Integer.toString(levelData.getFinalMemX()))) {
                    ((TextView) incDialog.findViewById(R.id.txtLevelDisplay)).setText(String.format(Locale.getDefault(),"Correct!"));
                    incDialog.findViewById(R.id.txtLevelDisplay).setVisibility(View.VISIBLE);
                    try {
                        Thread.sleep(1000); //1000 milliseconds is one second.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    levelData.generateSequenceData();
                    Dialog seq_diag;
                    seq_diag = new Dialog(v.getContext(), R.style.newlvl_diag);
                    seq_diag.setContentView(R.layout.sequence_dialogue);
                    configureSequenceDialog(seq_diag);
                    seq_diag.show();
                    incDialog.dismiss();
                }
                else{
                    ((TextView) incDialog.findViewById(R.id.txtLevelDisplay)).setText(String.format(Locale.getDefault(),"Oh no! That's not correct."));
                    ((TextView) incDialog.findViewById(R.id.txtLevelDisplay)).setVisibility(View.VISIBLE);
                    ((EditText) incDialog.findViewById(R.id.editTxtMemX)).setText(String.format(Locale.getDefault(),""));
                }
            }
        });
    }

    /* method for the configuration of the dialogue created after the newlevel screen*/
    /*segment number should be 2*/
    private void configureSequenceDialog(final Dialog incDialog){
        levelData.iterateLevelSegment();
        initializeBtnArray(incDialog);
        TextView tempText = (TextView) incDialog.findViewById(R.id.txtSequenceInstruct);
        tempText.setText(String.format(Locale.getDefault(),"%s",levelData.descString));
        updateProgress(incDialog,levelData.getProgress());
        updateWrongAnswers(incDialog);
        updateLevelScore(incDialog);
        refreshButtons();
    }

    /* method to initialize the btn array used by the sequence dialogue*/
    private void initializeBtnArray(final Dialog incDialog){
        seqBtnArray = new Button[levelData.getMaxSequences()][levelData.getMaxSequenceValues()];
        for(int seqCounter=0;seqCounter<levelData.getMaxSequences();seqCounter++)
            for(int valCounter=0;valCounter<levelData.getMaxSequenceValues();valCounter++) {
                String btnID = String.format(Locale.getDefault(),"btnSeq%dVal%d",seqCounter+1,valCounter+1);
                int tempID = getResources().getIdentifier(btnID,"id",getPackageName());
                Button tempBtn = (Button)incDialog.findViewById(tempID);
                tempBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Button temp = (Button)incDialog.findViewById(v.getId());
                        if(levelData.validateChoice(temp.getText().toString())) {
                            switch(levelData.updateSequenceSegmentStatus()){
                                case(1): {updateSegmentScore(incDialog);break;}
                                case(4): {updateProgress(incDialog, levelData.getProgress());updateSegmentScore(incDialog); break;}
                                case(5): {
                                    updateSegmentScore(incDialog);
                                    Dialog seqSuccessDiag;             // Custom dialog to display sequence success information
                                    seqSuccessDiag=new Dialog(incDialog.getContext(),R.style.newlvl_diag);
                                    seqSuccessDiag.setContentView(R.layout.sequencesuccessdialog);
                                    if(levelData.getLevelSegment()<6) configureSequenceSuccessMid(seqSuccessDiag);
                                    else configureSequenceSuccessFinal(seqSuccessDiag);
                                    seqSuccessDiag.show();
                                    incDialog.dismiss();
                                    break;
                                }
                            }
                        }
                        else{
                            if(levelData.iterateWrongAnswers()) sequenceFailed(incDialog);
                            else configureWrongAnswersDiag(incDialog);
                            updateWrongAnswers(incDialog);
                        }
                    }
                });
                seqBtnArray[seqCounter][valCounter] =(Button)incDialog.findViewById(tempID);
            }
    }

    /*method to refresh button text to the values in the sequence descriptor array*/
    /*also turns off visibility for any button correlating to a -1 value in       */
    /*sequence descriptor array*/
    private void refreshButtons(){
        for(int seqCounter=0;seqCounter<levelData.getMaxSequences();seqCounter++)
            for (int valCounter = 0; valCounter < levelData.getMaxSequenceValues(); valCounter++) {
                if (this.levelData.seqDescriptor[seqCounter][valCounter].equals("-1")) {
                    seqBtnArray[seqCounter][valCounter].setVisibility(View.INVISIBLE);
                    seqBtnArray[seqCounter][valCounter].setEnabled(false);
                } else {
                    seqBtnArray[seqCounter][valCounter].setVisibility(View.VISIBLE);
                    seqBtnArray[seqCounter][valCounter].setText(levelData.seqDescriptor[seqCounter][valCounter]);
                }
            }
    }

    /*method to update the progress text field on sequence dialogue*/
    private void updateProgress(final Dialog incDialog,int incProgress){
        TextView tempText = (TextView) incDialog.findViewById(R.id.txtProgress);
        tempText.setAlpha((float)0.99);
        switch(levelData.getProgress()){
            case(0): {tempText.setBackgroundResource(R.drawable.cheese5);break;}
            case(1): {tempText.setBackgroundResource(R.drawable.cheese4);break;}
            case(2): {tempText.setBackgroundResource(R.drawable.cheese3);break;}
            case(3): {tempText.setBackgroundResource(R.drawable.cheese2);break;}
            case(4): {tempText.setBackgroundResource(R.drawable.cheese1);break;}
        }
        tempText.setText(String.format(Locale.getDefault(),"Progress: %d of 5 .",levelData.getProgress()));
    }

    /*method to update the wrong answers text field on sequence dialogue*/
    private void updateWrongAnswers(final Dialog incDialog){
        TextView tempText = (TextView) incDialog.findViewById(R.id.txtWrongAnswers);
        tempText.setAlpha((float)0.99);
        if(levelData.getCurWrongAnswers()==0) tempText.setBackgroundResource(R.drawable.wrongtwo);
        if(levelData.getCurWrongAnswers()==1) tempText.setBackgroundResource(R.drawable.wrongone);
        tempText.setText(String.format(Locale.getDefault(),"Incorrect:%d of 2.",levelData.getCurWrongAnswers()));
    }

    /*method to display the wrong answers dialoge for the first wrong answer*/
    private void configureWrongAnswersDiag(final Dialog incDialog){
        final Dialog wrong_diag;
        wrong_diag = new Dialog(incDialog.getContext(), R.style.newlvl_diag);
        wrong_diag.setContentView(R.layout.wronganswer_dialogue);
        TextView message = (TextView) wrong_diag.findViewById(R.id.txtMessage);
        message.setText("Oops!  You slipped up and made some noise.  The cat is on his way.  You have one more try to complete the sequence in the right order or the cat will find you.  Click 'Try Again' to return to your sequence.");
        Button tryAgain = (Button) wrong_diag.findViewById(R.id.btnTryAgain);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong_diag.dismiss();
            }
        });
        Button mainMenu = (Button) wrong_diag.findViewById(R.id.btnMainMenu);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incDialog.dismiss();
                Intent intent = new Intent(incDialog.getContext(), Main.class);
                startActivity(intent);
            }
        });

        wrong_diag.show();
    }

    /*method to update the segment score text field on sequence dialogue*/
    private void updateSegmentScore(final Dialog incDialog){
        TextView tempText = (TextView) incDialog.findViewById(R.id.txtSegmentScore);
        tempText.setText(String.format(Locale.getDefault(),"Segment Score: %d",levelData.getSegmentScore()));
    }

    /*method to update the level score text field on sequence dialogue*/
    private void updateLevelScore(final Dialog incDialog){
        TextView tempText = (TextView) incDialog.findViewById(R.id.txtLevelScore);
        tempText.setText(String.format(Locale.getDefault(),"Total Score: %d",levelData.getLevelScore()));
    }

    /*Methods to configure dialogues for displaying congratulations*/
    /*and segment scores after success sequence completion*/

    /*method for the configuration of the dialogue leading to a memx display screen*/
    private void configureSequenceSuccessMid(final Dialog incDialog){
        TextView tempText2 = (TextView) incDialog.findViewById(R.id.txtSuccess2);
        tempText2.setText(String.format(Locale.getDefault(),"You got all the cheese from that round!\nYou now have earned %d points on this level.\nClick below to scurry on to the next step.",levelData.getLevelScore()));
        Button contBtn = (Button) incDialog.findViewById(R.id.btnContinue);
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog changeMemX_diag=new Dialog(incDialog.getContext(),R.style.newlvl_diag);
                changeMemX_diag.setContentView(R.layout.newlvl_dialogue);
                configureChangeMemXDialog(changeMemX_diag);
                changeMemX_diag.show();
                incDialog.dismiss();
            }
        });
    }

    /*method for the configuration of the dialoge leading to the memx solution/final screen*/
    private void configureSequenceSuccessFinal(final Dialog incDialog) {
        TextView tempText2 = (TextView) incDialog.findViewById(R.id.txtSuccess2);
        tempText2.setText(String.format(Locale.getDefault(), "You got all the cheese from that round!\nYou now have earned %d points on this level.\nClick below to scurry on to the final step.", levelData.getLevelScore()));
        Button contBtn = (Button) incDialog.findViewById(R.id.btnContinue);
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog solveMemX_diag = new Dialog(incDialog.getContext(), R.style.newlvl_diag);
                solveMemX_diag.setContentView(R.layout.newlvl_dialogue);
                configureSolveMemXDialog(solveMemX_diag);
                solveMemX_diag.show();
                incDialog.dismiss();
            }
        });
    }


    private void sequenceFailed(final Dialog incDialog){final Dialog wrong_diag;
        wrong_diag = new Dialog(incDialog.getContext(), R.style.newlvl_diag);
        wrong_diag.setContentView(R.layout.wronganswer_dialogue);
        TextView message = (TextView) wrong_diag.findViewById(R.id.txtMessage);
        message.setText("Squeak!! Your second misstep caused and even louder racket that led the cat right to you!  That's all the tries for this round.  Click the Main Menu button below to return to the main screen.");
        Button tryAgain = (Button) wrong_diag.findViewById(R.id.btnTryAgain);
        tryAgain.setVisibility(View.INVISIBLE);
        Button mainMenu = (Button) wrong_diag.findViewById(R.id.btnMainMenu);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incDialog.dismiss();
                score = levelData.getTotalScore();
                Intent intent = new Intent(incDialog.getContext(), GameOver.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });
        incDialog.dismiss();
        wrong_diag.show();
    }

    /*general method for main menu buttons*/
    public void gotoMain(View view) {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }


}