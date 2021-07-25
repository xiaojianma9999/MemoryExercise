package com.xiaojianma.memoryexercise.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaojianma.memoryexercise.model.NumberImage;
import com.xiaojianma.memoryexercise.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>  {

    private static final String TAG = "ViewPagerAdapter";

    private List<NumberImage> mList;

    private Context mContext;

    public ViewPagerAdapter(Context context, List<NumberImage> list) {
        mContext = context;
        mList = list;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager, parent, false))
                ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        holder.getItemViewType();
        String text = mList.get(position).getNumberText();
        holder.mTv.setText(text);
        holder.mImageView.setImageDrawable(mList.get(position).getDrawable());
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv;

        private ImageView mImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tvTitle);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
