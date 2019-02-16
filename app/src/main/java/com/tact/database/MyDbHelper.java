package com.tact.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tact.model.VoterData;
import com.tact.model.VoterData2;

import java.util.ArrayList;

import static com.tact.database.DbConstants.DB_TABLE_VOTER;
import static com.tact.database.DbConstants.KEY_VOTER_ACNO;
import static com.tact.database.DbConstants.KEY_VOTER_AC_NAME;
import static com.tact.database.DbConstants.KEY_VOTER_AGE;
import static com.tact.database.DbConstants.KEY_VOTER_AcNAMEOTHER;
import static com.tact.database.DbConstants.KEY_VOTER_DOB;
import static com.tact.database.DbConstants.KEY_VOTER_FATHER_NAME;
import static com.tact.database.DbConstants.KEY_VOTER_FATHER_NAME_OTHER;
import static com.tact.database.DbConstants.KEY_VOTER_GENDER;
import static com.tact.database.DbConstants.KEY_VOTER_HOUSE_NO;
import static com.tact.database.DbConstants.KEY_VOTER_ID;
import static com.tact.database.DbConstants.KEY_VOTER_ISSYNCED;
import static com.tact.database.DbConstants.KEY_VOTER_MOBILE_NO;
import static com.tact.database.DbConstants.KEY_VOTER_MOBILE_NO2;
import static com.tact.database.DbConstants.KEY_VOTER_NAME;
import static com.tact.database.DbConstants.KEY_VOTER_NAME_OTHER;
import static com.tact.database.DbConstants.KEY_VOTER_PREFIX;
import static com.tact.database.DbConstants.KEY_VOTER_PRIORITY;
import static com.tact.database.DbConstants.KEY_VOTER_PsNAME;
import static com.tact.database.DbConstants.KEY_VOTER_PsNAMEOTHER;
import static com.tact.database.DbConstants.KEY_VOTER_PsNO;
import static com.tact.database.DbConstants.KEY_VOTER_SECTIONNo;
import static com.tact.database.DbConstants.KEY_VOTER_SECTION_NAME;
import static com.tact.database.DbConstants.KEY_VOTER_SERIAL;

public class MyDbHelper extends SQLiteOpenHelper
{

    private final String COMMA = ",";

    public MyDbHelper(Context context)
    {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DbConstants.CREATE_VOTER_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }


