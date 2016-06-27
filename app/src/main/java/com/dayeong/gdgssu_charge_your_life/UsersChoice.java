package com.dayeong.gdgssu_charge_your_life;

import android.util.Log;

/**
 * Created by Hyemingway on 2016. 6. 25..
 */
public class UsersChoice {

    private static final String TAG = "USERS_CHOICE";
    private static UsersChoice usersChoice;
    public static final int QUEST_NUM = 4;
    private int[] arrayChoices;

    private UsersChoice(){
        arrayChoices = new int[QUEST_NUM];

    }

    public static synchronized UsersChoice getInstance(){
        if(usersChoice == null){
            usersChoice = new UsersChoice();
        }
        return usersChoice;
    }

    public void setChoice(int idx, int choice){
        if(idx < 0){
            return;
        }
        int i = idx;
        arrayChoices[i] = choice;
        Log.d(TAG, "idx :" +i + " is " + choice );
    }

    public int[] getArrayChoices(){
        return arrayChoices;

    }

    public int getResultValue(){
        int res = 0;
        if(arrayChoices.length < 1){
            return res;
        }

        int temp = 0;
        for(int i = 0; i < arrayChoices.length; i++ ){

            temp += arrayChoices[i] * Math.pow(2, arrayChoices.length - (i + 1) );
        }
        res = temp;
        return res;
    }

    public String getResultRawValue(){
        String res = "";
        for(int i = 0; i < arrayChoices.length; i++){
            res += arrayChoices[i];
        }
        return res;
    }
}
