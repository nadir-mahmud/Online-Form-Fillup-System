package com.example.ofs.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registrar {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("department")
    @Expose
    String department;

    @SerializedName("initialDate")
    @Expose
    String initialDate;

    @SerializedName("deadlineWithoutFine")
    @Expose
    String deadlineWithoutFine;

    @SerializedName("deadlineWithFine")
    @Expose
    String deadlineWithFine;

    @SerializedName("feePerCredit")
    @Expose
    int feePerCredit;

    @SerializedName("finePerDay")
    @Expose
    int finePerDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getDeadlineWithoutFine() {
        return deadlineWithoutFine;
    }

    public void setDeadlineWithoutFine(String deadlineWithoutFine) {
        this.deadlineWithoutFine = deadlineWithoutFine;
    }

    public String getDeadlineWithFine() {
        return deadlineWithFine;
    }

    public void setDeadlineWithFine(String deadlineWithFine) {
        this.deadlineWithFine = deadlineWithFine;
    }

    public int getFeePerCredit() {
        return feePerCredit;
    }

    public void setFeePerCredit(int feePerCredit) {
        this.feePerCredit = feePerCredit;
    }

    public int getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(int finePerDay) {
        this.finePerDay = finePerDay;
    }

}
