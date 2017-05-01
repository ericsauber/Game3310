package com.example.ericsauber.game3310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Michael on 4/22/2017.
 */

class GameData {
    private int maxSequences=3;
    private int maxSequenceValues=5;
    private int sequenceSeedOffset=3;
    private int maxGroceryItems=5;
    private int maxXMods=2;
    private int maxWrong=2;
    private int segmentPointValue=10;
    private int numOfGroceryItems=6;
    private String groceryItems[]= {"Apples","Bananas","Bread","Cheese","Dozen Eggs","Milk"};


    String Sequences[][];
    String descString =""; //Depreciated long description generation initialized with "In level";
    String seqDescriptor[][];
    private int numSequences;
    private int numGroceries;
    private int seqCountVals[];
    int groceryList[][];
    private int memX;
    int xMods[][];
    public String troubleString;

    // Game play variables
    private int curLevel=4;              // The current level, default to 4 as first 3 levels are set. display and pass to gamedata
    private int curItem=0;               // The current item that is to be selected.  It will be compared to the Sequence attribute of the gamedata object
    private int curWrongAnswers=0;       // The current number of wrong answers.  Will be reset with each sequence
    private int curDifficulty=1;         // The current difficulty level. default to 1 unless available from user data. passed to gamedata
    private int progress=0;              // The current number of series items correctly entered. each series item consists of a value from each sequence column from the same row
    private int totalScore=0;            // The total game score
    private int levelScore=0;
    private int segmentScore=0;          // Score for the current level
    private int levelSegment=0;          // What segment of the level is the user currently experiencing (there are 7 set segments for each level 4+


    /*Game data contructor and overloads*/

    GameData(int incDifficulty, int incLevel){
        if (!(incLevel==curLevel)) setCurLevel(incLevel);
        if (!(incDifficulty==curDifficulty)) setCurDifficulty(incDifficulty);
        newLevel();
    }

    GameData(int incDifficulty){
        if (!(incDifficulty==curDifficulty)) setCurDifficulty(incDifficulty);
        newLevel();
    }


    GameData()
    {
        newLevel();
    }

    /*This section contains methods pertaining to the global level values*/

    public void setMaxValues(int incMaxSequences, int incMaxSequenceValues, int incMaxGroceryItems, int incMaxWrong) {
        if (!(incMaxSequences == maxSequences)) setMaxSequences(incMaxSequenceValues);
        if (!(incMaxSequenceValues == maxSequenceValues)) setMaxSequenceValues(incMaxSequenceValues);
        if (!(incMaxGroceryItems == maxGroceryItems)) setMaxGroceryItems(incMaxGroceryItems);
        if (!(incMaxWrong == maxWrong)) setMaxWrong(incMaxWrong);

    }

    /* This section will contain those methods pertaining to the level scope processes*/

    /*simple method to contain calls for initialize and generation of level scope data*/
    public void newLevel(){
        initializeLevelData();
        generateLevelData();
    }

    /*level scope data initialization. This will initialize grocery list,levelScore, and memx items*/
    private void initializeLevelData(){

        groceryList = new int[maxGroceryItems][2];
        levelScore=0;

        for (int groceryCounter = 0; groceryCounter < maxGroceryItems; groceryCounter++) {
            setGroceryListItem(groceryCounter, -1, -1);
        }

        setMemX(-1);

        xMods=new int[maxXMods][3];
        for(int modCounter=0;modCounter<maxXMods;modCounter++){
            setXMod(modCounter,-1,-1);
        }
    }

