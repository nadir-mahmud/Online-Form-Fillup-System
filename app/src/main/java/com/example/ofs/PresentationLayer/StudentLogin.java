package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ofs.DataLayer.DataReceiver;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.R;
import com.example.ofs.entity.StudentLoginValidation;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class StudentLogin extends AppCompatActivity {
    TextInputEditText studentLoginId, studentPassword, studentSemester, studentYear;
    String passwordServer,password;
    String year,semester;
    Button login;
    List<StudentLoginValidation> studentValidation;
    Spinner spinnerSemester,spinnerYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        studentValidation = new ArrayList<>();

        studentLoginId = findViewById(R.id.student_login_id);
        studentLoginId.setFocusableInTouchMode(true);
        studentPassword = findViewById(R.id.student_password);
        studentPassword.setFocusableInTouchMode(true);

        spinnerSemester = findViewById(R.id.semester_studentLogin_spinner);
        ArrayAdapter<CharSequence> adapterSemester = ArrayAdapter.createFromResource(this,
                R.array.semester_array, android.R.layout.simple_spinner_item);
        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(adapterSemester);


        spinnerYear = findViewById(R.id.year_studentLogin_spinner);
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterYear);



        login = findViewById(R.id.student_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNotValid()){
                    Toast.makeText(StudentLogin.this, "Empty Information",Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    semester = spinnerSemester.getSelectedItem().toString();
                    year = spinnerYear.getSelectedItem().toString();

                    RemoteServer remoteServer = new RemoteServer();
                    remoteServer.getStudentLoginData(studentLoginId.getText().toString().trim(), new DataReceiver() {
                        @Override
                        public void onReceive(List<?> dataList) {
                            studentValidation = (List<StudentLoginValidation>) dataList;
                            if(studentValidation.size() > 0){
                                passwordServer = studentValidation.get(0).getPassword();

                                password = studentPassword.getText().toString().trim();

                                if(password.equals(passwordServer)){
                                    Intent intent = new Intent(StudentLogin.this, FormActivity.class);
                                    intent.putExtra("studentId",studentLoginId.getText().toString().trim());
                                    intent.putExtra("semester",semester);
                                    intent.putExtra("year",year);
                                    startActivity(intent);
                                }
                            }
                            else{
                                Toast.makeText(StudentLogin.this, "Incorrect Passwod", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    public Boolean isNotValid(){
        return TextUtils.isEmpty(studentLoginId.getText()) || TextUtils.isEmpty(studentPassword.getText());
    }


}