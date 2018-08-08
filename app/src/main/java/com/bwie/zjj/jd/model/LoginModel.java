package com.bwie.zjj.jd.model;

import android.os.Handler;
import android.os.Message;

import com.bwie.zjj.jd.bean.UserBean;
import com.bwie.zjj.jd.common.Api;
import com.google.gson.Gson;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel {

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public void register(String name, String pwd, final LogCallBack logCallBack){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        FormBody  formBody=new FormBody.Builder()
                .add("name",name)
                .add("pwd",pwd)
                .build();
        Request request= new Request.Builder()
                .url(Api.REG_URL)
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(logCallBack!=null){
                    logCallBack.error("请求失败");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code() ==200){
                    String result = response.body().string();
                    pareseJsonResult(result,logCallBack);
                }
            }


        });
    }

    private void pareseJsonResult(String result, final LogCallBack logCallBack) {
        if(result!=null){
            Gson gson = new Gson();
            final UserBean userBean = gson.fromJson(result, UserBean.class);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(logCallBack!=null){
                        logCallBack.success(userBean);
                    }
                }
            });
        }
    }

    public interface LogCallBack{
        void success(UserBean userBean);
        void error(String s);
    }
}
