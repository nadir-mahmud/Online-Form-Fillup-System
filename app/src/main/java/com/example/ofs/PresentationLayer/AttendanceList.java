package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ofs.BusinessLogicLayer.MyOwnAdapter;
import com.example.ofs.DataLayer.DataReceiver;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.entity.Attendance;
import com.example.ofs.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLDisplay;

public class AttendanceList extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Attendance> attendance;
    RemoteServer remoteServer;
    MyOwnAdapter adapter;
    MyOwnAdapter.RecyclerviewClickListener listener;
    String spinnerSemester = null, spinnerYear = null,spinnerDepartment = null,departmentEdit,departmentInsert,validate = "No";
    TextView txt1,txt2;
    Intent intent1,intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        intent1 = new Intent(AttendanceList.this, EditAddOne.class);

        txt1 = (TextView) findViewById(R.id.textView);
        txt2 = (TextView) findViewById(R.id.textView2);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        intent = getIntent();

        spinnerDepartment = intent.getStringExtra("department");
        spinnerSemester = intent.getStringExtra("semester");
        spinnerYear = intent.getStringExtra("year");
        validate = intent.getStringExtra("isChanged?");



        remoteServer = new RemoteServer();
        attendance = new ArrayList<>();


        try {
            //test(spinnerSemester,spinnerYear);
            if(validate.equals("Yes")){

                if(intent.getStringExtra("updateInfo").equals("update")){
                    departmentEdit = intent.getStringExtra("departmentUpdate");
                    spinnerDepartment = departmentEdit;
                } else if(intent.getStringExtra("updateInfo").equals("insert")){
                    departmentInsert = intent.getStringExtra("departmentInsert");
                    spinnerDepartment = departmentInsert;
                }
                else{

                }

                spinnerSemester = intent.getStringExtra("semesterUpdate");
                spinnerYear = intent.getStringExtra("yearUpdate");
            }

            remoteServer.loadDatafromServer(spinnerDepartment,spinnerSemester, spinnerYear, new DataReceiver() {
                @Override
                public void onReceive(List<?> dataList) {
                    adapter = new MyOwnAdapter(AttendanceList.this, (List<Attendance>) dataList,setOnClickListener((List<Attendance>) dataList));
                    recyclerView.setAdapter(adapter);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                intent1.putExtra("department insert",spinnerDepartment);
                startActivity(intent1);
            }
        });


    }

    private MyOwnAdapter.RecyclerviewClickListener setOnClickListener(final List<Attendance> attendance) {
        listener = new MyOwnAdapter.RecyclerviewClickListener() {
            @Override
            public void onclick(View view, int postition) {
                Intent intent2 = new Intent(AttendanceList.this, EditAddOne.class);
                intent2.putExtra("id", attendance.get(postition).getId());
                intent2.putExtra("update", spinnerDepartment);
                intent2.putExtra("studentId", attendance.get(postition).getStudentId());
                intent2.putExtra("attendance", attendance.get(postition).getAttendance());
                intent2.putExtra("semester", attendance.get(postition).getSemester());
                intent2.putExtra("year", attendance.get(postition).getYear());
                startActivity(intent2);
            }
        };

        return listener;
    }

}