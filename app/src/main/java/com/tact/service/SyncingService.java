package com.tact.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tact.database.MyDbHelper;
import com.tact.model.SyncedVoterResponseModel;
import com.tact.model.VoterData;
import com.tact.model.VoterData2;
import com.tact.network.ApiClient;
import com.tact.network.ApiClientInterface;
import com.tact.utils.Constant;
import com.tact.utils.SharedPreference;
import com.tact.utils.Support;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncingService extends IntentService {

    private MyDbHelper myDbHelper;
    ApiClientInterface apiClientInterface;
    SharedPreference sharedPreference;

    public SyncingService() {
        super("syncing");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        myDbHelper = new MyDbHelper(getApplicationContext());
        apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        sharedPreference = new SharedPreference(getApplicationContext());
        startSyncingProcess();
    }


    private void startSyncingProcess()
    {
        ArrayList<VoterData> unsyncedVoterList = myDbHelper.getSurveyedUnsyncedData();

        ArrayList<VoterData2> unsyncedVoterList2 = myDbHelper.getSurveyedUnsyncedData2();

        for (int i = 0; i < unsyncedVoterList.size(); i++)
        {
            final VoterData voterData = unsyncedVoterList.get(i);

            final VoterData2 voterData2 = unsyncedVoterList2.get(i);

            if (voterData2.getDob() == null || voterData2.getDob().isEmpty())
            {
                voterData2.setDob("1/1/1900");
            }
            voterData2.setUserId(sharedPreference.getValueInt(Constant.KEY_USER_ID));
            Log.d("Ayush" , voterData.toString());

            apiClientInterface.syncVoters(voterData2).enqueue(new Callback<SyncedVoterResponseModel>()
            {
                @Override
                public void onResponse(Call<SyncedVoterResponseModel> call, Response<SyncedVoterResponseModel> response)
                {
                    if (response.isSuccessful())
                    {
                        voterData.setIsSynced(1);
                        myDbHelper.updateVoterData(voterData);
                        Support.showToast(getApplicationContext(), "Sync success");
                    }
                    else
                    {
                        Support.showToast(getApplicationContext(), "Server error");
                    }
                }

                @Override
                public void onFailure(Call<SyncedVoterResponseModel> call, Throwable t)
                {
                    t.printStackTrace();
                }
            });

        }
    }



}
