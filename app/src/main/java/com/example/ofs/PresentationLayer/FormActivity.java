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

import com.example.ofs.BusinessLogicLayer.CreditLogic;
import com.example.ofs.DataLayer.DataReceiver;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.entity.Attendance;
import com.example.ofs.R;
import com.example.ofs.entity.Registrar;
import com.example.ofs.entity.Student;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FormActivity extends AppCompatActivity {
    TextInputEditText studentName, fatherName, motherName, semester, hallName;
    TextInputEditText course1,course2,course3,course4,course5,course6,course7,course8,course9,course10,course11;
    TextInputEditText credit1,credit2,credit3,credit4,credit5,credit6,credit7,credit8,credit9,credit10,credit11;
    TextView attendance;
    Button submit;
    int year1,month,day,fineDay,fine, credit;
    String year,needFine;
    String studentIdLogin,semesterLogin,yearLogin;
    String name, father, mother, hall;
    String nameServer, fatherServer, motherServer,departmentServer, hallServer;
    RemoteServer remoteServer;
    List<Attendance> studentAttendance;
    List<Student> student;
    List<Registrar> registrars;
    Intent intent;
    CreditLogic creditLogic;
    String department;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        onBackPressed();


        student = new ArrayList<>();
        registrars = new ArrayList<>();
        studentAttendance = new ArrayList<>();

        intent = new Intent(FormActivity.this, PaymentActivity.class);

        year1 = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        year = Integer.toString(year1);

        creditLogic = new CreditLogic();

        studentName = (TextInputEditText) findViewById(R.id.studentname_Etx);
        fatherName = (TextInputEditText) findViewById(R.id.fathersname_Etx);
        motherName = (TextInputEditText) findViewById(R.id.mothername_Etx);
        semester = (TextInputEditText) findViewById(R.id.semester_Etx);
        hallName = (TextInputEditText) findViewById(R.id.hall_Etx);

        course1 = (TextInputEditText) findViewById(R.id.course1);
        course2 = (TextInputEditText) findViewById(R.id.course2);
        course3 = (TextInputEditText) findViewById(R.id.course3);
        course4 = (TextInputEditText) findViewById(R.id.course4);
        course5 = (TextInputEditText) findViewById(R.id.course5);
        course6 = (TextInputEditText) findViewById(R.id.course6);
        course7 = (TextInputEditText) findViewById(R.id.course7);
        course8 = (TextInputEditText) findViewById(R.id.course8);
        course9 = (TextInputEditText) findViewById(R.id.course9);
        course10 = (TextInputEditText) findViewById(R.id.course10);
        course11 = (TextInputEditText) findViewById(R.id.course11);

        credit1 = (TextInputEditText) findViewById(R.id.credit1);
        credit2 = (TextInputEditText) findViewById(R.id.credit2);
        credit3 = (TextInputEditText) findViewById(R.id.credit3);
        credit4 = (TextInputEditText) findViewById(R.id.credit4);
        credit5 = (TextInputEditText) findViewById(R.id.credit5);
        credit6 = (TextInputEditText) findViewById(R.id.credit6);
        credit7 = (TextInputEditText) findViewById(R.id.credit7);
        credit8 = (TextInputEditText) findViewById(R.id.credit8);
        credit9 = (TextInputEditText) findViewById(R.id.credit9);
        credit10 = (TextInputEditText) findViewById(R.id.credit10);
        credit11 = (TextInputEditText) findViewById(R.id.credit11);



        attendance = (TextView) findViewById(R.id.attendance_formfillup);


        remoteServer = new RemoteServer();


        submit = (Button) findViewById(R.id.formfillupsubmit);

        submit.setEnabled(true);

        Intent intentLogin = getIntent();
        studentIdLogin = intentLogin.getStringExtra("studentId");
        semesterLogin = intentLogin.getStringExtra("semester");
        Log.d("semester",semesterLogin);
        yearLogin = intentLogin.getStringExtra("year");
        Log.d("year", yearLogin);

        getStudentInfo(studentIdLogin);

        checkAttendance(studentIdLogin,semesterLogin, yearLogin);



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {

                    if(TextUtils.isEmpty(studentName.getText().toString()) && TextUtils.isEmpty(fatherName.getText().toString()) && TextUtils.isEmpty(motherName.getText().toString()) && TextUtils.isEmpty(hallName.getText().toString()) ){
                        Toast.makeText(FormActivity.this,"You have provided empty information", Toast.LENGTH_SHORT).show();
                    }

                    name = studentName.getText().toString().trim();
                    father= fatherName.getText().toString().trim();
                    mother = motherName.getText().toString().trim();
                    hall = hallName.getText().toString().trim();

                    int totalCredit = creditLogic.getTotalCredit(credit1.getText().toString().trim(), credit2.getText().toString().trim(),
                            credit3.getText().toString().trim(), credit4.getText().toString().trim(), credit5.getText().toString().trim(),
                            credit6.getText().toString().trim(), credit7.getText().toString().trim(), credit8.getText().toString().trim(),
                            credit9.getText().toString().trim(), credit10.getText().toString().trim(), credit11.getText().toString().trim());

                    intent.putExtra("Total credit", totalCredit);
                    Log.d("total", String.valueOf(totalCredit));

                    Boolean result = name.equals(nameServer);
                    Log.d("result", String.valueOf(result));

                    remoteServer.getStudentInfo(studentIdLogin, new DataReceiver() {
                        @Override
                        public void onReceive(List<?> dataList) {
                            student = (List<Student>) dataList;

                            if( student.size() > 0 ){
                                String dept = student.get(0).getDepartment();
                                Log.d("department",dept);
                                getStudentInfo(student.get(0).getDepartment());
                                nameServer = student.get(0).getName();
                                fatherServer = student.get(0).getFatherName();
                                motherServer = student.get(0).getMotherName();
                                departmentServer = student.get(0).getDepartment();
                                intent.putExtra("department",departmentServer);
                                hallServer = student.get(0).getHallName();
                            }

                            Log.d("name", nameServer);
                            Log.d("father",fatherServer);
                            Log.d("mother",motherServer);
                            Log.d("mother",hallServer);


                            if(name.equals(nameServer) && father.equals(fatherServer) && mother.equals(motherServer) && hall.equals(hallServer)) {

                                intent.putExtra("semester",semesterLogin);
                                intent.putExtra("name",nameServer);
                                intent.putExtra("year",yearLogin);
                                intent.putExtra("studentId", studentIdLogin);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(FormActivity.this,"You have provided wrong information", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void checkAttendance(String studentId, String semester, String year){
        remoteServer.checkAttendance(studentId, semester, year, new DataReceiver() {
            @Override
            public void onReceive(List<?> dataList) {
                studentAttendance = (List<Attendance>) dataList;
                if(studentAttendance.size() > 0){
                    String attend = studentAttendance.get(0).getAttendance();
                    if(Integer.parseInt(attend) < 60){
                        submit.setEnabled(false);
                    }
                    attendance.setText("Attendance : " + attend + "%");
                }
            }
        });
    }

    public void getStudentInfo(final String studentId){
        remoteServer.getStudentInfo(studentId, new DataReceiver() {
            @Override
            public void onReceive(List<?> dataList) {
                student = (List<Student>) dataList;

                if( student.size() > 0 ){
                    String dept = student.get(0).getDepartment();
                    Log.d("department",dept);
                    getRegistrarInfo(student.get(0).getDepartment());
                    nameServer = student.get(0).getName();
                    fatherServer = student.get(0).getFatherName();
                    motherServer = student.get(0).getMotherName();
                    hallServer = student.get(0).getHallName();
                }
            }
        });
    }

    public void getRegistrarInfo(String department){
        remoteServer.getRegistrarInfo(department,new DataReceiver() {
            @Override
            public void onReceive(List<?> dataList) {
                registrars = (List<Registrar>) dataList;

                if( registrars.size() > 0 ){
                    String initial_date = registrars.get(0).getInitialDate();
                    String deadline_without_fine = registrars.get(0).getDeadlineWithoutFine();
                    String deadline_with_fine = registrars.get(0).getDeadlineWithFine();
                    credit = registrars.get(0).getFeePerCredit();
                    fine = registrars.get(0).getFinePerDay();

                    Calendar todayCalendar = Calendar.getInstance();
                    Date today = todayCalendar.getTime();

                    Date initial = null;
                    Date without_fine = null;
                    Date with_fine = null;

                    try {
                        initial = new SimpleDateFormat("yyyy/MM/dd").parse(initial_date);
                        without_fine = new SimpleDateFormat("yyyy/MM/dd").parse(deadline_without_fine);
                        with_fine = new SimpleDateFormat("yyyy/MM/dd").parse(deadline_with_fine);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    if(today.compareTo(initial) == -1 ){
                        int val = today.compareTo(initial);
                        Log.d("val", String.valueOf(val));
                        submit.setEnabled(false);
                        Toast.makeText(FormActivity.this, "Form Fil-up hasn't started yet",Toast.LENGTH_SHORT).show();
                    }

                    if((today.compareTo(with_fine)) == 1){
                        int value = today.compareTo(with_fine);
                        Log.d("value", String.valueOf(value));

                        submit.setEnabled(false);
                        Toast.makeText(FormActivity.this, "You are too late, sorry!",Toast.LENGTH_SHORT).show();
                    }


                    if(today.compareTo(without_fine) == 1){
                        int again = today.compareTo(without_fine);
                        Log.d("val", String.valueOf(again));
                        int toda = today.getDate();
                        int without = without_fine.getDate();
                        fineDay = toda - without;
                        needFine = "FineNeeded";
                    }

                    intent.putExtra("fineDay",fineDay);
                    intent.putExtra("need fine?", needFine );
                    intent.putExtra("fine", fine);
                    intent.putExtra("credit", credit);
                }

            }
        });
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }


}