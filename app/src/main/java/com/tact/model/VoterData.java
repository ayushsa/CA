package com.tact.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VoterData implements Serializable {
    @SerializedName("Prefix")
    private String prefix;

    @SerializedName("AcNo")
    private int acNo;

    @SerializedName("AcName")
    private String acName;

    @SerializedName("AcNameOther")
    private String acNameOther;

    @SerializedName("PsNo")
    private int psNo;

    @SerializedName("PsName")
    private String psName;

    @SerializedName("PsNameOther")
    private String psNameOther;

    @SerializedName("SectionNo")
    private int sectionNo;

    @SerializedName("SectionName")
    private String sectionName;

    @SerializedName("VoterId")
    private String voterId;

    @SerializedName("VoterSerial")
    private int voterSerial;

    @SerializedName("Name")
    private String name;

    @SerializedName("NameOther")
    private String nameOther;

    @SerializedName("Gender")
    private String gender;

    @SerializedName("FatherName")
    private String fatherName;

    @SerializedName("FatherNameOther")
    private String fatherNameOther;

    @SerializedName("Age")
    private int age;

    @SerializedName("Dob")
    private String dob = "";

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("HouseNo")
    private String houseNo;

    @SerializedName("Status")
    private String priority;


    @SerializedName("MobileNo1")
    private String mobileNo2;


    @SerializedName("Address")
    private String Address = "testAddress";


    @SerializedName("UserId")
    private int UserId;

    private int isSynced = 0;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getMobileNo2() {
        return mobileNo2;
    }

    public void setMobileNo2(String mobileNo2) {
        this.mobileNo2 = mobileNo2;
    }

    public int getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(int isSynced) {
        this.isSynced = isSynced;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getAcNo() {
        return acNo;
    }

    public void setAcNo(int acNo) {
        this.acNo = acNo;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcNameOther() {
        return acNameOther;
    }

    public void setAcNameOther(String acNameOther) {
        this.acNameOther = acNameOther;
    }

    public int getPsNo() {
        return psNo;
    }

    public void setPsNo(int psNo) {
        this.psNo = psNo;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getPsNameOther() {
        return psNameOther;
    }

    public void setPsNameOther(String psNameOther) {
        this.psNameOther = psNameOther;
    }

    public int getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(int sectionNo) {
        this.sectionNo = sectionNo;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public int getVoterSerial() {
        return voterSerial;
    }

    public void setVoterSerial(int voterSerial) {
        this.voterSerial = voterSerial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOther() {
        return nameOther;
    }

    public void setNameOther(String nameOther) {
        this.nameOther = nameOther;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherNameOther() {
        return fatherNameOther;
    }

    public void setFatherNameOther(String fatherNameOther) {
        this.fatherNameOther = fatherNameOther;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
}
