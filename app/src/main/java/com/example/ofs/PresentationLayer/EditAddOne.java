package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.R;

import java.io.IOException;

public class EditAddOne extends AppCompatActivity {

    private EditText studentId, attendance, semester, year;
    private Button ok;
    RemoteServer remoteServer;
    Intent intent,intent1;
    String student, atten, sem, year2,departmentInsert,departmentUpdate;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add_one);

        studentId = (EditText) findViewById(R.id.et_id);
        attendance = (EditText) findViewById(R.id.et_attendance);
        semester = (EditText) findViewById(R.id.et_semester);
        year = (EditText) findViewById(R.id.et_year);
        ok = (Button) findViewById(R.id.btn_ok);

       remoteServer = new RemoteServer();

        intent = getIntent();

        id = intent.getIntExtra("id", 0);
        departmentUpdate = intent.getStringExtra("update");
        student = intent.getStringExtra("studentId");
        atten = intent.getStringExtra("attendance");
        sem = intent.getStringExtra("semester");
        year2 = intent.getStringExtra("year");
        departmentInsert = intent.getStringExtra("department insert");
        //Log.d("insert",departmentUpdate);




        if(id != 0){
            setDataFromIntentExtra();
        }


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intentUpdate = new Intent(EditAddOne.this,AttendanceList.class);
                    intentUpdate.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intentUpdate.putExtra("semesterUpdate",semester.getText().toString().trim());
                    intentUpdate.putExtra("yearUpdate",year.getText().toString().trim());
                    intentUpdate.putExtra("isChanged?", "Yes");


                    if(id != 0){
                        remoteServer.updateData(id,departmentUpdate.trim(),studentId.getText().toString().trim(), attendance.getText().toString().
                                trim(), semester.getText().toString().trim(), year.getText().toString().trim());
                        intentUpdate.putExtra("departmentUpdate",departmentUpdate);
                        intentUpdate.putExtra("updateInfo", "update");
                        startActivity(intentUpdate);
                    }
                    else {
                        
                        remoteServer.sendPost(departmentInsert,studentId.getText().toString().trim(), attendance.getText().toString().trim(),
                                semester.getText().toString().trim(), year.getText().toString().trim());
                        intentUpdate.putExtra("departmentInsert",departmentInsert);
                        intentUpdate.putExtra("updateInfo","insert");
                        startActivity(intentUpdate);
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }


                //Intent intent = new Intent(EditAddOne.this, DepartmentActivity.class);
                //startActivity(intent);
            }
        });



    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            studentId.setText(student);
            attendance.setText(atten);
            semester.setText(sem);
            year.setText(year2);

        }

    }

}