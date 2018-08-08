package com.bwie.zjj.jd;

import android.text.TextUtils;

import com.bwie.zjj.jd.bean.UserBean;
import com.bwie.zjj.jd.model.LoginModel;
import com.bwie.zjj.jd.view.LoginView;

public class LoginPresenter {

    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModel();
    }

    public void register(String name, String pwd){

        if (TextUtils.isEmpty(name)){
            loginView.nameVerify();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            loginView.pwdVerify();
            return;
        }
        loginModel.register(name, pwd, new LoginModel.LogCallBack() {
            @Override
            public void success(UserBean userBean) {
                loginView.success(userBean);
            }

            @Override
            public void error(String s) {
                loginView.error(s);
            }
        });


    }
}
