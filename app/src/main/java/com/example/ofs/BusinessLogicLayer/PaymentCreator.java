package com.example.ofs.BusinessLogicLayer;

public class PaymentCreator {

    public static int getPayment(int credit, int fine, int fineDay, int totalCredit){
        int payment;

        payment = (credit * totalCredit) + (fine * fineDay);

        return payment;

    }

}
