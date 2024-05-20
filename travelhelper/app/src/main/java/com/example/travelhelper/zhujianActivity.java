package com.example.travelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.travelhelper.Map.MapApplication;
import com.example.travelhelper.Map.MapMainActivity;

public class zhujianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhujian);
    }

    /**
     * 当“进入地图”按钮被点击时调用
     */
    public void onEnterMapClick(View view) {
        startActivity(new Intent(this, MapMainActivity.class));
    }
}