    /*level scope data generation. This method generates data to populate the grocery list and memx attributes*/
    private void generateLevelData(){
        Random rn = new Random();
        setMemX(rn.nextInt(5*(this.curLevel-1+this.curDifficulty))+1);
        for(int modCounter=0;modCounter<maxXMods;modCounter++){
            int tempMod = rn.nextInt(99)+1;
            if(tempMod%25>0) tempMod=(tempMod/25)+1; else tempMod=tempMod/25;
            setXMod(modCounter,tempMod,rn.nextInt(10)+1);
        }
        int curGroceryItems=rn.nextInt(maxGroceryItems-3)+1;
        for(int grocCounter=0;grocCounter<curGroceryItems;grocCounter++){
            if(!insertGroceryItem(generateGroceryItem())) grocCounter--;
        }
    }

    /*sequence scope methods*/

    /*sequence leve data initialization. This will initialize:
       Sequences array
       seqDescripter array
       curItem
       progress
       curWrongAnswers
     */
    private void initializeSequenceData(){
        curItem=0;
        progress=0;
        curWrongAnswers=0;
        segmentScore=0;
        descString ="";
        Sequences = new String[maxSequences][maxSequenceValues+sequenceSeedOffset];
        seqDescriptor = new String[maxSequences][maxSequenceValues+sequenceSeedOffset];
        seqCountVals = new int[maxSequences];

        for(int sequenceCounter=0;sequenceCounter<maxSequences;sequenceCounter++) {
            setSeqCounts(sequenceCounter,0);
            for (int valCounter = 0; valCounter < maxSequenceValues+sequenceSeedOffset; valCounter++) {
                setSequenceValue(sequenceCounter, valCounter, String.format(Locale.getDefault(),"%d", -1));
                setSequenceDescriptorValue(sequenceCounter, valCounter, String.format(Locale.getDefault(),"%d", -1));
            }
        }
    }

