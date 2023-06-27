package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ofs.DataLayer.DataReceiver;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.R;
import com.example.ofs.entity.Office;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class OfficeLogin extends AppCompatActivity {
    TextInputEditText officeEmail, officePassword;
    Button officeLogin;
    String officeRole,officePasswordServer,officePass,office_email,department;
    List<Office> offices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_login);


        offices = new ArrayList<>();

        officeEmail = findViewById(R.id.office_email);
        officePassword = findViewById(R.id.office_password);

        officeLogin = findViewById(R.id.office_login);


        officeLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                office_email = officeEmail.getText().toString().trim();
                Log.d("pass", office_email);


                if (TextUtils.isEmpty(officeEmail.getText().toString()) && TextUtils.isEmpty(officePassword.getText().toString())) {
                    Toast.makeText(OfficeLogin.this, "You have not provided essential information", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String email = officeEmail.getText().toString().trim();
                        Log.d("email", email);

                        RemoteServer remoteServer = new RemoteServer();
                        remoteServer.getOfficeData(officeEmail.getText().toString().trim(), new DataReceiver() {
                            @Override
                            public void onReceive(List<?> dataList) {
                                offices = (List<Office>) dataList;
                                officeRole = offices.get(0).getRole();
                                department = offices.get(0).getDepartment();
                                officePasswordServer = offices.get(0).getPassword().trim();

                                officePass = officePassword.getText().toString().trim();

                                if (officePass.equals(officePasswordServer)) {

                                    switch (officeRole) {

                                        case "Department":
                                            Intent intentDepartment = new Intent(OfficeLogin.this, DepartmentActivity.class);
                                            intentDepartment.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            intentDepartment.putExtra("department",department);
                                            startActivity(intentDepartment);
                                            break;

                                        case "Registrar":
                                            Intent intent1 = new Intent(OfficeLogin.this, RegistrarActivity.class);
                                            startActivity(intent1);
                                            break;
                                        default:
                                            //do nothing

                                    }
                                } else {
                                    Toast.makeText(OfficeLogin.this, "You have not provided essential information", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        });
    }



}