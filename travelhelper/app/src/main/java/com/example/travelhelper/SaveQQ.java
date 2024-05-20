package com.example.travelhelper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;
public class SaveQQ {
    public static void SaveUserInfo(Context context, String account, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("QQuser",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("account",account);
        editor.putString("password",password);
        editor.apply();
    }
    public static Map<String,String> getUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("QQuser",Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("account",null);
        String password = sharedPreferences.getString("password",null);
        Map<String,String> map = new HashMap<>();
        map.put("account",account);
        map.put("password",password);
        return map;
    }
}
