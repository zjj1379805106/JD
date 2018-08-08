package com.bwie.zjj.jd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.zjj.jd.bean.UserBean;
import com.bwie.zjj.jd.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private EditText name;
    private EditText pwd;
    private Button but;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        but = findViewById(R.id.but);

        loginPresenter = new LoginPresenter(this);
        but.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but:
                loginPresenter.register(name.getText().toString(),pwd.getText().toString());
                break;
        }

    }

    @Override
    public void nameVerify() {
        Toast.makeText(MainActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdVerify() {
        Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserBean bean) {
        Toast.makeText(MainActivity.this,"登录成功"+bean.pwd,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String s) {
        Toast.makeText(MainActivity.this,"登录失败"+s,Toast.LENGTH_SHORT).show();
    }

}
