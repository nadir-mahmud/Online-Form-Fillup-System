package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ofs.BusinessLogicLayer.PaymentCreator;
import com.example.ofs.DataLayer.DataReceiver;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.R;
import com.example.ofs.entity.MobileBank;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    TextView studentId, studentIdValue, paymentSemester, paymentSemesterValue, paymentYear, paymentYearValue, payment, paymentValue;
    TextInputEditText mobileNumber, pinNumber, paymentAmount;
    String pinServer, pin , amount, mobile,department;
    int paymentReal;
    List<MobileBank> mobileBanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mobileBanks = new ArrayList<>();

        studentId =(TextView) findViewById(R.id.studentId);
        studentIdValue = (TextView) findViewById(R.id.studentIdValue);
        paymentSemester = (TextView) findViewById(R.id.paymentSemester);
        paymentSemesterValue = (TextView) findViewById(R.id.paymentSemesterValue);
        paymentYear = (TextView) findViewById(R.id.paymentYear);
        paymentYearValue = (TextView) findViewById(R.id.paymentYearValue);
        payment = (TextView) findViewById(R.id.payment);
        paymentValue = (TextView) findViewById(R.id.paymentValue);

        Intent intent = getIntent();
        int fineDay = intent.getIntExtra("fineDay",0);
        String needFin = intent.getStringExtra("need fine?");
        int fine = intent.getIntExtra("fine",0);
        int credit = intent.getIntExtra("credit",0);
        int totalCredit = intent.getIntExtra("Total credit",0);
        Log.d("total", String.valueOf(totalCredit));
        final String semester = intent.getStringExtra("semester");
        final String year = intent.getStringExtra("year");
        final String name = intent.getStringExtra("name");
        final String studentId = intent.getStringExtra("studentId");
        department = intent.getStringExtra("department");

        mobileNumber = (TextInputEditText) findViewById(R.id.mobile_number);
        paymentAmount = (TextInputEditText) findViewById(R.id.paymentAmount);
        pinNumber = (TextInputEditText) findViewById(R.id.pin_number);

        Button pay = (Button) findViewById(R.id.pay);

        paymentReal = PaymentCreator.getPayment(credit,fine,fineDay, totalCredit);
        Log.d("payment", String.valueOf(paymentReal));

        studentIdValue.setText(studentId);
        paymentSemesterValue.setText(semester);
        paymentYearValue.setText(year);
        paymentValue.setText(Integer.toString(paymentReal));

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNotValid()){

                }

                mobile = mobileNumber.getText().toString().trim();
                pin = pinNumber.getText().toString().trim();
                amount = paymentAmount.getText().toString().trim();

                RemoteServer remoteServer = new RemoteServer();
                remoteServer.getMobileBankingData(mobile,new DataReceiver() {
                            @Override
                            public void onReceive(List<?> dataList) {
                                mobileBanks = (List<MobileBank>) dataList;
                                if( mobileBanks.size() > 0 ){
                                    pinServer = mobileBanks.get(0).getPinNumber();

                                    if(pin.equals(pinServer) && amount.equals(String.valueOf(paymentReal))){
                                        Intent intent = new Intent(PaymentActivity.this, AdmitCard.class);
                                        intent.putExtra("name",name);
                                        intent.putExtra("studentId",studentId);
                                        intent.putExtra("semester", semester);
                                        intent.putExtra("year", year);
                                        startActivity(intent);
                                    }
                                }
                                else{
                                    if(!pin.equals(pinServer)){
                                        Toast.makeText(PaymentActivity.this,"Incorrect pin number",Toast.LENGTH_SHORT).show();
                                    }
                                    if(!amount.equals(String.valueOf(paymentReal))){
                                        Toast.makeText(PaymentActivity.this,"Incorrect amount",Toast.LENGTH_SHORT).show();
                                    }
                                    if((!pin.equals(pinServer)) && (!amount.equals(String.valueOf(paymentReal)))){
                                        Toast.makeText(PaymentActivity.this,"Incorrect amount and pin number",Toast.LENGTH_SHORT).show();
                                    }

                                }
                        }
                });

            }

        });

    }

    public Boolean isNotValid(){
        return TextUtils.isEmpty(mobileNumber.getText().toString().trim()) && TextUtils.isEmpty(paymentAmount.getText().toString().trim()) &&
                TextUtils.isEmpty(pinNumber.getText().toString().trim());
    }

}