package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.PagerAdapter;
import com.robbin.fingerread.constant.ReadingApi;

/**
 * Created by OneAPM on 2016/8/23.
 */
public class BaseReadFragment extends TopNavigationFragment {
    private PagerAdapter pagerAdapter;

    @Override
    public PagerAdapter initPageAdapter() {
        pagerAdapter = new PagerAdapter(getChildFragmentManager(), ReadingApi.Tag_Titles) {
            @Override
            public Fragment getItem(int position) {
                ReadFragment fragment = new ReadFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(getString(R.string.id_pos),position);
                bundle.putSerializable(getString(R.string.id_category),ReadingApi.Tag_Titles[position]);
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
