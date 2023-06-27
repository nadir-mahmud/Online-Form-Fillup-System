package com.example.ofs.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileBank {

    @SerializedName("mobile_number")
    @Expose
    String mobile_number;

    @SerializedName("pin_number")
    @Expose
    String pin_number;

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getPinNumber() {
        return pin_number;
    }

    public void setPin_number(String pin_number) {
        this.pin_number = pin_number;
    }


}