    public void generateSequenceData(){

        initializeSequenceData();

        Random rn = new Random();
        int numOfSequences = this.curLevel/5+2;
        int lastSeqType=-1;
        if (numOfSequences>maxSequences) numOfSequences=maxSequences;
        this.numSequences=numOfSequences;

        //descString = String.format("%s %d you will need to...\n",descString,incLevel);  //Depreciated long descritpion generation
        for(int sequenceCounter=0;sequenceCounter<numOfSequences;sequenceCounter++) {
            int seqType = rn.nextInt(2) + 1;
            while (seqType == lastSeqType) seqType = rn.nextInt(2) + 1;
            lastSeqType = seqType;
            int seqCur = 0;
            switch (seqType) {
                case (1): {
                    setSeqCounts(sequenceCounter, rn.nextInt(1 + ((this.curLevel/4)+this.levelSegment)) + 1);
                    seqCur = rn.nextInt(4) + 1;
                    //Depreciated long description generation
                    //descString = String.format("%s Count through the digits 1,2,3,...9 by %d's, beginning at %s.\n",descString,seqCountVals[sequenceCounter],Character.toString((char) (seqCur + 48)));
                    for (int valCounter = 0; valCounter < maxSequenceValues + sequenceSeedOffset; valCounter++) {
                        setSequenceValue(sequenceCounter, valCounter, Character.toString((char) (seqCur + 48)));
                        addSeqDescriptor(sequenceCounter, Character.toString((char) (seqCur + 48)));
                        if ((seqCur + this.seqCountVals[sequenceCounter]) > 9)
                            seqCur = (seqCur + this.seqCountVals[sequenceCounter]) % 9;
                        else seqCur += this.seqCountVals[sequenceCounter];
                    }
                    break;
                }
                case (2): {
                    setSeqCounts(sequenceCounter, rn.nextInt(3 + (((this.curLevel/4)+this.levelSegment) % 10)) + 1);
                    seqCur = rn.nextInt(12) + 1;
                    //Depreciated long description generation
                    //descString = String.format("%s Count through the characters a,b,c,...z for each %d'th character, beginning with %s.\n",descString,seqCountVals[sequenceCounter],Character.toString((char) (seqCur + 64)));
                    for (int valCounter = 0; valCounter < maxSequenceValues + sequenceSeedOffset; valCounter++) {
                        setSequenceValue(sequenceCounter, valCounter, Character.toString((char) (seqCur + 64)));
                        addSeqDescriptor(sequenceCounter, Character.toString((char) (seqCur + 64)));
                        if ((seqCur + this.seqCountVals[sequenceCounter]) > 26)
                            seqCur = (seqCur + this.seqCountVals[sequenceCounter]) % 26;
                        else seqCur += this.seqCountVals[sequenceCounter];
                    }
                    break;
                }
                case (3): {
                    setSeqCounts(sequenceCounter, 1);
                    seqCur = rn.nextInt(1) + 1;
                    //Depreciated long description generation
                    //descString = String.format("%s Count through the set True,False, starting at ",descString);
                    if (seqCur % 2 == 0)
                        String.format(Locale.getDefault(), "%s %s.\n", descString, "False");
                    else String.format(Locale.getDefault(), "%s %s.\n", descString, "True");
                    for (int valCounter = 0; valCounter < maxSequenceValues + sequenceSeedOffset; valCounter++) {
                        if (seqCur % 2 == 0) {
                            setSequenceValue(sequenceCounter, valCounter, "False");
                            addSeqDescriptor(sequenceCounter, "False");
                        } else {
                            setSequenceValue(sequenceCounter, valCounter, "True");
                            addSeqDescriptor(sequenceCounter, "True");
                        }
                        seqCur++;
                    }
                    break;
                }
            }
        }

        descString = String.format(Locale.getDefault(),"%s (",descString);
        for(int valCounter=0;valCounter<3;valCounter++) {
            for (int seqCounter = 0; seqCounter < numOfSequences; seqCounter++) descString = String.format("%s%s",descString,Sequences[seqCounter][valCounter]);
            if(valCounter<sequenceSeedOffset-1) descString = String.format(Locale.getDefault(),"%s,",descString);
        }
        descString = String.format(Locale.getDefault(),"%s)",descString);


        for(int seqCounter=0; seqCounter<numOfSequences;seqCounter++)
            for(int valCounter=0;valCounter<maxSequenceValues+sequenceSeedOffset;valCounter++) {
                if (valCounter < 5 && !seqDescriptor[seqCounter][valCounter + sequenceSeedOffset].equals("-1"))
                    seqDescriptor[seqCounter][valCounter] = seqDescriptor[seqCounter][valCounter + sequenceSeedOffset];
                if (valCounter > 4) seqDescriptor[seqCounter][valCounter] = "-1";
            }


        for(int seqCounter=0;seqCounter<maxSequences;seqCounter++) reorderSeqDescriptors(seqCounter);
    }


    private void troubleString(String incString){
        this.troubleString = String.format(Locale.getDefault(),"%s%s",troubleString,incString);
    }



    private void setSequenceValue(int sequenceNum, int valueNum, String incValue){
        this.Sequences[sequenceNum][valueNum]=incValue;
    }

    private void setSequenceDescriptorValue(int sequenceNum, int valueNum, String incValue){
        this.seqDescriptor[sequenceNum][valueNum]=incValue;
    }

    private void addSeqDescriptor(int incSequence,String incValue){
        Boolean valInSequence = false;
        int firstOpenValue = 0;
        for(int valCounter=0;valCounter<maxSequenceValues+sequenceSeedOffset;valCounter++){
            if(seqDescriptor[incSequence][valCounter].equals(incValue)){
                valInSequence=true;
            }
            if(seqDescriptor[incSequence][valCounter].equals("-1")){
                firstOpenValue=valCounter;
                break;
            }
        }
        if(!valInSequence && firstOpenValue<maxSequenceValues+sequenceSeedOffset)
            seqDescriptor[incSequence][firstOpenValue]=incValue;
    }

