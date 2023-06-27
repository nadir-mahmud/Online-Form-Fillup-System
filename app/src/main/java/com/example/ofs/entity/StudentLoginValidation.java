package com.example.ofs.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentLoginValidation {
    @SerializedName("studentId")
    @Expose
    String studentId;

    @SerializedName("password")
    @Expose
    String password;

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }
}
