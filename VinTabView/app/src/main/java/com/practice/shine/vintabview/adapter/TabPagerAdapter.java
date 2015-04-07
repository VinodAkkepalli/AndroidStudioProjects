package com.practice.shine.vintabview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.practice.shine.vintabview.GamesFragment;
import com.practice.shine.vintabview.MoviesFragment;
import com.practice.shine.vintabview.TopRatedFragment;

/**
 * Created by Vinod Akkepalli on 12/21/14.
 */
public class TabPagerAdapter extends FragmentPagerAdapter{

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new TopRatedFragment();
            case 1:
                return new GamesFragment();
            case 2:
                return new MoviesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
