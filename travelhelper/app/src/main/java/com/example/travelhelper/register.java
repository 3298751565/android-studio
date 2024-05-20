package com.example.travelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class register extends AppCompatActivity {
    private EditText register_z, register_m, register_am, register_pnum;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        register_z = findViewById(R.id.register_z);
        register_pnum = findViewById(R.id.register_pnum);
        register_m = findViewById(R.id.register_m);
        register_am = findViewById(R.id.register_am);
        Button bt_rgcommit = findViewById(R.id.bt_rgcommit);
        back = findViewById(R.id.register_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result();
            }
        });
        bt_rgcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = register_z.getText().toString();
                String num = register_pnum.getText().toString().trim();
                String password = register_m.getText().toString();
                String apassword = register_am.getText().toString();
                if(TextUtils.isEmpty(account)) {
                    Toast.makeText(register.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(num)) {
                    Toast.makeText(register.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(register.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(apassword)) {
                    Toast.makeText(register.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(apassword)) {
                    Toast.makeText(register.this, "密码和确认密码不相同", Toast.LENGTH_SHORT).show();
                    return;
                }
                int f = QQperate.insert(register.this, account, num, password);
                if (f == 1) {
                    SaveQQ.SaveUserInfo(register.this , account, password);
                    Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(register.this, "注册失败，账号或者号码已存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void result() {
        Intent intent = new Intent(register.this , login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            result();
        }
        return false;
    }
}