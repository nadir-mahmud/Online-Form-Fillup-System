package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ofs.DataLayer.DataReceiver;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.R;
import com.example.ofs.entity.Registrar;

import java.util.ArrayList;
import java.util.List;

public class RegistrarInformation extends AppCompatActivity {
    TextView department, initialDate, withoutFine, withFine, feePerCredit, finePerDay;
    Button update;
    String intentDepartment;
    List<Registrar> registrars;
    Intent intent1;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_information);

        registrars = new ArrayList<>();

        department = findViewById(R.id.department_name);
        initialDate = findViewById(R.id.iniatial_date_value);
        withoutFine = findViewById(R.id.without_fine_value);
        withFine = findViewById(R.id.with_fine_value);
        feePerCredit = findViewById(R.id.fee_per_credit_value);
        finePerDay = findViewById(R.id.fine_per_day_value);

        update = findViewById(R.id.update);

        Intent intent = getIntent();
        intentDepartment = intent.getStringExtra("department");

        intent1 = new Intent(RegistrarInformation.this, UpdateRegistrarActivity.class);

        RemoteServer remoteServer = new RemoteServer();
        remoteServer.getRegistrarInfo(intentDepartment, new DataReceiver() {
            @Override
            public void onReceive(List<?> dataList) {
                registrars = (List<Registrar>) dataList;

                if( registrars.size() > 0 ){
                    id = registrars.get(0).getId();
                    department.setText(registrars.get(0).getDepartment());
                    initialDate.setText(registrars.get(0).getInitialDate());
                    intent1.putExtra("initial date", registrars.get(0).getInitialDate());
                    withoutFine.setText(registrars.get(0).getDeadlineWithoutFine());
                    intent1.putExtra("without fine", registrars.get(0).getDeadlineWithoutFine());
                    withFine.setText(registrars.get(0).getDeadlineWithFine());
                    intent1.putExtra("with fine", registrars.get(0).getDeadlineWithFine());
                    feePerCredit.setText(String.valueOf(registrars.get(0).getFeePerCredit()));
                    intent1.putExtra("fee per credit",String.valueOf(registrars.get(0).getFeePerCredit()));
                    finePerDay.setText(String.valueOf(registrars.get(0).getFinePerDay()));
                    intent1.putExtra("fine per day", String.valueOf(registrars.get(0).getFinePerDay()));
                } else{
                    Toast.makeText(RegistrarInformation.this, "Network connection failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent1.putExtra("department",intentDepartment);
                intent1.putExtra("id", id);
                startActivity(intent1);

            }
        });


    }
}