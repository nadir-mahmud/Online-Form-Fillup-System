package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ofs.R;

public class DepartmentActivity extends AppCompatActivity {
    private Button viewButton;
    Spinner spinnerDepartment, spinnerSemester, spinnerYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        //onBackPressed();


        viewButton = findViewById(R.id.view);

        spinnerDepartment = findViewById(R.id.department_spinner_1);
        ArrayAdapter<CharSequence> adapterDepartment = ArrayAdapter.createFromResource(this,
                R.array.department_array, android.R.layout.simple_spinner_item);
        adapterDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapterDepartment);

        spinnerSemester = (Spinner) findViewById(R.id.semester_spinner);
        ArrayAdapter<CharSequence> adapterSemester = ArrayAdapter.createFromResource(this,
                R.array.semester_array, android.R.layout.simple_spinner_item);
        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(adapterSemester);

        spinnerYear = (Spinner) findViewById(R.id.year_spinner);
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterYear);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String department = spinnerDepartment.getSelectedItem().toString();
                String semester = spinnerSemester.getSelectedItem().toString();
                String year = spinnerYear.getSelectedItem().toString();

                Intent intent = new Intent(DepartmentActivity.this, AttendanceList.class);
                intent.putExtra("department", department);
                intent.putExtra("semester", semester);
                intent.putExtra("year", year);
                intent.putExtra("isChanged?","food");
                startActivity(intent);

            }
        });


    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }
}