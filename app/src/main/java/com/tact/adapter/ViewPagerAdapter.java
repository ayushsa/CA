package com.tact.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tact.fragments.SurveyedFragment;
import com.tact.fragments.UnserveyedFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    String[] tabs = new String[]{"Unsurveyed", "Surveyed"};

    int ps_no;
    int ac_no;
    String prefix;

    public ViewPagerAdapter(FragmentManager fm, int ps_no, int ac_no, String prefix)
    {
        super(fm);

        this.ps_no = ps_no;
        this.ac_no = ac_no;
        this.prefix = prefix;
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        switch (position) {

            case 0:

                Bundle b = new Bundle();
                b.putInt("PsNo" , ps_no);
                b.putInt("AcNo" , ac_no);
                b.putString("Prefix" , prefix);

                fragment = new UnserveyedFragment();
                fragment.setArguments(b);

                break;

            case 1:
                fragment = new SurveyedFragment();
                break;

        }

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        title = tabs[position];
        return title;
    }


}