    private void reorderSeqDescriptors(int incCounter){
        int curVal=0;
        String tempDesc[];

        while(curVal<maxSequenceValues) {
            if(seqDescriptor[incCounter][curVal].equals("-1")) break;
            curVal++;
        }
        tempDesc = new String[curVal];
        for(int valCounter=0;valCounter<curVal;valCounter++) tempDesc[valCounter]=seqDescriptor[incCounter][valCounter];
        //ArrayList<String> descList = new ArrayList<String>(Arrays.asList(tempDesc));
        Collections.shuffle(Arrays.asList(tempDesc));
        for(int valCounter=0;valCounter<curVal;valCounter++) seqDescriptor[incCounter][valCounter]=tempDesc[valCounter];

    }

    private void setMemX(int incMemX){ this.memX=incMemX;}

    private void setGroceryListItem(int incItemNum, int incItemType, int incItemQuantity){
        this.groceryList[incItemNum][0]=incItemType;
        this.groceryList[incItemNum][1]=incItemQuantity;
    }

    private void setXMod(int modNum, int incModOperator, int incModOperand){
        int startX;
        this.xMods[modNum][0]=incModOperator;
        this.xMods[modNum][1]=incModOperand;
        if(modNum==0) startX=memX; else startX=this.xMods[modNum-1][2];
        switch(incModOperator){
            case(-1): {this.xMods[modNum][2]=-1; break;}
            case(1): {this.xMods[modNum][2]=startX+incModOperand;break;}
            case(2): {this.xMods[modNum][2]=startX-incModOperand;break;}
            case(3): {this.xMods[modNum][2]=startX*incModOperand;break;}
            case(4): {this.xMods[modNum][2]=startX/incModOperand;break;}
        }
    }

    private void setSeqCounts(int seqNum, int incCountValue){
        this.seqCountVals[seqNum]=incCountValue;
    }

    private boolean insertGroceryItem(int incGroceryItem[]){
        for(int itemCounter=0;itemCounter<maxGroceryItems;itemCounter++) {
            if (groceryList[itemCounter][0] > -1) {
                if (groceryList[itemCounter][0] == incGroceryItem[0]) {
                    if (groceryList[itemCounter][1] > 0) {
                        groceryList[itemCounter][1] += incGroceryItem[1];
                        return true;
                    } else return false;
                }
            }
            else {
                groceryList[itemCounter] = incGroceryItem;
                return true;
            }
        }
      return false;
    }

    private int []  generateGroceryItem(){
        Random rn = new Random();
       int newGroceryItem[] = {0,0};
        newGroceryItem[0]=rn.nextInt(numOfGroceryItems);
        if(groceryItems[newGroceryItem[0]].endsWith("s"))
            newGroceryItem[1]=rn.nextInt(5)+1;
        else newGroceryItem[1]=0;

       return newGroceryItem;
    }

    public boolean validateChoice(String incChoice){
        return this.Sequences[curItem%numSequences][(curItem/numSequences)+sequenceSeedOffset].equals(incChoice);
    }

    public int updateSequenceSegmentStatus(){
        changeSegmentScore(segmentPointValue);  //add standard point value to segment score
        if (checkForProgress()) {      //checks to see if series progress was made
            if (iterateProgress()) {   //iterate progress; true if progress was 5
                segmentToLevel();    //adds segment score to level score and clears segment score
                return 5;            //return value indicating end of segment progress was made
            }
            iterateCurItem();
            return 4;                 //return value for progress made but segment not complete
        }
        iterateCurItem();
        return 1;                     //return value for no series progress;
    }

    public void changeSegmentScore(int incScoreChange){
        this.segmentScore+=incScoreChange;
    }

    public void clearSegmentScore(){
        this.segmentScore=0;
    }

    public void segmentToLevel(){
        this.levelScore+=this.segmentScore;
        this.clearSegmentScore();
    }

    public void changeLevelScore(int incScoreChange){
        this.levelScore+=incScoreChange;
    }

    public void clearLevelScore(){
        this.levelScore=0;
    }

    public void addLevelToTotal(){
        this.totalScore+=levelScore;
        this.clearLevelScore();
    }

