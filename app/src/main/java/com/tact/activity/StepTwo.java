package com.tact.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tact.database.MyDbHelper;
import com.tact.kumbhca.R;
import com.tact.model.GetVotersRequestModel;
import com.tact.model.GetVotersResponseModel;
import com.tact.model.PollingStationModel;
import com.tact.model.PollingStationOneModel;
import com.tact.model.PollingStationTwoModel;
import com.tact.model.VoterData;
import com.tact.network.ApiClient;
import com.tact.network.ApiClientInterface;
import com.tact.utils.Constant;
import com.tact.utils.SharedPreference;
import com.tact.utils.Support;
import java.util.ArrayList;
import java.util.List;
import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StepTwo extends AppCompatActivity
{
    ApiClientInterface apiClientInterface;
    private TextView tvPAreaName;
    private int user_id;
    private int ac_no;
    private String prefix;
    private SharedPreference sp;
    private TextView tvPickedUserAccount;
    private SearchableSpinner mSearchableSpinner3;
    private SimpleListAdapter mSimpleListAdapter;
    private List<PollingStationTwoModel> listPollingStation;
    private final ArrayList<String> mStrings = new ArrayList<>();
    private int PS_NO;
    private String assemblyName;
    String area_name;
    public int pos = 0;
    private GetVotersResponseModel getVotersResponseModel;
    private ArrayList<VoterData> voterList = new ArrayList<>();
    private MyDbHelper myDbHelper;


    private ProgressDialog pd;
    private String pollingStationName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);
        sp = new SharedPreference(this);
        myDbHelper = new MyDbHelper(this);
        apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        pd = new android.app.ProgressDialog(StepTwo.this);

        mSimpleListAdapter = new SimpleListAdapter(this, mStrings);
        initToolBar("Step: 2");
        init();
        getDataFromBundle();
        getPollingStation(user_id, ac_no, prefix);
    }


    private void getPollingStation(int user_id, int ac_no, String prefix)
    {

        pd.setMessage("Fetching Polling Stations for " + assemblyName);
        pd.setCancelable(false);
        pd.show();

        PollingStationModel psm = new PollingStationModel();
        psm.setUserId(user_id);
        psm.setAcNo(ac_no);
        psm.setPrefix(prefix);

        apiClientInterface.getPollingStation(psm).enqueue(new Callback<PollingStationOneModel>()
        {
            @Override
            public void onResponse(Call<PollingStationOneModel> call, Response<PollingStationOneModel> response)
            {
                pd.dismiss();

                if (response.isSuccessful())
                {
                    if (response.body().getStatusCode() == 200)
                    {
                        listPollingStation = response.body().getData();

                        for (int i =0 ; i < listPollingStation.size(); i++)
                        {
                            mStrings.add(listPollingStation.get(i).getPsNo() + "-" +  listPollingStation.get(i).getPsName());
                        }
                    }
                    else if (response.body().getStatusCode() == 500)
                    {
                        Support.showAlertDialog(StepTwo.this, "Server Error");
                    }
                }
                else
                {
                    Support.showAlertDialog(StepTwo.this, "Server Error!");
                }
            }

            @Override
            public void onFailure(Call<PollingStationOneModel> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
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



    private void init()
    {
        tvPAreaName = (TextView) findViewById(R.id.tvPAreaName);
        tvPickedUserAccount = (TextView) findViewById(R.id.tvPickedUserAccount);


        mSearchableSpinner3 = (SearchableSpinner) findViewById(R.id.SearchableSpinner3);
        mSearchableSpinner3.setAdapter(mSimpleListAdapter);

        mSearchableSpinner3.setOnItemSelectedListener(mOnItemSelectedListener);
        mSearchableSpinner3.setStatusListener(new IStatusListener()
        {
            @Override
            public void spinnerIsOpening()
            {
                mSearchableSpinner3.hideEdit();
            }

            @Override
            public void spinnerIsClosing()
            {

            }
        });


        Button appCompatButtonLogin = (Button) findViewById(R.id.appCompatButtonLogin);
        appCompatButtonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (pos != 0)
                {
                    callGetVotersData(PS_NO, ac_no, prefix);
                   // getSyncedVoterData(PS_NO, ac_no, prefix);
                }
                else
                {
                    Support.showAlertDialog(StepTwo.this, "Select polling station");
                }
            }
        });
    }


    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener()
    {
        @Override
        public void onItemSelected(View view, int position, long id)
        {
            try
            {
                // Toast.makeText(StepTwo.this,  listPollingStation.get(position).getPsName()+"", Toast.LENGTH_SHORT).show();

                pollingStationName = (String) mSimpleListAdapter.getItem(position);
                String[] seprated =  pollingStationName.split("-");

                Support.showToast(StepTwo.this , seprated[1]);
                PS_NO = Integer.parseInt(seprated[0]);

                pos = position;

                sp.setValueString("PS_NAME" , pollingStationName);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onNothingSelected()
        {
            Toast.makeText(StepTwo.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };


    private void getDataFromBundle()
    {
        Bundle b = getIntent().getExtras();

        if (b != null)
        {
            user_id = b.getInt("USER_ID");
            ac_no = b.getInt("AC_NO");
            prefix = b.getString("Prefix");
            assemblyName = b.getString("ASSEMBLY_NAME");
            area_name = b.getString("PAN");

            tvPAreaName.setText(b.getString("PAN"));
            tvPickedUserAccount.setText(assemblyName);


            sp.setValueString("PAN", b.getString("PAN"));
            sp.setValueString("ASSEMBLY_NAME", assemblyName);
        }
    }


    private void callGetVotersData(int psNo, int acNo, final String prefix)
    {
        pd.setMessage("Fetching citizen records for " + pollingStationName);
        pd.setCancelable(false);
        pd.show();

        GetVotersRequestModel item = new GetVotersRequestModel();
        item.setAcNo(acNo);
        item.setPsNo(psNo);
        item.setPrefix(prefix);

        apiClientInterface.getVoters(item).enqueue(new Callback<GetVotersResponseModel>()
        {
            @Override
            public void onResponse(Call<GetVotersResponseModel> call, Response<GetVotersResponseModel> response)
            {
                pd.dismiss();

                if (response.isSuccessful())
                {
                    myDbHelper.deleteWholeTableData();
                    getVotersResponseModel = response.body();
                    if (getVotersResponseModel.getStatusCode() == 200)
                    {
                        voterList = getVotersResponseModel.getVoterList();
                        myDbHelper.saveOrUpdateDataInLocalDbFromApi(voterList);

                        getSyncedVoterData(PS_NO, ac_no, prefix);


//                        Intent intent = new Intent(StepTwo.this, CitizenDashboard.class);
//                        intent.putExtra("PsNo", PS_NO);
//                        intent.putExtra("AcNo", ac_no);
//                        intent.putExtra("Prefix", prefix);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    }
                }
                else
                {
                    Toast.makeText(StepTwo.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetVotersResponseModel> call, Throwable t)
            {
                t.printStackTrace();
                pd.dismiss();
            }
        });

    }


    private void getSyncedVoterData(int psNo, int acNo, final String prefix)
    {
        GetVotersRequestModel item = new GetVotersRequestModel();
        item.setAcNo(acNo);
        item.setPsNo(psNo);
        item.setPrefix(prefix);
        item.setUserId(sp.getValueInt(Constant.KEY_USER_ID));


        apiClientInterface.getSurveyedVoters(item).enqueue(new Callback<GetVotersResponseModel>()
        {
            @Override
            public void onResponse(Call<GetVotersResponseModel> call, Response<GetVotersResponseModel> response)
            {
                if (response.isSuccessful())
                {
                   // myDbHelper.deleteWholeTableData();

                    GetVotersResponseModel getVotersResponseModel = response.body();

                    if (getVotersResponseModel.getStatusCode() == 200)
                    {
                        voterList = getVotersResponseModel.getVoterList();

                        for(int i=0 ; i < voterList.size(); i++)
                        {
                            VoterData voterData = new VoterData();
                            voterData.setAcNo(voterList.get(i).getAcNo());
                            voterData.setAcName(voterList.get(i).getAcName());
                            voterData.setAcNameOther(voterList.get(i).getAcNameOther());
                            voterData.setPsNo(voterList.get(i).getPsNo());
                            voterData.setPsName(voterList.get(i).getPsName());
                            voterData.setPsNameOther(voterList.get(i).getPsNameOther());
                            voterData.setSectionNo(voterList.get(i).getSectionNo());
                            voterData.setSectionName(voterList.get(i).getSectionName());
                            voterData.setVoterId(voterList.get(i).getVoterId());
                            voterData.setVoterSerial(voterList.get(i).getVoterSerial());
                            voterData.setName(voterList.get(i).getName());
                            voterData.setNameOther(voterList.get(i).getNameOther());
                            voterData.setGender(voterList.get(i).getGender());
                            voterData.setFatherName(voterList.get(i).getFatherName());
                            voterData.setFatherNameOther(voterList.get(i).getFatherNameOther());
                            voterData.setAge(voterList.get(i).getAge());
                            voterData.setDob(voterList.get(i).getDob());
                            voterData.setMobileNo(voterList.get(i).getMobileNo());
                            voterData.setMobileNo2(voterList.get(i).getMobileNo2());
                            voterData.setHouseNo(voterList.get(i).getHouseNo());
                            voterData.setIsSynced(1);
                            switch (voterList.get(i).getPriority())
                            {
                                case "Very Important":
                                    voterData.setPriority("1");
                                    break;
                                case "Important":
                                    voterData.setPriority("2");
                                    break;
                                case "As usual":
                                    voterData.setPriority("3");
                                    break;
                                case "Not intrested":
                                    voterData.setPriority("4");
                                    break;
                            }


                            boolean isupdated = myDbHelper.updateVoterData(voterData);
                            if (isupdated)
                            {
                                //Toast.makeText(StepTwo.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                            }
                        }

                        //myDbHelper.saveOrUpdateDataInLocalDbFromApi(unSyncedVoterData);
                        Intent intent = new Intent(StepTwo.this, CitizenDashboard.class);
                        intent.putExtra("PsNo", PS_NO);
                        intent.putExtra("AcNo", ac_no);
                        intent.putExtra("Prefix", prefix);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    }
                }
                else
                {
                    Toast.makeText(StepTwo.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetVotersResponseModel> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }
}
