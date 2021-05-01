package com.indiaforfitness.app;

public class GeneralPublic_beneficiary {
    public String regNo="JH11D10000", dob, name, mobile,aadhar_number,gender, type,address,state,pin,district;
    public int age;

    public GeneralPublic_beneficiary(){

    }

    public GeneralPublic_beneficiary( String mRegNo, int age, String mName, String mDob, String mMobile, String mAadhar, String mGender, String mAddress, String mState, String mPin, String mDistrict){
        this.age =age;
        this.name = mName;
        this.dob = mDob;
        this.mobile = mMobile;
        this.aadhar_number = mAadhar;
        this.gender = mGender;
        this.type = "General Public";
        this.address = mAddress;
        this.state = mState;
        this.pin = mPin;
        this.district = mDistrict;
        this.regNo = mRegNo;

    }


    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getAadhar_number() {
        return aadhar_number;
    }

    public String getType() {
        return type;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getState() {
        return state;
    }

    public String getPin() {
        return pin;
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
