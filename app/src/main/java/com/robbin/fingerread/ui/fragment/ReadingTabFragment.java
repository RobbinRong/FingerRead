package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.robbin.fingerread.R;
import com.robbin.fingerread.constant.ReadingApi;

import butterknife.Bind;

/**
 * Created by OneAPM on 2016/9/6.
 */
public class ReadingTabFragment extends BaseFragment {
    @Bind(R.id.text)
    TextView textView;
    private int pos;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_reading_tab;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        pos = getArguments().getInt(getString(R.string.id_pos));
        textView.setText(ReadingApi.getBookInfo(pos, ReadDetailFragment.bookeBean));
    }
}
