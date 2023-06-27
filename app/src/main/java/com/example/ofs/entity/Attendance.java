package com.example.ofs.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attendance {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("department")
    @Expose
    String department;

    @SerializedName("studentId")
    @Expose
    String studentId;

    @SerializedName("attendance")
    @Expose
    String attendance;

    @SerializedName("semester")
    @Expose
    String semester;

    @SerializedName("year")
    @Expose
    String year;


    public int getId(){ return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
