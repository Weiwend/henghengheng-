package com.example.yuekaodemo02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuekaodemo02.R;
import com.example.yuekaodemo02.bean.Data;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private List<Data.DataBean> list;
    private Context context;

    public LeftAdapter(List<Data.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.left_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.lefttext.setText(list.get(i).getName());
        viewHolder.lefttext.setOnClickListener(new View.OnClickListener() {
            @Override
            //传下标
            public void onClick(View v) {
                if (mListPosition!=null){
                    mListPosition.setListPosition(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lefttext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lefttext=itemView.findViewById(R.id.left_text);

        }
    }
    public interface ListPosition{
        void setListPosition(int position);
    }
    private ListPosition mListPosition;
    public void setListData(ListPosition listPosition){
        this.mListPosition = listPosition;
    }
}
