package com.example.travelhelper;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
public class login  extends AppCompatActivity implements View.OnClickListener{
    QQHelper qqHelper;
    private EditText login_z, login_m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        qqHelper = new QQHelper(login.this);
//        login.this.deleteDatabase("qq.db");
        init();
        Outdate();
    }
    private void init() {
        login_z = findViewById(R.id.login_z);
        login_m = findViewById(R.id.login_m);
        Button bt_register = findViewById(R.id.bt_register);
        Button bt_xpassword = findViewById(R.id.bt_xpassword);
        Button bt_login = findViewById(R.id.bt_login);
        bt_register.setOnClickListener(this);
        bt_xpassword.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    private void Outdate() {
        Map<String,String> map = SaveQQ.getUserInfo(login.this);
        String account = map.get("account");
        String password = map.get("password");
        login_z.setText(account);
        login_m.setText(password);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register: {
                Intent intent = new Intent(login.this , register.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.bt_xpassword: {
                Intent intent = new Intent(login.this , UpdatePassword.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.bt_login: {
                String account = login_z.getText().toString().trim();
                String password = login_m.getText().toString();
                Log.i("debug","登录按钮");
                if(TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                int f = QQperate.query(login.this, account, password);
                if(f == 1) {
                    Intent intent = new Intent(login.this , zhujianActivity.class);
                    SaveQQ.SaveUserInfo(login.this, account, password);
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
