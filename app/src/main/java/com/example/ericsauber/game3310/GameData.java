package com.example.ericsauber.game3310;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Michael on 4/22/2017.
 */

class GameData {
    private int maxSequences=3;
    private int maxSequenceValues=5;
    private int maxGroceryItems=5;

    public String Sequences[][];
    public String descString ="In level";
    public String seqDescriptor[][];
    public int numSequences;
    public int numGroceries;
    private int seqCountVals[];
    public int groceryList[][];
    public int memX;
    public int xMods[];

    GameData(int incLevel, int incDifficulty, int incMaxSequences, int incMaxSequenceValues, int incMaxGroceryItems){
        if(!(incMaxSequences==maxSequences)) setMaxSequences(incMaxSequenceValues);
        if(!(incMaxSequenceValues==maxSequenceValues)) setMaxSequenceValues(incMaxSequenceValues);
        if(!(incMaxGroceryItems==maxGroceryItems)) setMaxGroceryItems(incMaxGroceryItems);

        initializeData(incLevel,incDifficulty,1);
        generateLevelData(incLevel,incDifficulty);
    }

    GameData(int incLevel, int incDifficulty){
       initializeData(incLevel,incDifficulty,1);
       generateLevelData(incLevel,incDifficulty);
    }

    GameData()
    {
        initializeData(4,1,1);
        generateLevelData(4,1);
    }

    public void generateLevelData(int incLevel, int incDifficulty){
        Random rn = new Random();
        int numOfSequences = incLevel/5+2;
        if (numOfSequences>maxSequences) numOfSequences=maxSequences;
        this.numSequences=numOfSequences;
        descString = String.format("%s %d you will need to...\n",descString,incLevel);
        for(int sequenceCounter=0;sequenceCounter<numOfSequences;sequenceCounter++){
            int seqType= rn.nextInt(2)+1;
            int seqCur;
            switch(seqType) {
                case (1): {
                    setSeqCounts(sequenceCounter, rn.nextInt(1 + (incLevel%8)) + 1);
                    seqCur = rn.nextInt(4) + 1;
                    descString = String.format("%s Count through the digits 1,2,3,...9 by %d's, beginning at %s.\n",descString,seqCountVals[sequenceCounter],Character.toString((char) (seqCur + 48)));
                    for (int valCounter = 0; valCounter < maxSequenceValues; valCounter++) {
                        setSequenceValue(sequenceCounter, valCounter, Character.toString((char) (seqCur + 48)));
                        addSeqDescriptor(sequenceCounter, Character.toString((char) (seqCur + 48)));
                        if ((seqCur + this.seqCountVals[sequenceCounter]) > 9)
                            seqCur = (seqCur + this.seqCountVals[sequenceCounter]) % 9;
                        else seqCur += this.seqCountVals[sequenceCounter];
                    }
                    break;
                }
                case (2): {
                    setSeqCounts(sequenceCounter, rn.nextInt(3 + (incLevel%10)) + 1);
                    seqCur = rn.nextInt(12) + 1;
                    descString = String.format("%s Count through the characters a,b,c,...z for each %d'th character, beginning with %s.\n",descString,seqCountVals[sequenceCounter],Character.toString((char) (seqCur + 64)));
                    for (int valCounter = 0; valCounter < maxSequenceValues; valCounter++) {
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
                    descString = String.format("%s Count through the set True,False, starting at ",descString);
                    if (seqCur % 2 == 0) String.format("%s %s.\n",descString,"False");
                    else String.format("%s %s.\n",descString,"True");
                    for (int valCounter = 0; valCounter < maxSequenceValues; valCounter++) {
                        if (seqCur % 2 == 0) setSequenceValue(sequenceCounter, valCounter, "False");
                        else setSequenceValue(sequenceCounter, valCounter, "True");
                        seqCur++;
                    }
                    break;
                }
            }
        }
        descString = String.format("%s (",descString);
        for(int valCounter=0;valCounter<3;valCounter++) {
            for (int seqCounter = 0; seqCounter < numOfSequences; seqCounter++) descString = String.format("%s%s",descString,Sequences[seqCounter][valCounter]);
            if(valCounter<2) descString = String.format("%s,",descString);
        }
        descString = String.format("%s)\n",descString);
        for(int seqCounter=0;seqCounter<maxSequences;seqCounter++) reorderSeqDescriptors(seqCounter);
    }


    void initializeData(int incLevel, int incDifficulty, int initGroceryItems){
       Sequences = new String[maxSequences][maxSequenceValues];
       seqDescriptor = new String[maxSequences][maxSequenceValues];
       seqCountVals = new int[maxSequences];

       for(int sequenceCounter=0;sequenceCounter<maxSequences;sequenceCounter++) {
           setSeqCounts(sequenceCounter,0);
           for (int valCounter = 0; valCounter < maxSequenceValues; valCounter++) {
               setSequenceValue(sequenceCounter, valCounter, String.format("%d", -1));
               setSequenceDescriptorValue(sequenceCounter, valCounter, String.format("%d", -1));
           }
       }
        if(initGroceryItems==1) {
            groceryList = new int[2][maxGroceryItems];

            for (int groceryCounter = 0; groceryCounter < maxGroceryItems; groceryCounter++) {
                setGroceryListItem(groceryCounter, 0, -1);
            }
        }

       setMemX(-1);

       xMods=new int[incLevel];
        for(int modCounter=0;modCounter<incLevel;modCounter++){
            setXMod(modCounter,-1);
        }
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
        for(int valCounter=0;valCounter<maxSequenceValues;valCounter++){
            if(seqDescriptor[incSequence][valCounter].equals(incValue)){
                valInSequence=true;
            }
            if(seqDescriptor[incSequence][valCounter].equals("-1")){
                firstOpenValue=valCounter;
                break;
            }
        }
        if(valInSequence==false && firstOpenValue<maxSequenceValues)
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
        this.groceryList[0][incItemNum]=incItemType;
        this.groceryList[1][incItemNum]=incItemQuantity;
    }

    private void setXMod(int modNum, int incModValue){
        this.xMods[modNum]=incModValue;
    }

    private void setSeqCounts(int seqNum, int incCountValue){
        this.seqCountVals[seqNum]=incCountValue;
    }

    private void setMaxSequences(int incNewMax){
        this.maxSequences=incNewMax;
    }

    private void setMaxSequenceValues(int incNewMax){
        this.maxSequenceValues=incNewMax;
    }

    private void setMaxGroceryItems(int incNewMax){
        this.maxGroceryItems=incNewMax;
    }
}
