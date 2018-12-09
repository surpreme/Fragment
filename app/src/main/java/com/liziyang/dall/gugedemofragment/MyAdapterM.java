package com.liziyang.dall.gugedemofragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapterM extends FragmentPagerAdapter {
    private int num;
    Wang1 wang1;
    Wang2 wang2;
    Wang3 wang3;

    public MyAdapterM(FragmentManager fm, int num) {
        super ( fm );
        this.num=num;
    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                if (wang1==null){
                    return new Wang1 ();
                }


            case 1:
                if (wang2==null){
                    return new Wang2 ();
                }

            case 2:
                if (wang3==null){
                    return new Wang3 ();
                }

                default:
                    return null;

        }

    }

    @Override
    public int getCount() {
        return num;
    }
}
