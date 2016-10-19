package com.robbin.fingerread.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.robbin.fingerread.ui.fragment.MovieFragment;

/**
 * Created by Administrator on 2016/10/17.
 */
public class BaseMovieAdapter extends FragmentStatePagerAdapter {

    public BaseMovieAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MovieFragment movieFragment=new MovieFragment(position);
        return movieFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
