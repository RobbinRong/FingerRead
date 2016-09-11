package com.robbin.fingerread.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.dao.DailyDao;
import com.robbin.fingerread.ui.activity.DailyDetailActivity;
import com.robbin.fingerread.utils.DateUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OneAPM on 2016/8/24.
 */
public class  DailyAdapter   extends RecyclerView.Adapter<DailyAdapter.DailViewHolder> {
    private Context mContext;

    private List<News> list;
    private static final int ITEM_NEWS = 0;
    private static final int ITEM_NEWS_DATE = 1;
    private long lastPos = -1;
    private boolean isAnim = true;
    private DailyDao dailyDao;

     public DailyAdapter(Context context, List<News> newsList){
        this.mContext = context;
        this.list = newsList;
         this.dailyDao=new DailyDao(context);
    }
    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_NEWS;
        }
        String currentDate=list.get(position).getDate();
        int index=position-1;
        String indexDate=list.get(index).getDate();
        return currentDate.equals(indexDate)?ITEM_NEWS:ITEM_NEWS_DATE;
//        return ITEM_NEWS;
    }

    @Override
    public DailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_NEWS){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item,parent,false);
            return new DailViewHolder(view);
        }
        else {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item_date,parent,false);
            return new DailyDateViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(DailViewHolder holder, int position) {
        final News news=list.get(position);
        if(news==null){
            return;
        }
        if(holder instanceof DailyDateViewHolder){
            DailyDateViewHolder dailyDateViewHolder= (DailyDateViewHolder) holder;
            String date=null;
            date= DateUtil.formatDate(news.getDate());
            dailyDateViewHolder.mTvNewsDate.setText(date);
            bindViewHolder(dailyDateViewHolder, position, news);
        } else {
              bindViewHolder(holder, position, news);
        }

    }
    private void bindViewHolder(final DailViewHolder holder, int position, final News news) {
        holder.mTvTitle.setText(news.getTitle());
        List<String> images = news.getImages();
        if (images != null && images.size() > 0) {
            Glide.with(mContext).load(images.get(0)).placeholder(R.drawable.ic_placeholder).into(holder.mIvNews);
        }
        if (!news.isRead()) {
            holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.textColorFirst_Day));
        } else {
            holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.textColorThird_Day));
        }
        holder.mCvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!news.isRead()){
                    news.setRead(true);
                    holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_read));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            dailyDao.insertReadNew(news.getId() + "");
                        }
                    }).start();
                }
                DailyDetailActivity.start(mContext,news);
            }
        });
        if (isAnim) {
            startAnimator(holder.mCvItem, position);
        }

    }

    @Override
    public void onViewDetachedFromWindow(DailViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.mCvItem.clearAnimation();
    }

    private void startAnimator(CardView mCvItem, int position) {
        if (position > lastPos) {
            mCvItem.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.item_bottom_in));
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void change(List<News> newsList){
        this.list=newsList;
        this.notifyDataSetChanged();

    }

    public void addData(List<News> stories) {
        if(list==null){
            change(stories);
        }
        else {
            list.addAll(stories);
            this.notifyDataSetChanged();
        }
    }

    class DailViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cv_item)
        CardView mCvItem;

        @Bind(R.id.iv_news)
        ImageView mIvNews;

        @Bind(R.id.tv_title)
        TextView mTvTitle;
        public DailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class DailyDateViewHolder extends DailViewHolder{

        @Bind(R.id.tv_news_date)
        TextView mTvNewsDate;

        public DailyDateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
