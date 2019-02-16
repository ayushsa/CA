package com.tact.network;

import com.tact.model.GetVotersRequestModel;
import com.tact.model.GetVotersResponseModel;
import com.tact.model.PollingStationModel;
import com.tact.model.PollingStationOneModel;
import com.tact.model.SignInOneModel;
import com.tact.model.SignInSendModel;
import com.tact.model.SyncedVoterResponseModel;
import com.tact.model.VoterData;
import com.tact.model.VoterData2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiClientInterface {


    @POST("api/CA/SignIn")
    Call<SignInOneModel> signIn(@Body SignInSendModel retrofit);


    @POST("api/CA/GetPolingStation")
    Call<PollingStationOneModel> getPollingStation(@Body PollingStationModel retrofit);

    @POST("api/CA/GetVoters")
    Call<GetVotersResponseModel> getVoters(@Body GetVotersRequestModel getVotersRequestModel);


    @POST("api/CA/SyncVoter")
    Call<SyncedVoterResponseModel> syncVoters(@Body VoterData2 voterData);


    @POST("api/CA/GetServeyedVoters")
    Call<GetVotersResponseModel> getSurveyedVoters(@Body GetVotersRequestModel getVotersRequestModel);
}