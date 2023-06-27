package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ofs.R;

public class RegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Button view = findViewById(R.id.view_registrar_information);

        final Spinner spinnerDepartment = findViewById(R.id.department_spinner);
        ArrayAdapter<CharSequence> adapterDepartment = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_spinner_item);
        adapterDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapterDepartment);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDepartment = spinnerDepartment.getSelectedItem().toString();
                Intent intent = new Intent(RegistrarActivity.this, RegistrarInformation.class);
                intent.putExtra("department", selectedDepartment);
                startActivity(intent);
            }
        });
    }
}