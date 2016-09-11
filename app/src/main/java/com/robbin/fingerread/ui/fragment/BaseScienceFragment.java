package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.PagerAdapter;
import com.robbin.fingerread.constant.ScienceApi;

/**
 * Created by OneAPM on 2016/8/23.
 */
public class BaseScienceFragment extends TopNavigationFragment {
    private PagerAdapter pagerAdapter;

    @Override
    public PagerAdapter initPageAdapter() {
        pagerAdapter = new PagerAdapter(getChildFragmentManager(), ScienceApi.channel_title) {
            @Override
            public Fragment getItem(int position) {
                ScienceFragment fragment = new ScienceFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(getString(R.string.id_pos),position);
                bundle.putSerializable(getString(R.string.id_category),ScienceApi.channel_tag[position]);
                fragment.setArguments(bundle);
                return fragment;
            }
        };
        return pagerAdapter;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(getChildFragmentManager().getFragments()!=null){
            getChildFragmentManager().getFragments().clear();

        }
    }
}
