package com.tact.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tact.database.MyDbHelper;
import com.tact.kumbhca.R;
import com.tact.model.VoterData;
import com.tact.service.SyncingService;
import com.tact.utils.Support;

public class VoterDetailActivity extends AppCompatActivity
{
    private Context context;

    private TextView tv_voter_ser_id;
    private TextView tv_voter_id;
    private TextView tv_house_no;

    private TextView tv_name;
    private TextView tv_gender;
    private TextView tv_father_name;
    private TextView tv_age;
    private TextView tv_dob;
    private EditText tv_mobile_no1;
    private EditText tv_mobile_no2;
    private RadioButton radio_very_important;
    private RadioButton radio_important;
    private RadioButton radio_as_usual;
    private RadioButton radio_not_interested;
    private AppCompatButton btn_save;
    private RadioGroup radioGroup;
    private VoterData voterData;
    private MyDbHelper myDbHelper;
    private String priority = "1";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_detail);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initWidgets();
        setupUI();
        initWidgetListener();

        initToolBar("Survey");
    }

    // initializing the toolbar
    private void initToolBar(String title)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home)
        {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }


    private void initWidgets()
    {
        context = VoterDetailActivity.this;

        tv_voter_ser_id = findViewById(R.id.tv_voter_ser_id);
        tv_voter_id = findViewById(R.id.tv_voter_id);
        tv_house_no = findViewById(R.id.tv_house_no);

        tv_name = findViewById(R.id.tv_name);
        tv_gender = findViewById(R.id.tv_gender);
        tv_father_name = findViewById(R.id.tv_father_name);
        tv_age = findViewById(R.id.tv_age);
       // tv_dob = findViewById(R.id.tv_dob);
        tv_mobile_no1 = findViewById(R.id.tv_mobile_no);
        tv_mobile_no2 = findViewById(R.id.tv_mobile_no2);
        radio_very_important = findViewById(R.id.radio_very_important);
        radio_important = findViewById(R.id.radio_important);
        radio_as_usual = findViewById(R.id.radio_as_usual);
        radio_not_interested = findViewById(R.id.radio_not_interested);
        btn_save = findViewById(R.id.btn_save);
        radioGroup = findViewById(R.id.radioGroup);
        myDbHelper = new MyDbHelper(context);

        voterData = (VoterData) getIntent().getSerializableExtra("VoterData");

    }

    private void initWidgetListener()
    {
        btn_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isAllDetailValid())
                {
                    updateDataInLocalDatabase();
                }

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {

                    case R.id.radio_very_important:
                        priority = "1";
                        break;
                    case R.id.radio_important:

                        priority = "2";
                        break;
                    case R.id.radio_as_usual:
                        priority = "3";
                        break;
                    case R.id.radio_not_interested:
                        priority = "4";
                        break;

                }


            }
        });
    }


    private void setupUI()
    {
        tv_voter_ser_id.setText(voterData.getVoterSerial()+"");
        tv_voter_id.setText(voterData.getVoterId()+"");
        tv_house_no.setText(voterData.getHouseNo()+"");

        tv_name.setText(voterData.getName()+"");
        tv_father_name.setText(voterData.getFatherName()+"");
        tv_age.setText(voterData.getAge() + "");
        tv_gender.setText(voterData.getGender()+"");

        tv_mobile_no1.setText((String) voterData.getMobileNo());
    }

    private boolean isAllDetailValid()
    {
        if (tv_mobile_no1.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Please enter at least one mobile no.", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


    private void updateDataInLocalDatabase()
    {
        voterData.setMobileNo(tv_mobile_no1.getText().toString());
        voterData.setMobileNo2(tv_mobile_no2.getText().toString());
        voterData.setPriority(priority);
        voterData.setIsSynced(0);
        boolean isupdated = myDbHelper.updateVoterData(voterData);
        if (isupdated)
        {
            Toast.makeText(context, "Data saved successfully", Toast.LENGTH_SHORT).show();
            finish();
            if (Support.isNetworkOnline(context))
            {
                System.out.println("internet has been connected = " + context);
                startService(context);
            }
        }

        finish();
    }

    private void startService(Context context)
    {
        Intent intent = new Intent(context, SyncingService.class);
        context.startService(intent);
    }


    @Override
    public void onBackPressed()
    {
        if (new MyDbHelper(context).getSurveyedAndUnsyncedCount() > 0)
        {

        }
        else
        {
            super.onBackPressed();
        }
    }
}
