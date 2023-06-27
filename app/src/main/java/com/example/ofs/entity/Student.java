package com.example.ofs.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("studentId")
    @Expose
    String studentId;

    @SerializedName("fatherName")
    @Expose
    String fatherName;

    @SerializedName("motherName")
    @Expose
    String motherName;

    @SerializedName("department")
    @Expose
    String department;

    @SerializedName("hallName")
    @Expose
    String hallName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDepartment() {
        return department;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

}
