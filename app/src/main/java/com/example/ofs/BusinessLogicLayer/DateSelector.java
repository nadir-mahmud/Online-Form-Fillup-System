package com.example.ofs.BusinessLogicLayer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class DateSelector {
    DatePickerDialog.OnDateSetListener mDateSetListener;
    public void selectDate(final Context context, final TextView textView){

        textView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog, mDateSetListener, year, month, dayOfMonth);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = " " + year + "/" + month + "/" + dayOfMonth;
                textView.setText(date);
            }
        };
    }
}
