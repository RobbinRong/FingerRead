package com.robbin.fingerread.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.bean.ScienceBean;
import com.robbin.fingerread.constant.Settings;
import com.robbin.fingerread.ui.activity.ScienceDetailActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OneAPM on 2016/9/6.
 */
public class ScienceAdapter extends RecyclerView.Adapter<ScienceAdapter.ScienceViewHolder> {

    //private ScienceBean scienceBean;
    private Context context;
    private long lastPos = -1;
    private boolean isAnim = true;
    private List<ArticleBean> list;

    public ScienceAdapter(List<ArticleBean> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public ScienceAdapter.ScienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.science_item, parent, false);
        return new ScienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScienceAdapter.ScienceViewHolder holder, int position) {
        final ArticleBean articleBean =list.get(position);
        if(Settings.isNightMode){
            holder.mCvItem.setCardBackgroundColor(Color.rgb(100,100,100));
        }
        holder.mTvTitle.setText(articleBean.getTitle());
        Glide.with(context).load(articleBean.getImage_info().getUrl()).placeholder(R.drawable.ic_placeholder).into(holder.mIvNews);
        holder.mComment.setText(articleBean.getReplies_count()+"");
        if (isAnim) {
            startAnimator(holder.mCvItem, position);
        }
        holder.mCvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScienceDetailActivity.start(context,articleBean);
            }
        });

    }
    private void startAnimator(CardView mCvItem, int position) {
        if (position > lastPos) {
            mCvItem.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.item_bottom_in));
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return list.isEmpty()?0:list.size();
    }

    public void change(ScienceBean scienceBean) {
        this.list= Arrays.asList(scienceBean.getResult());
        this.notifyDataSetChanged();
    }

    class ScienceViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.cv_item)
        CardView mCvItem;

        @Bind(R.id.iv_news)
        ImageView mIvNews;

        @Bind(R.id.tv_title)
        TextView mTvTitle;
        @Bind(R.id.comment)
        TextView mComment;

        public ScienceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
