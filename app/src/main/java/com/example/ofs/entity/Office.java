package com.example.ofs.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Office {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("role")
    @Expose
    String role;

    @SerializedName("department")
    @Expose
    String department;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }
}
