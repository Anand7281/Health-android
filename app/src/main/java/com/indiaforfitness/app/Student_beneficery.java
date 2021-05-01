package com.indiaforfitness.app;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class Student_beneficery {

    public String regNo="JH11A10000", dob, name, mobile, parent, aadhar_number,gender, type,address,state,pin,district,school,std_class,section;
    public int age;
    public Student_beneficery(String mRegNo, int age, String mName, String mDob, String mMobile, String mParent, String mAadhar, String mGender, String mAddress, String mState, String mPin, String mDistrict, String mSchool, String mClass, String mSec){
         this.age =age;
         this.name = mName;
         this.dob = mDob;
         this.mobile = mMobile;
         this.parent = mParent;
         this.aadhar_number = mAadhar;
         this.gender = mGender;
         this.type = "School Student";
         this.address = mAddress;
         this.state = mState;
         this.pin = mPin;
         this.district = mDistrict;
         this.school = mSchool;
         this.std_class = mClass;
         this.section = mSec;
         this.regNo = mRegNo;

    }

    public String getAadhar_number() {
        return aadhar_number;
    }

    public String getType() {
        return type;
    }

    public String getStd_class() {
        return std_class;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getState() {
        return state;
    }

    public String getSection() {
        return section;
    }

    public String getSchool() {
        return school;
    }

    public String getPin() {
        return pin;
    }

    public String getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getDistrict() {
        return district;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
