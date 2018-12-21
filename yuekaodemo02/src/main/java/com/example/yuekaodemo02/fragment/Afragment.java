package com.example.yuekaodemo02.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuekaodemo02.R;
import com.example.yuekaodemo02.adapter.LeftAdapter;
import com.example.yuekaodemo02.adapter.RightAdapter;
import com.example.yuekaodemo02.bean.Data;
import com.example.yuekaodemo02.ivew.IVew;
import com.example.yuekaodemo02.presenter.IPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class Afragment extends Fragment implements IVew {
    private RecyclerView left;
    private RecyclerView right;
    private TextView price;
    private TextView jisuan;
    private List<Data.DataBean> list=new ArrayList<>();
    private List<Data.DataBean.SpusBean> mlist=new ArrayList<>();
    private LeftAdapter adapter1;
    private IPresenterImpl iPresenter;
    private String mUrl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private RightAdapter adapter2;
    private List<Data.DataBean.SpusBean> spus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.afragment, null);
        initView(view);
        adapter1 = new LeftAdapter(list,getContext());
        left.setAdapter(adapter1);
        adapter2 = new RightAdapter(mlist,getActivity());
        right.setAdapter(adapter2);

        iPresenter.getData(mUrl);
        adapter1.setListData(new LeftAdapter.ListPosition() {
            @Override
            public void setListPosition(int position) {
            mlist.clear();
                spus = list.get(position).getSpus();
                for(int i=0;i<spus.size();i++){
                    spus.get(i).setPraise_num(0);
                }
                mlist.addAll(spus);
                adapter2.notifyDataSetChanged();
            }
});
        adapter2.setListData(new RightAdapter.RefreshCount() {
            @Override
            public void refresh() {
                float f=0;
                int t=0;
                for (int i = 0; i < spus.size(); i++) {
                    f+=spus.get(i).getPraise_num()*Float.valueOf(spus.get(i).getSkus().get(0).getPrice());
                }
                for (int i = 0; i < spus.size(); i++) {
                    t+=spus.get(i).getPraise_num();
                }
                jisuan.setText("数量:"+t);
                price.setText("价钱:"+f);
            }
        });
        return view;

    }

    private void initView(View view) {
        left = (RecyclerView) view.findViewById(R.id.left);
        right = (RecyclerView) view.findViewById(R.id.right);
        price = (TextView) view.findViewById(R.id.price);
        jisuan = (TextView) view.findViewById(R.id.jisuan);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        left.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getActivity());
        right.setLayoutManager(linearLayoutManager1);
        iPresenter = new IPresenterImpl(this);
    }

    @Override
    public void showData(Object data) {
      Data dd = (Data) data;
      list.addAll(dd.getData());
      adapter1.notifyDataSetChanged();

    }
}
