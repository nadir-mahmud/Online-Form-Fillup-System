package com.example.ofs.BusinessLogicLayer;

import android.text.TextUtils;
import android.util.Log;


public class CreditLogic {
    public int getTotalCredit(String credit1, String credit2, String credit3, String credit4, String credit5, String credit6, String credit7, String credit8, String credit9, String credit10, String credit11){
        int cred1, cred2, cred3, cred4, cred5, cred6, cred7, cred8, cred9, cred10, cred11;

        Log.d("credit1",credit1);

        if(!(credit1.equals(""))){
            cred1 = Integer.parseInt(credit1);
        }
        else{
            cred1 = 0;
        }

        if(!(credit2.equals(""))){
            cred2 = Integer.parseInt(credit2);
        }
        else{
            cred2 = 0;
        }

        if(!(credit3.equals(""))){
            cred3 = Integer.parseInt(credit3);
        }
        else{
            cred3 = 0;
        }

        if(!(credit4.equals(""))){
            cred4 = Integer.parseInt(credit4);
        }
        else{
            cred4 = 0;
        }

        if(!(credit5.equals(""))){
            cred5 = Integer.parseInt(credit5);
        }
        else{
            cred5 = 0;
        }

        if(!(credit6.equals(""))){
            cred6 = Integer.parseInt(credit6);
        }
        else{
            cred6 = 0;
        }

        if(!(credit7.equals(""))){
            cred7 = Integer.parseInt(credit7);
        }
        else{
            cred7 = 0;
        }

        if(!(credit8.equals(""))){
            cred8 = Integer.parseInt(credit8);
        }
        else{
            cred8 = 0;
        }

        if(!(credit9.equals(""))){
            cred9 = Integer.parseInt(credit9);
        }
        else{
            cred9 = 0;
        }

        if(!(credit10.equals(""))){
            cred10 = Integer.parseInt(credit10);
        }
        else{
            cred10 = 0;
        }

        if(!(credit11.equals(""))){
            cred11 = Integer.parseInt(credit11);
        }
        else{
            cred11 = 0;
        }

        return cred1 + cred2 + cred3 + cred4 + cred5 + cred6 + cred7 + cred8 + cred9 + cred10 + cred11;
    }
}