    public void saveOrUpdateDataInLocalDbFromApi(ArrayList<VoterData> voterList) {


        SQLiteDatabase database = openDatabase();


        for (int i = 0; i < voterList.size(); i++) {

            VoterData voterData = voterList.get(i);


            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_VOTER_PREFIX, voterData.getPrefix());
            contentValues.put(KEY_VOTER_ACNO, voterData.getAcNo());
            contentValues.put(KEY_VOTER_AC_NAME, voterData.getAcName());
            contentValues.put(KEY_VOTER_AcNAMEOTHER, voterData.getAcNameOther());
            contentValues.put(KEY_VOTER_PsNO, voterData.getPsNo());
            contentValues.put(KEY_VOTER_PsNAME, voterData.getPsName());
            contentValues.put(KEY_VOTER_PsNAMEOTHER, voterData.getPsNameOther());
            contentValues.put(KEY_VOTER_SECTIONNo, voterData.getSectionNo());
            contentValues.put(KEY_VOTER_SECTION_NAME, voterData.getSectionName());
            contentValues.put(KEY_VOTER_ID, voterData.getVoterId());
            contentValues.put(KEY_VOTER_SERIAL, voterData.getVoterSerial());
            contentValues.put(KEY_VOTER_NAME, voterData.getName());
            contentValues.put(KEY_VOTER_NAME_OTHER, voterData.getNameOther());
            contentValues.put(KEY_VOTER_GENDER, voterData.getGender());
            contentValues.put(KEY_VOTER_FATHER_NAME, voterData.getFatherName());
            contentValues.put(KEY_VOTER_FATHER_NAME_OTHER, voterData.getFatherNameOther());
            contentValues.put(KEY_VOTER_AGE, voterData.getAge());
            contentValues.put(KEY_VOTER_DOB, voterData.getDob());
            contentValues.put(KEY_VOTER_MOBILE_NO, voterData.getMobileNo());
            contentValues.put(KEY_VOTER_HOUSE_NO, voterData.getHouseNo());
            contentValues.put(KEY_VOTER_MOBILE_NO2, "");
            contentValues.put(KEY_VOTER_PRIORITY, "0");
            contentValues.put(KEY_VOTER_ISSYNCED, 0);

            long rowId = database.insert(DB_TABLE_VOTER, null, contentValues);

            System.out.println("rowId = " + rowId);

        }
        closeDatabase(database);
    }


    public boolean updateVoterData(VoterData voterData)
    {
        SQLiteDatabase database = openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_VOTER_PREFIX, voterData.getPrefix());
        contentValues.put(KEY_VOTER_ACNO, voterData.getAcNo());
        contentValues.put(KEY_VOTER_AC_NAME, voterData.getAcName());
        contentValues.put(KEY_VOTER_AcNAMEOTHER, voterData.getAcNameOther());
        contentValues.put(KEY_VOTER_PsNO, voterData.getPsNo());
        contentValues.put(KEY_VOTER_PsNAME, voterData.getPsName());
        contentValues.put(KEY_VOTER_PsNAMEOTHER, voterData.getPsNameOther());
        contentValues.put(KEY_VOTER_PsNAME, voterData.getPsName());
        contentValues.put(KEY_VOTER_SECTIONNo, voterData.getSectionNo());
        contentValues.put(KEY_VOTER_SECTION_NAME, voterData.getSectionName());
        contentValues.put(KEY_VOTER_SERIAL, voterData.getVoterSerial());
        contentValues.put(KEY_VOTER_NAME, voterData.getName());
        contentValues.put(KEY_VOTER_NAME_OTHER, voterData.getNameOther());
        contentValues.put(KEY_VOTER_GENDER, voterData.getGender());
        contentValues.put(KEY_VOTER_FATHER_NAME, voterData.getFatherName());
        contentValues.put(KEY_VOTER_FATHER_NAME_OTHER, voterData.getFatherNameOther());
        contentValues.put(KEY_VOTER_AGE, voterData.getAge());
        contentValues.put(KEY_VOTER_DOB, voterData.getDob());
        contentValues.put(KEY_VOTER_MOBILE_NO, voterData.getMobileNo());
        contentValues.put(KEY_VOTER_HOUSE_NO, voterData.getHouseNo());
        contentValues.put(KEY_VOTER_MOBILE_NO2, voterData.getMobileNo2());
        contentValues.put(KEY_VOTER_PRIORITY, voterData.getPriority());
        contentValues.put(KEY_VOTER_ISSYNCED, voterData.getIsSynced());

        int updateId = database.update(DB_TABLE_VOTER, contentValues, KEY_VOTER_ID + "= ?", new String[]{voterData.getVoterId()});


        System.out.println("updateId = " + updateId);
        if (updateId > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    public ArrayList<VoterData> getSurveyedUnsyncedData()
    {
        SQLiteDatabase sqLiteDatabase = openDatabase();
        ArrayList<VoterData> unsyncedVoterList = new ArrayList<>();
        String query = "SELECT * FROM " + DbConstants.DB_TABLE_VOTER + " WHERE " + DbConstants.KEY_VOTER_ISSYNCED + "= 0 AND  " + DbConstants.KEY_VOTER_PRIORITY + " != 0";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++)
            {

                VoterData voterData = new VoterData();


                voterData.setPrefix(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PREFIX)));
                voterData.setAcNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_ACNO)));
                voterData.setAcName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AC_NAME)));
                voterData.setAcNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AcNAMEOTHER)));
                voterData.setPsNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_PsNO)));
                voterData.setPsName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAME)));
                voterData.setPsNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAMEOTHER)));

                voterData.setSectionNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setSectionName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setVoterId(cursor.getString(cursor.getColumnIndex(KEY_VOTER_ID)));
                voterData.setVoterSerial(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SERIAL)));
                voterData.setName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME)));
                voterData.setNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME_OTHER)));
                voterData.setGender(cursor.getString(cursor.getColumnIndex(KEY_VOTER_GENDER)));
                voterData.setFatherName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME)));
                voterData.setFatherNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME_OTHER)));
                voterData.setAge(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_AGE)));

                System.out.println("cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)) = " + cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)));

                if (cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)).isEmpty())
                {
                    voterData.setDob("1/1/1900");
                }
                else
                 {
                    voterData.setDob(cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)));

                }
                voterData.setMobileNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO)));
                voterData.setMobileNo2(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO2)));
                voterData.setHouseNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_HOUSE_NO)));
                voterData.setPriority(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PRIORITY)));

                unsyncedVoterList.add(voterData);
                cursor.moveToNext();

            }
        }

        return unsyncedVoterList;
    }


    //ayush

    public ArrayList<VoterData2> getSurveyedUnsyncedData2()
    {
        SQLiteDatabase sqLiteDatabase = openDatabase();
        ArrayList<VoterData2> unsyncedVoterList = new ArrayList<>();
        String query = "SELECT * FROM " + DbConstants.DB_TABLE_VOTER + " WHERE " + DbConstants.KEY_VOTER_ISSYNCED + "= 0 AND  " + DbConstants.KEY_VOTER_PRIORITY + " != 0";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++)
            {

                VoterData2 voterData = new VoterData2();


                voterData.setPrefix(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PREFIX)));
                voterData.setAcNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_ACNO)));
                voterData.setAcName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AC_NAME)));
                voterData.setAcNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AcNAMEOTHER)));
                voterData.setPsNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_PsNO)));
                voterData.setPsName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAME)));
                voterData.setPsNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAMEOTHER)));

                voterData.setSectionNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setSectionName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setVoterId(cursor.getString(cursor.getColumnIndex(KEY_VOTER_ID)));
                voterData.setVoterSerial(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SERIAL)));
                voterData.setName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME)));
                voterData.setNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME_OTHER)));
                voterData.setGender(cursor.getString(cursor.getColumnIndex(KEY_VOTER_GENDER)));
                voterData.setFatherName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME)));
                voterData.setFatherNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME_OTHER)));
                voterData.setAge(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_AGE)));

                System.out.println("cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)) = " + cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)));

                if (cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)).isEmpty())
                {
                    voterData.setDob("1/1/1900");
                }
                else
                {
                    voterData.setDob(cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)));

                }
                voterData.setMobileNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO)));
                voterData.setMobileNo2(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO2)));
                voterData.setHouseNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_HOUSE_NO)));
                voterData.setPriority(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PRIORITY)));

                unsyncedVoterList.add(voterData);
                cursor.moveToNext();

            }
        }

        return unsyncedVoterList;
    }

    public void deleteWholeTableData()
    {
        SQLiteDatabase sqLiteDatabase = openDatabase();
        sqLiteDatabase.delete(DbConstants.DB_TABLE_VOTER, null, null);

        closeDatabase(sqLiteDatabase);
    }


    public int getSurveyedAndUnsyncedCount() {
        SQLiteDatabase sqLiteDatabase = openDatabase();

        String query = "SELECT * FROM " + DbConstants.DB_TABLE_VOTER + " WHERE " + DbConstants.KEY_VOTER_ISSYNCED + "= 0 AND  " + DbConstants.KEY_VOTER_PRIORITY + " != 0";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            return cursor.getCount();
        }

        return 0;
    }


    private SQLiteDatabase openDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        return database;
    }


    private void closeDatabase(SQLiteDatabase database) {

        if (database != null) {
            database.close();
        }

    }


    public ArrayList<VoterData> getUnsyncedData()
    {
        SQLiteDatabase sqLiteDatabase = openDatabase();
        ArrayList<VoterData> unsyncedVoterList = new ArrayList<>();
        String query = "SELECT * FROM " + DbConstants.DB_TABLE_VOTER + " WHERE " + DbConstants.KEY_VOTER_ISSYNCED + "= 0 AND  " + DbConstants.KEY_VOTER_PRIORITY + " = 0";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                VoterData voterData = new VoterData();


                voterData.setPrefix(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PREFIX)));
                voterData.setAcNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_ACNO)));
                voterData.setAcName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AC_NAME)));
                voterData.setAcNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AcNAMEOTHER)));
                voterData.setPsNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_PsNO)));
                voterData.setPsName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAME)));
                voterData.setPsNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAMEOTHER)));

                voterData.setSectionNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setSectionName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setVoterId(cursor.getString(cursor.getColumnIndex(KEY_VOTER_ID)));
                voterData.setVoterSerial(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SERIAL)));
                voterData.setName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME)));
                voterData.setNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME_OTHER)));
                voterData.setGender(cursor.getString(cursor.getColumnIndex(KEY_VOTER_GENDER)));
                voterData.setFatherName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME)));
                voterData.setFatherNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME_OTHER)));
                voterData.setAge(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_AGE)));
                voterData.setDob(cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)));
                voterData.setMobileNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO)));
                voterData.setMobileNo2(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO2)));
                voterData.setHouseNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_HOUSE_NO)));
                voterData.setPriority(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PRIORITY)));

                unsyncedVoterList.add(voterData);
                cursor.moveToNext();
            }
        }
        return unsyncedVoterList;
    }


    public ArrayList<VoterData> getSyncedData()
    {
        SQLiteDatabase sqLiteDatabase = openDatabase();
        ArrayList<VoterData> syncedVoterList = new ArrayList<>();
        String query = "SELECT * FROM " + DbConstants.DB_TABLE_VOTER + " WHERE " + /*DbConstants.KEY_VOTER_ISSYNCED + "= 1 AND  "+ */ DbConstants.KEY_VOTER_PRIORITY + " != 0";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++)
            {
                VoterData voterData = new VoterData();

                voterData.setPrefix(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PREFIX)));
                voterData.setAcNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_ACNO)));
                voterData.setAcName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AC_NAME)));
                voterData.setAcNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_AcNAMEOTHER)));
                voterData.setPsNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_PsNO)));
                voterData.setPsName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAME)));
                voterData.setPsNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PsNAMEOTHER)));

                voterData.setSectionNo(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setSectionName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_SECTIONNo)));
                voterData.setVoterId(cursor.getString(cursor.getColumnIndex(KEY_VOTER_ID)));
                voterData.setVoterSerial(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_SERIAL)));
                voterData.setName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME)));
                voterData.setNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_NAME_OTHER)));
                voterData.setGender(cursor.getString(cursor.getColumnIndex(KEY_VOTER_GENDER)));
                voterData.setFatherName(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME)));
                voterData.setFatherNameOther(cursor.getString(cursor.getColumnIndex(KEY_VOTER_FATHER_NAME_OTHER)));
                voterData.setAge(cursor.getInt(cursor.getColumnIndex(KEY_VOTER_AGE)));
                voterData.setDob(cursor.getString(cursor.getColumnIndex(KEY_VOTER_DOB)));
                voterData.setMobileNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO)));
                voterData.setMobileNo2(cursor.getString(cursor.getColumnIndex(KEY_VOTER_MOBILE_NO2)));
                voterData.setHouseNo(cursor.getString(cursor.getColumnIndex(KEY_VOTER_HOUSE_NO)));
                voterData.setPriority(cursor.getString(cursor.getColumnIndex(KEY_VOTER_PRIORITY)));

                syncedVoterList.add(voterData);
                cursor.moveToNext();
            }
        }
        return syncedVoterList;
    }


}
