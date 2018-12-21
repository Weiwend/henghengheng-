package com.example.yuekaodemo02.okhttp;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OKHttpUtils {
  private OkHttpClient okHttpClient;

    public OKHttpUtils(){

        okHttpClient=new OkHttpClient();
      }
    public static OKHttpUtils getInstance() {

      return okHolder.okHttpUtils;
    }
    static class okHolder{
        static OKHttpUtils okHttpUtils=new OKHttpUtils();
    }
    public void post(String uri, Callback callback){
        Request request=new Request.Builder().url(uri).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
