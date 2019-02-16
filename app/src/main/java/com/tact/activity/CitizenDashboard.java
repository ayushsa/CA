package com.tact.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.tact.adapter.ViewPagerAdapter;
import com.tact.database.MyDbHelper;
import com.tact.kumbhca.R;
import com.tact.model.VoterData;
import com.tact.utils.Constant;
import com.tact.utils.SharedPreference;
import com.tact.utils.Support;
import java.util.ArrayList;
import me.drakeet.materialdialog.MaterialDialog;

public class CitizenDashboard extends AppCompatActivity
{

    private Context context;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    private int ps_no;
    private int ac_no;
    private String prefix;
    private SharedPreference sp;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout dLayout;
    MyDbHelper myDbHelper;
    ArrayList<VoterData> syncedVoterData;
    ArrayList<VoterData> unSyncedVoterData;

    private int syncedVoterCount;
    private int unsyncedVoterCount;
    private MenuItem item;

    private TextView tvUnSynced;
    private TextView tvSynced;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dashboard);
        sp = new SharedPreference(this);

        initToolBar("Citizen Records");
        initWidgets();

        getDataFromBundle();

        initWidgetListener();

        myDbHelper = new MyDbHelper(context);

        sp.setValueBool(Constant.IS_ON_DASHBOARD, true);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        unSyncedVoterData = myDbHelper.getUnsyncedData();
        syncedVoterData = myDbHelper.getSyncedData();

        unsyncedVoterCount = unSyncedVoterData.size();
        syncedVoterCount = syncedVoterData.size();

        tvUnSynced.setText(unsyncedVoterCount+"");
        tvSynced.setText(syncedVoterCount+"");

        if (item != null)
        {
            item.setTitle(Html.fromHtml("Unsurveyed :" + unsyncedVoterCount+"<br>" + "Surveyed :" + syncedVoterCount));
        }
    }

    private void initToolBar(String title)
    {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, dLayout, R.string.drawer_open_content_desc, R.string.drawer_close_content_desc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(title);

        dLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        mDrawerToggle.syncState();

        getMenuInflater().inflate(R.menu.home_menu, menu);

        item = menu.findItem(R.id.mcount);
        item.setTitle(Html.fromHtml("Unsurveyed :" + unsyncedVoterCount+"<br>" + "Surveyed :" + syncedVoterCount));

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        switch (item.getItemId())
        {
            case R.id.action_logout:

                Support.showMessageOKCancel(this, "Do you want to logout?", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        sp.clearAll();
                        startActivity(new Intent(CitizenDashboard.this, SplashActivity.class));
                        finish();
                    }
                });

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getDataFromBundle()
    {
        Bundle b = getIntent().getExtras();

        if (b != null)
        {
            ps_no = b.getInt("PsNo");
            ac_no = b.getInt("AcNo");
            prefix = b.getString("Prefix");
        }
    }

    private void initWidgetListener()
    {

    }

    private void initWidgets()
    {
        context = CitizenDashboard.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tvUnSynced = (TextView) findViewById(R.id.tvUnSynced);
        tvSynced = (TextView) findViewById(R.id.tvSynced);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        adapter = new ViewPagerAdapter(getSupportFragmentManager() , ps_no, ac_no, prefix);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);

        tabLayout.setupWithViewPager(viewPager);


     /*   for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.layout_custom_tab, null);

            tabLayout.getTabAt(i).setCustomView(tv);
        }*/

        TextView tvParliament = (TextView) findViewById(R.id.tvParliament);
        TextView tvAssemblyName = (TextView) findViewById(R.id.tvAssemblyName);
        TextView tvPollingStationName = (TextView) findViewById(R.id.tvPollingStationName);

        tvParliament.setText(sp.getValueString("PS_NAME"));
        tvAssemblyName.setText(sp.getValueString("ASSEMBLY_NAME"));
        tvPollingStationName.setText(sp.getValueString("PAN"));
    }

//    @Override
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//        alertDialog();
//    }

    public void alertDialog()
    {

    }


    @Override
    public void onBackPressed()
    {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0)
        {
            //additional code

            final MaterialDialog md = new MaterialDialog(CitizenDashboard.this);
            md.setTitle("Alert Citizen App");
            md.setMessage("Are you sure you want to go back from this screen? If you go back from this screen your surveyed data for " + sp.getValueString("PS_NAME") + " will be reset.");
            md.setPositiveButton("Yes", new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    md.dismiss();
                    sp.setValueBool(Constant.IS_ON_DASHBOARD, false);

                    Intent intent = new Intent(CitizenDashboard.this, StepOne.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            });

            md.setNegativeButton("No", new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    md.dismiss();
                }
            });
            md.show();
           // super.onBackPressed();
        }
        else
        {
            getFragmentManager().popBackStack();
        }
    }

}
