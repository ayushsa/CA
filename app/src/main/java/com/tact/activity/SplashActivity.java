package com.tact.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tact.kumbhca.R;
import com.tact.utils.Constant;
import com.tact.utils.SharedPreference;

public class SplashActivity extends AppCompatActivity
{
    private SharedPreference sp;
    
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = new SharedPreference(this);
        
        startAnimations();
        startTimer();
    }

    private void startAnimations()
    {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);
    }

    //timer for showing splash screen
    private void startTimer()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity();
            }
        }, 3000);
    }


    //starts the Home Activity(DashboardActivity activity)
    void startActivity()
    {
        if (!sp.getValueBoolean(Constant.KEY_ISLOGIN))
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            this.finish();
        }
        else if (sp.getValueBoolean(Constant.KEY_ISLOGIN))
        {
            if (sp.getValueBoolean(Constant.IS_ON_DASHBOARD))
            {
                Intent intent = new Intent(this, CitizenDashboard.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                this.finish();
            }
            else
            {
                Intent intent = new Intent(this, StepOne.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                this.finish();
            }
        }
    }
}