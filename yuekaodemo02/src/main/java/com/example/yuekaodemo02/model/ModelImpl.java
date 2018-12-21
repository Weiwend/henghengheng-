package com.example.yuekaodemo02.model;

import android.os.Handler;
import android.os.Message;

import com.example.yuekaodemo02.bean.Data;
import com.example.yuekaodemo02.callback.MyCallBack;
import com.example.yuekaodemo02.okhttp.OKHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ModelImpl implements Model {
    private MyCallBack callBack;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String d = (String) msg.obj;
            Gson g=new Gson();
            Data data = g.fromJson(d, Data.class);
            callBack.onSuccess(data);
        }
    };
    @Override

    public void getData(final String uri, MyCallBack callBack) {
         this.callBack=callBack;
         new Thread(new Runnable() {
             @Override
             public void run() {
                 OKHttpUtils.getInstance().post(uri, new Callback() {
                     @Override
                     public void onFailure(Call call, IOException e) {

                     }

                     @Override
                     public void onResponse(Call call, Response response) throws IOException {
                         handler.sendMessage(handler.obtainMessage(0,response.body().string()));
                     }
                 });

             }
         }).start();
    }
}
