package com.example.ofs.entity;

import java.util.ArrayList;
import java.util.List;

public class AttendanceList {
    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    List<Attendance> attendance = new ArrayList<>();
}
