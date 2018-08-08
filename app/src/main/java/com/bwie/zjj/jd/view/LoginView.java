package com.bwie.zjj.jd.view;

import com.bwie.zjj.jd.bean.UserBean;

public interface LoginView {
    void nameVerify();
    void pwdVerify();
    void success(UserBean bean);
    void error(String s);

}
