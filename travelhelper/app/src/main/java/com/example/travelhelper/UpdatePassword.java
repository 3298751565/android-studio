package com.example.travelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Map;

public class UpdatePassword extends AppCompatActivity {
    private EditText up_pnum, up_m, up_am;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        init();
    }

    private void init() {
        up_pnum = findViewById(R.id.up_pnum);
        up_m = findViewById(R.id.up_m);
        up_am = findViewById(R.id.up_am);
        Button bt_upcommit = findViewById(R.id.bt_upcommit);
        back = findViewById(R.id.password_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result();
            }
        });
        bt_upcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = up_pnum.getText().toString().trim();
                String password = up_m.getText().toString();
                String apassword = up_am.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    Toast.makeText(UpdatePassword.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(UpdatePassword.this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(apassword)) {
                    Toast.makeText(UpdatePassword.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(apassword)) {
                    Toast.makeText(UpdatePassword.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                int f = QQperate.update(UpdatePassword.this , num, password);
                if(f == 1) {
                    Map<String,String> map = QQperate.queryUp(UpdatePassword.this , num);
                    SaveQQ.SaveUserInfo(UpdatePassword.this , map.get("account"), map.get("password"));
                    Toast.makeText(UpdatePassword.this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdatePassword.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void result() {
        Intent intent = new Intent(UpdatePassword.this , login.class);
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