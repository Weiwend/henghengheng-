package com.example.yuekaodemo02.callback;

import com.example.yuekaodemo02.bean.Data;

public interface MyCallBack {
    void onSuccess(Data data);
    void onFail(Exception e);
}
