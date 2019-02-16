package com.tact.database;

public class DbConstants {

    // database name and version
    public static final String DATABASE_NAME = "citizenapp.db";
    public static final int DATABASE_VERSION = 2;


    // table column data types

    public static final String DATATYPE_TEXT_WITH_COMMA = " TEXT, ";
    public static final String DATATYPE_TEXT_WITHOUT_COMMA = " TEXT ";

    public static final String DATATYPE_INT_WITH_COMMA = " INTEGER, ";
    public static final String DATATYPE_INT_WITHOUT_COMMA = " INTEGER ";

    public static final String DATATYPE_FLOAT_WITH_COMMA = " FLOAT, ";
    public static final String DATATYPE_FLOAT_WITHOUT_COMMA = " FLOAT ";


    // database tables and corresponding columns


    /**
     * budget table goes here
     */


    public static final String DB_TABLE_VOTER = " VoterTable ";


    public static final String KEY_VOTER_PRIMARY_ID = "VoterPrimarykey";
    public static final String KEY_VOTER_PREFIX = "Prefix";
    public static final String KEY_VOTER_ACNO = "AcNo";
    public static final String KEY_VOTER_AC_NAME = "AcName";
    public static final String KEY_VOTER_AcNAMEOTHER = "AcNameOther";
    public static final String KEY_VOTER_PsNO = "PsNo";
    public static final String KEY_VOTER_PsNAME = "PsName";
    public static final String KEY_VOTER_PsNAMEOTHER = "PsNameOther";
    public static final String KEY_VOTER_SECTIONNo = "SectionNo";
    public static final String KEY_VOTER_SECTION_NAME = "SectionName";
    public static final String KEY_VOTER_ID = "VoterId";
    public static final String KEY_VOTER_SERIAL = "VoterSerial";
    public static final String KEY_VOTER_NAME = "Name";
    public static final String KEY_VOTER_NAME_OTHER = "NameOther";
    public static final String KEY_VOTER_GENDER = "Gender";
    public static final String KEY_VOTER_FATHER_NAME = "FatherName";
    public static final String KEY_VOTER_FATHER_NAME_OTHER = "FatherNameOther";
    public static final String KEY_VOTER_AGE = "Age";
    public static final String KEY_VOTER_DOB = "Dob";
    public static final String KEY_VOTER_MOBILE_NO = "MobileNo";
    public static final String KEY_VOTER_HOUSE_NO = "HouseNo";
    public static final String KEY_VOTER_MOBILE_NO2 = "mobileno2";
    public static final String KEY_VOTER_PRIORITY = "priority";
    public static final String KEY_VOTER_ISSYNCED = "isSynced";


    public static final String CREATE_VOTER_TABLE_QUERY = "CREATE TABLE " + DB_TABLE_VOTER + "( "
            + KEY_VOTER_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_VOTER_PREFIX + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_ACNO + DATATYPE_INT_WITH_COMMA
            + KEY_VOTER_AC_NAME + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_AcNAMEOTHER + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_PsNO + DATATYPE_INT_WITH_COMMA
            + KEY_VOTER_PsNAME + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_PsNAMEOTHER + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_SECTIONNo + DATATYPE_INT_WITH_COMMA
            + KEY_VOTER_SECTION_NAME + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_ID + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_SERIAL + DATATYPE_INT_WITH_COMMA
            + KEY_VOTER_NAME + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_NAME_OTHER + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_GENDER + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_FATHER_NAME + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_FATHER_NAME_OTHER + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_AGE + DATATYPE_INT_WITH_COMMA
            + KEY_VOTER_DOB + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_MOBILE_NO + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_HOUSE_NO + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_MOBILE_NO2 + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_PRIORITY + DATATYPE_TEXT_WITH_COMMA
            + KEY_VOTER_ISSYNCED + DATATYPE_INT_WITHOUT_COMMA + ")";


}