    public boolean checkForProgress(){
        if(getCurItem()%numSequences==numSequences-1) return true;
        else return false;
    }


    public void iterateCurLevel() {iterateCurLevel(1);}

    public void iterateCurLevel(int incLevelChange){
        this.curLevel+=incLevelChange;
    }

    public void iterateCurItem() {iterateCurItem(1);}

    public void iterateCurItem(int incItemChange){
        this.curItem+=incItemChange;
    }


    public boolean iterateLevelSegment() { return iterateLevelSegment(1);}

    public boolean iterateLevelSegment(int segmentChange){
       this.levelSegment+=segmentChange;
       this.levelSegment=this.levelSegment%7;
       if(this.levelSegment==1){
           return true;
       }
       else return false;
    }

    public boolean iterateProgress(){ return iterateProgress(1);}

    public boolean iterateProgress(int progressChange){
        this.progress+=progressChange;
        if(this.progress%5==0){
            return true;
        }
        else return false;
    }

    public boolean iterateWrongAnswers() {return iterateWrongAnswers(1);}

    public boolean iterateWrongAnswers(int wrongChange){
        this.curWrongAnswers+=wrongChange;
        if(this.curWrongAnswers%2==0){
            return true;
        }
        else return false;
    }

    private void clearWrongAnswers(){
        curWrongAnswers=0;
    }

    public int getFinalMemX() {
      return xMods[maxXMods-1][2];
    }

    public String getMemXModDesc() {
        int curMod;
        String desc = "Now,";
        if(this.levelSegment>1 && this.levelSegment%2==1) {
            curMod = (this.levelSegment - 3) / 2;
            int modType=this.xMods[curMod][0];
            switch(modType) {
                case (0): {
                    desc= String.format(Locale.getDefault(),"%s add %d to X.", desc, this.xMods[curMod][1]);
                    break;
                }
                case (1): {
                    desc = String.format(Locale.getDefault(),"%s subtract %d from X.", desc, this.xMods[curMod][1]);
                    break;
                }
                case (2): {
                    desc = String.format(Locale.getDefault(),"%s multiply %d and X.", desc, this.xMods[curMod][1]);
                    break;
                }
                case (3): {
                    desc = String.format(Locale.getDefault(),"%s divide %d into X.", desc, this.xMods[curMod][1]);
                    break;
                }
            }
        }
        else desc="None";
        return desc;
    }

    public int getMaxSequences() {return this.maxSequences;}
    public int getMaxSequenceValues() {return this.maxSequenceValues;}
    public int getMaxGroceryItems() {return this.maxGroceryItems;}
    public int getMaxWrong() {return this.maxWrong;}
    public int getMemX() {return this.memX;}

    public int getCurDifficulty() {return this.curDifficulty;}
    public int getLevelSegment(){return this.levelSegment;}
    public int getCurLevel() {return this.curLevel;}
    public int getLevelScore() {return this.levelScore;}
    public int getTotalScore() {return this.totalScore;}
    public int getSegmentScore() {return this.segmentScore;}
    public int getCurItem() {return this.curItem;}
    public int getCurWrongAnswers() {return this.curWrongAnswers;}
    public int getProgress() {return this.progress;}
    public int getSegmentPointValue() {return this.segmentPointValue;}


    private void setMaxSequences(int incNewMax){this.maxSequences=incNewMax;}
    private void setMaxSequenceValues(int incNewMax){this.maxSequenceValues=incNewMax;}
    private void setMaxGroceryItems(int incNewMax){this.maxGroceryItems=incNewMax;}
    private void setCurDifficulty(int incDifficulty) {this.curDifficulty=incDifficulty;}
    private void setCurLevel(int incLevel) {this.curLevel=incLevel;}
    private void setMaxWrong(int incWrong) {this.maxWrong=incWrong;}
    public void setSegmentPointValue(int incPointValue) {this.segmentPointValue=incPointValue;}
}
