package com.tact.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tact.kumbhca.R;
import com.tact.model.SignInOneModel;
import com.tact.utils.SharedPreference;
import com.tact.utils.Support;

import org.json.JSONArray;
import org.json.JSONObject;

public class StepOne extends AppCompatActivity
{
    private TextView tvPAreaName;
    private RadioGroup radioSexGroup;
    private Integer userId;
    private TextView tvUserName;
    private String parliament_area_name;
    private SharedPreference sp;
    private long lastPress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one);
        sp = new SharedPreference(this);
        
        initToolBar("Step: 1");
        init();
        getDataFromBundle();
    }

    private void initToolBar(String title)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
    }

    private void init()
    {
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvPAreaName = (TextView) findViewById(R.id.tvPAreaName);

        radioSexGroup = (RadioGroup) findViewById(R.id.rgSex);


        Button appCompatButtonLogin = (Button) findViewById(R.id.appCompatButtonLogin);
        appCompatButtonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
                String prefix = null;

                String assemblyName = null;
                try 
                {
                    String data = radioSexButton.getText().toString();

                    String[] parts = data.split("-");
                    prefix = parts[1] ;

                    assemblyName = parts[0];

                    Log.d("Deepak1", data);
                    Log.d("Deepak2", parts[0] + "Hi " +parts[1]);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                

                Intent intent = new Intent(StepOne.this, StepTwo.class);
                intent.putExtra("USER_ID",  userId);
                intent.putExtra("AC_NO",  radioSexButton.getId());
                intent.putExtra("Prefix" , prefix);
                intent.putExtra("PAN" , parliament_area_name);

                intent.putExtra("ASSEMBLY_NAME" , assemblyName);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
    }

    private void getDataFromBundle()
    {
        Bundle b = getIntent().getExtras();
        SignInOneModel st = null;

        if (b != null)
        {
            st = (SignInOneModel) b.getSerializable("KEY_USER_DATA");

            tvPAreaName.setText(st.getData().getParliamentData().getParliamentAreaName());
            parliament_area_name = st.getData().getParliamentData().getParliamentAreaName();

            tvUserName.setText(st.getData().getUserData().getUserName());
            userId = st.getData().getUserData().getId();

            for (int i = 0; i < st.getData().getUserACData().size() ; i++)
            {
                RadioButton rbn = new RadioButton(this);
                rbn.setId(st.getData().getUserACData().get(i).getAcNo());
                rbn.setTag(st.getData().getUserACData().get(i).getPrefix());
                rbn.setText(st.getData().getUserACData().get(i).getAcName() +"-"+ st.getData().getUserACData().get(i).getPrefix());
                radioSexGroup.addView(rbn);

                if (i == 0)
                {
                    rbn.setChecked(true);
                }
            }
        }
        else
        {
            try
            {
                String data = sp.getValueString("LOGIN_DATA");

                JSONObject obj = new JSONObject(data);
                JSONObject obj1 = obj.getJSONObject("UserData");
                JSONObject obj2 = obj.getJSONObject("ParliamentData");
                JSONArray jArr = obj.getJSONArray("UserACData");

                obj2.getInt("ParliamentAreaId");
                tvPAreaName.setText( obj2.getString("ParliamentAreaName"));
                parliament_area_name = obj2.getString("ParliamentAreaName");

                tvUserName.setText(obj1.getString("UserName"));
                userId = obj1.getInt("Id");

                for(int i=0; i< jArr.length(); i++)
                {
                    RadioButton rbn = new RadioButton(this);
                    rbn.setId(jArr.getJSONObject(i).getInt("AcNo"));

                    rbn.setText(jArr.getJSONObject(i).getString("AcName") +"-"+ jArr.getJSONObject(i).getString("Prefix"));
                    radioSexGroup.addView(rbn);

                    if (i == 0)
                    {
                        rbn.setChecked(true);
                    }
                }
            }
            catch (Exception e)
            {

                e.printStackTrace();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_logout:

                Support.showMessageOKCancel(this, getString(R.string.dywtl), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        sp.clearAll();
                        startActivity(new Intent(StepOne.this, SplashActivity.class));
                        finish();
                    }
                });

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPress > 5000)
        {
            Toast.makeText(getBaseContext(), R.string.pbate, Toast.LENGTH_LONG).show();
            lastPress = currentTime;
        }
        else
        {
            super.onBackPressed();
        }
    }
}
