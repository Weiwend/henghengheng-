package com.example.yuekaodemo02.model;

import com.example.yuekaodemo02.callback.MyCallBack;

public interface Model {
    void getData(String uri,MyCallBack callBack);
}
