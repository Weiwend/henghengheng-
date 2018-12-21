package com.example.yuekaodemo02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuekaodemo02.R;
import com.example.yuekaodemo02.bean.Data;
import com.example.yuekaodemo02.weight.JianJianView;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private List<Data.DataBean.SpusBean> mlist;
    private Context context;

    public RightAdapter(List<Data.DataBean.SpusBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.right_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String s = mlist.get(i).getPic_url();
        Glide.with(context).load(s).into(viewHolder.icon);
        viewHolder.title.setText(mlist.get(i).getName());
        viewHolder.price.setText(mlist.get(i).getSkus().get(0).getPrice());
        viewHolder.addreduceview.setNum(mlist.get(i).getPraise_num());
        viewHolder.addreduceview.setCountChange(new JianJianView.CountChange() {
            @Override
            public void setCount(int count) {
                    mlist.get(i).setPraise_num(count);
                    notifyDataSetChanged();
                if (mRefreshCount!=null){
                    mRefreshCount.refresh();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView price;
        private TextView title;
        private JianJianView addreduceview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           icon=  itemView.findViewById(R.id.icon);
           title= itemView.findViewById(R.id.title);
           price= itemView.findViewById(R.id.price);
           addreduceview= itemView.findViewById(R.id.addreduceview);
        }
    }
    public interface RefreshCount{
        void refresh();
    }
    private RefreshCount mRefreshCount;
    public void setListData(RefreshCount refreshCount){
        this.mRefreshCount = refreshCount;
    }
}
