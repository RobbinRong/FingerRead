package com.robbin.fingerread.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.MovieCommonsZY;

/**
 * Created by Administrator on 2016/10/20.
 */
public class MovieCommonZYAdapter extends BaseAdapter {
    private MovieCommonsZY commonsZY;
    private  Context context;

    public MovieCommonZYAdapter(MovieCommonsZY commonsZY, Context context) {
        this.commonsZY = commonsZY;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(null!=commonsZY&&!commonsZY.data.isEmpty()){
            Log.e("getcount", "getCount: " +commonsZY.data.size());
            return  commonsZY.data.size();
        }
        return 0;
    }

    @Override
    public MovieCommonsZY.Data getItem(int position) {
        return commonsZY.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHoler viewHolder;
        MovieCommonsZY.Data data = getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_common_zy, null);
            viewHolder = new ViewHoler();
            viewHolder.imUserLogo = (RoundedImageView) view.findViewById(R.id.iv_zy);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tvAuthinfo = (TextView) view.findViewById(R.id.tv_authinfo);
            viewHolder.tvScore = (TextView) view.findViewById(R.id.tv_score);
            viewHolder.tvContent = (TextView) view.findViewById(R.id.tv_content);
            viewHolder.tvDate = (TextView) view.findViewById(R.id.tv_date);
            viewHolder.tvApprove = (TextView) view.findViewById(R.id.tv_common_approve);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHoler) view.getTag();
        }
        Glide.with(context).load(data.avatarurl).placeholder(R.drawable.ic_placeholder).into(viewHolder.imUserLogo);
        viewHolder.tvName.setText(data.nickName);
        viewHolder.tvAuthinfo.setText(data.authInfo);
        viewHolder.tvScore.setText(data.score*2+"");
        viewHolder.tvContent.setText(data.content);
        viewHolder.tvDate.setText(data.startTime);
        //viewHolder.tvApprove.setText(data.approve);
        return view;
    }
    class ViewHoler{
        com.makeramen.roundedimageview.RoundedImageView imUserLogo;
        TextView tvName;
        TextView tvAuthinfo;
        TextView tvScore;
        TextView tvContent;
        TextView tvDate;
        TextView tvApprove;

    }
}
