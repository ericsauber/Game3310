package com.example.ericsauber.game3310;

import java.util.Random;

/**
 * Created by Michael on 4/22/2017.
 */

class GameData {
    int maxSequences=5;
    int maxSequenceValues=5;
    int maxGroceryItems=5;

    String Sequences[][];
    int seqCountVals[];
    int groceryList[][];
    int memX;
    int xMods[];

    GameData(int incMaxSequences, int incMaxSequenceValues, int incMaxGroceryItems){
        if(!(incMaxSequences==maxSequences)) setMaxSequences(incMaxSequenceValues);
        if(!(incMaxSequenceValues==maxSequenceValues)) setMaxSequenceValues(incMaxSequenceValues);
        if(!(incMaxGroceryItems==maxGroceryItems)) setMaxGroceryItems(incMaxGroceryItems);

        initializeData(1,1,1);
    }

    GameData(){
       initializeData(1,1,1);
    }

    public void generateLevelData(int incLevel, int incDifficulty){
        Random rn = new Random();
        int numOfSequences = incLevel/5+2;
        if (numOfSequences>maxSequences) numOfSequences=maxSequences;
        for(int sequenceCounter=0;sequenceCounter<numOfSequences;sequenceCounter++){
            int seqType= rn.nextInt(2)+1;
            int seqCur;
            switch(seqType){
                case(1): {
                    setSeqCounts(sequenceCounter,rn.nextInt(9)+1);
                    seqCur = rn.nextInt(4)+1;
                    for(int valCounter=0;valCounter<maxSequenceValues;valCounter++){
                        setSequenceValue(sequenceCounter,valCounter,Character.toString((char)(seqCur+48)));
                        if((seqCur+this.seqCountVals[sequenceCounter])>9)
                            seqCur=(seqCur+this.seqCountVals[sequenceCounter])%9;
                        else seqCur+=this.seqCountVals[sequenceCounter];
                    }
                    break;
                }
                case(2): {
                    setSeqCounts(sequenceCounter,rn.nextInt(25)+1);
                    seqCur = rn.nextInt(12)+1;
                    for(int valCounter=0;valCounter<maxSequenceValues;valCounter++){
                        setSequenceValue(sequenceCounter,valCounter,Character.toString((char)(seqCur+64)));
                        if((seqCur+this.seqCountVals[sequenceCounter])>26)
                            seqCur=(seqCur+this.seqCountVals[sequenceCounter])%26;
                        else seqCur+=this.seqCountVals[sequenceCounter];
                    }
                    break;
                }
                case(3): {
                    setSeqCounts(sequenceCounter,1);
                    seqCur = rn.nextInt(1)+1;
                    for(int valCounter=0;valCounter<maxSequenceValues;valCounter++){
                        if(seqCur%2==0) setSequenceValue(sequenceCounter,valCounter,"False");
                        else setSequenceValue(sequenceCounter,valCounter,"True");
                    }
                    break;
                }
            }
        }
    }


    void initializeData(int incLevel, int incDifficulty, int initGroceryItems){
       Sequences = new String[maxSequences][maxSequenceValues];

       for(int sequenceCounter=0;sequenceCounter<maxSequences;sequenceCounter++) {
           setSeqCounts(sequenceCounter,0);
           for (int valCounter = 0; valCounter < maxSequenceValues; valCounter++)
               setSequenceValue(sequenceCounter, valCounter, String.format("%d", -1));
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
