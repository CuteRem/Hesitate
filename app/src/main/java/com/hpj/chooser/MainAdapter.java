package com.hpj.chooser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hpj16042 on 2017/12/20.
 */

public class MainAdapter extends FragmentStatePagerAdapter {

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NumberFragment();
            case 1:
                return new TrueOrFalseFragment();
            case 2:
                return new CustomFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
