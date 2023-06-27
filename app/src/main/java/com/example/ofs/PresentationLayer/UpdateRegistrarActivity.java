package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ofs.BusinessLogicLayer.DateSelector;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Response;

public class UpdateRegistrarActivity extends AppCompatActivity {
    TextView initial_date_select, without_fine_date_select, with_fine_date_select;
    TextInputEditText enterFee,enterFine;
    Button submit;
    DateSelector initialDateSelector,withoutFineDateSelector,withFineDateSelector;
    RemoteServer remoteServer;
    String intentDepartment;
    int id;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_registrar);

        remoteServer = new RemoteServer();

        initial_date_select = (TextView) findViewById(R.id.select_date1);
        without_fine_date_select = (TextView) findViewById(R.id.select_date2);
        with_fine_date_select = (TextView) findViewById(R.id.select_date3);

        //   Year = year.getText().toString();
        enterFee = (TextInputEditText) findViewById(R.id.enter_fee);
        enterFine = (TextInputEditText) findViewById(R.id.enter_fine);

        initialDateSelector = new DateSelector();
        initialDateSelector.selectDate(this,initial_date_select);
        withoutFineDateSelector = new DateSelector();
        withoutFineDateSelector.selectDate(this,without_fine_date_select);
        withFineDateSelector = new DateSelector();
        withFineDateSelector.selectDate(this, with_fine_date_select);

        Intent intent = getIntent();
        intentDepartment = intent.getStringExtra("department");
        id = intent.getIntExtra("id",0);
        initial_date_select.setText(intent.getStringExtra("initial date"));
        without_fine_date_select.setText(intent.getStringExtra("without fine"));
        with_fine_date_select.setText(intent.getStringExtra("with fine"));
        enterFee.setText(intent.getStringExtra("fee per credit"));
        enterFine.setText(intent.getStringExtra("fine per day"));



        submit = (Button) findViewById(R.id.submitoffice);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    remoteServer.updateRegistrarInfo(id,intentDepartment,initial_date_select.getText().toString().trim(), without_fine_date_select.getText().toString().trim(), with_fine_date_select.getText().toString().trim(),
                            Integer.parseInt(enterFee.getText().toString().trim()) , Integer.parseInt(enterFine.getText().toString().trim()));

                    Intent intent1 = new Intent(UpdateRegistrarActivity.this,RegistrarInformation.class);
                    intent1.putExtra("department",intentDepartment);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}