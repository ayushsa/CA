package com.tact.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tact.database.MyDbHelper;
import com.tact.kumbhca.R;
import com.tact.model.GetVotersRequestModel;
import com.tact.model.GetVotersResponseModel;
import com.tact.model.VoterData;
import com.tact.network.ApiClient;
import com.tact.network.ApiClientInterface;
import com.tact.utils.DialogPickImage;
import com.tact.utils.Support;
import com.trend.progress.ProgressDialog;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnserveyedFragment extends Fragment
{
    View view;
    private Context context;
    private MyDbHelper myDbHelper;
    private ApiClientInterface apiClientInterface;

    private GetVotersResponseModel getVotersResponseModel;
    private ArrayList<VoterData> voterList = new ArrayList<>();
    private ArrayList<VoterData> voterList_2 = new ArrayList<VoterData>();

    private RecyclerView recyclerview_all;
    ProgressDialog progressBar;
    private  int pickType = 0;
    private  String search;
    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabClear;
    private LinearLayout layoutFabSubSearch;
    private TextView tvSurveyedData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_unserveyed, container, false);
        initWidgets();

        return view;
    }

    private void initWidgets()
    {
        context = getActivity();
        apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        recyclerview_all = view.findViewById(R.id.recyclerview_all);

        fabSettings = view.findViewById(R.id.fabSetting);
        tvSurveyedData  = view.findViewById(R.id.tvSurveyedData);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);


        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, resId);
        recyclerview_all.setLayoutAnimation(animation);

        recyclerview_all.setLayoutManager(linearLayoutManager);

        progressBar = new ProgressDialog(context);
        progressBar.setBarColor(getResources().getColor(R.color.red));
        myDbHelper = new MyDbHelper(context);

        layoutFabSubSearch = (LinearLayout) view.findViewById(R.id.layoutFabSubSearch);
        layoutFabClear = (LinearLayout) view.findViewById(R.id.layoutFabClear);

        layoutFabSubSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DialogPickImage(context, false, new DialogPickImage.DialogPickImageResponse()
                {
                    @Override
                    public void onOptionSelected(int PickType, String searchString)
                    {
                        closeSubMenusFab();

                        pickType = PickType;
                        search = searchString;
                        getDataFromDb(pickType, search);
                    }
                });
            }
        });

        layoutFabClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                closeSubMenusFab();

                Support.showSnackbar(context, "Filters removed");
                getDataFromDb(0, "");
            }
        });

        fabSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (fabExpanded == true)
                {
                    closeSubMenusFab();
                }
                else
                {
                    openSubMenusFab();
                }
            }
        });

        closeSubMenusFab();
    }


    //closes FAB submenus
    private void closeSubMenusFab()
    {
        layoutFabClear.setVisibility(View.INVISIBLE);
        layoutFabSubSearch.setVisibility(View.INVISIBLE);
        fabSettings.setImageResource(android.R.drawable.ic_menu_search);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab()
    {
        layoutFabClear.setVisibility(View.VISIBLE);
        layoutFabSubSearch.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fabSettings.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        fabExpanded = true;
    }



    private void callGetVotersData(int psNo, int acNo, String prefix) {
        progressBar.show();

        GetVotersRequestModel item = new GetVotersRequestModel();
        item.setAcNo(acNo);
        item.setPsNo(psNo);
        item.setPrefix(prefix);

        //final GetVotersRequestModel getVotersRequestModel = createGetVotersRequestModel();

        apiClientInterface.getVoters(item).enqueue(new Callback<GetVotersResponseModel>() {
            @Override
            public void onResponse(Call<GetVotersResponseModel> call, Response<GetVotersResponseModel> response) {
                progressBar.dismiss();

                getVotersResponseModel = response.body();
                if (getVotersResponseModel.getStatusCode() == 200)
                {
                    voterList = getVotersResponseModel.getVoterList();


                    setupAadapter(voterList_2);
                    myDbHelper.saveOrUpdateDataInLocalDbFromApi(voterList);

                }
            }

            @Override
            public void onFailure(Call<GetVotersResponseModel> call, Throwable t) {
                progressBar.dismiss();
            }
        });

    }


    private GetVotersRequestModel createGetVotersRequestModel()
    {
        GetVotersRequestModel getVotersRequestModel = new GetVotersRequestModel();
        return getVotersRequestModel;
    }

    private void getDataFromDb(int pickType, String searchString)
    {
        voterList = myDbHelper.getUnsyncedData();

        voterList_2.clear();

        if (pickType == 0)
        {
            setupAadapter(voterList);
        }

        else if (pickType == 1)
        {
            int searchSerialNumber = Integer.parseInt(searchString);

            for (int i=0; i< voterList.size(); i++)
            {
                if (voterList.get(i).getVoterSerial() == searchSerialNumber)
                {
                   voterList_2.add(voterList.get(i));
                }
            }

            setupAadapter(voterList_2);
        }

        else if (pickType == 2)
        {

            for (int i=0; i< voterList.size(); i++)
            {
                if (voterList.get(i).getVoterId().contains(searchString))
                {
                    voterList_2.add(voterList.get(i));
                }
            }

            setupAadapter(voterList_2);
        }

        else if (pickType == 3)
        {
            for (int i=0; i< voterList.size(); i++)
            {
                if (voterList.get(i).getName().contains(searchString))
                {
                    voterList_2.add(voterList.get(i));
                }
            }

            setupAadapter(voterList_2);
        }

        else if (pickType == 4)
        {
            for (int i=0; i< voterList.size(); i++)
            {
                if (voterList.get(i).getHouseNo().contains(searchString))
                {
                    voterList_2.add(voterList.get(i));
                }
            }
            setupAadapter(voterList_2);
        }

    }

    private void setupAadapter(ArrayList<VoterData> voterList_2)
    {
        if (voterList_2.size() <=0)
        {
            recyclerview_all.setVisibility(View.GONE);
            tvSurveyedData.setVisibility(View.VISIBLE);
            tvSurveyedData.setText("Sorry, No record found");
        }
        else
        {
            recyclerview_all.setVisibility(View.VISIBLE);
            tvSurveyedData.setVisibility(View.GONE);

            UnsurveyedDataAdapter unsurveyedDataAdapter = new UnsurveyedDataAdapter(context, voterList_2);
            recyclerview_all.setAdapter(unsurveyedDataAdapter);
        }
    }



    @Override
    public void onResume()
    {
        super.onResume();
        getDataFromDb(pickType, search);
    }



}
