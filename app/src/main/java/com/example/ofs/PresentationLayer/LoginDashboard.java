package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ofs.R;

public class LoginDashboard extends AppCompatActivity {
    Button student,office;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dashboard);

        student = findViewById(R.id.student);
        office = findViewById(R.id.office);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDashboard.this,StudentLogin.class);
                startActivity(intent);
            }
        });

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDashboard.this, OfficeLogin.class);
                startActivity(intent);

            }
        });
    }
}