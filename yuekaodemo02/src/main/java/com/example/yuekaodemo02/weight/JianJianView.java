package com.example.yuekaodemo02.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuekaodemo02.R;


public class JianJianView extends LinearLayout implements View.OnClickListener {

    private TextView add;
    private TextView shownumber;
    private TextView reducece;
    private int mCount;

    public JianJianView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.addreduceview_item ,this);
        initView();
    }

    private void initView() {
        reducece = findViewById(R.id.reducece);
        add = findViewById(R.id.add);
        shownumber = findViewById(R.id.show_nnumber);
        add.setOnClickListener(this);
        reducece.setOnClickListener(this);
    }

    public void setNum(int count){
        this.mCount = count;
        if (mCount==0){
            reducece.setVisibility(GONE);
        }else{
            reducece.setVisibility(VISIBLE);
        }
        shownumber.setText(count+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reducece:
                if(mCount>0){
                    mCount--;
                   shownumber.setText(mCount+"");
                    if(mCountChange!=null){
                        mCountChange.setCount(mCount);
                    }
                }
                break;
            case R.id.add:
                mCount++;
                shownumber.setText(mCount+"");
                if(mCountChange!=null){
                    mCountChange.setCount(mCount);
                }
                break;
        }
    }
    public interface CountChange{
        void setCount(int count);
    }
    private CountChange mCountChange;
    public void setCountChange(CountChange countChange){
        this.mCountChange = countChange;
    }
}
