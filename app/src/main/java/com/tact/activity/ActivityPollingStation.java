package com.tact.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.tact.kumbhca.R;
import com.tact.model.PollingStationModel;
import com.tact.model.PollingStationOneModel;
import com.tact.model.PollingStationTwoModel;
import com.tact.network.ApiClient;
import com.tact.network.ApiClientInterface;
import com.tact.utils.Constant;
import com.tact.utils.SharedPreference;
import com.tact.utils.Support;
import com.trend.progress.ProgressDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPollingStation extends AppCompatActivity
{
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    String[] permissions = new String[]{
            android.Manifest.permission.WRITE_CONTACTS, android.Manifest.permission.CALL_PHONE};
    //variable used to store the permissions asked from the user

    ApiClientInterface apiClientInterface;
    ProgressDialog progressBar;
    private ListView lvPollingStation;
    private SharedPreference sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling_station);

        sp = new SharedPreference(this);
        apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        progressBar = new ProgressDialog(this);
        progressBar.setBarColor(getResources().getColor(Constant.DEFAULT_PROGRESS_COLOR));

        init();
        initToolBar("Polling Station");

        getDataOnline(1, 122, "AC_122");
    }


    private void getDataOnline(int userId, int accountNo, String prefix)
    {
        progressBar.show();

        PollingStationModel psm = new PollingStationModel();
        psm.setUserId(userId);
        psm.setAcNo(accountNo);
        psm.setPrefix(prefix);


        apiClientInterface.getPollingStation(psm).enqueue(new Callback<PollingStationOneModel>()
        {
            @Override
            public void onResponse(Call<PollingStationOneModel> call, Response<PollingStationOneModel> response)
            {
                progressBar.dismiss();
                Log.d("AYUSH", response.isSuccessful() + "");

                if (response.isSuccessful())
                {
                    List<PollingStationTwoModel> items = response.body().getData();

                    if (items.size() == 0)
                    {
                        lvPollingStation.setVisibility(View.GONE);
                    }
                    else
                    {
                        setAdapter(items);
                    }

                }
                else
                {
                    Support.showToast(ActivityPollingStation.this, "Check your network");
                }
            }

            @Override
            public void onFailure(Call<PollingStationOneModel> call, Throwable t)
            {
                Log.d("Error", t.getMessage());
            }
        });
    }


    private void init()
    {
        lvPollingStation = (ListView) findViewById(R.id.lvPollingStation);
    }

    //initializes the toolbar
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

    //sets the adapter for the ulb voter list in the list view
    private void setAdapter(List<PollingStationTwoModel> items)
    {
//        AMPRViolationsAdapter adapter = new AMPRViolationsAdapter(AMPRViolationRecords.this, items);
//        lvAMPRViolationRecords.setAdapter(adapter);
    }

}
