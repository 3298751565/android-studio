package com.example.travelhelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
public class QQperate {
    //添加用户
    public static int insert(Context context, String account, String num, String password) {
        QQHelper qqHelper = new QQHelper(context);
        SQLiteDatabase db = qqHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account",account);
        values.put("num",num);
        values.put("password",password);
        String sql = "select * from user where account=? or num=?";
        Cursor cursor = db.rawQuery(sql, new String[]{account, num});
        int f = 0;
        if(cursor.getCount() == 0) {
            db.insert("user",null,values);
            f = 1;
        }
        db.close();
        cursor.close();
        return f;
    }
    //修改密码
    public static int update(Context context, String num, String password) {
        QQHelper qqHelper = new QQHelper(context);
        SQLiteDatabase db = qqHelper.getWritableDatabase();
        String sql = "update user set password=? where num=?";
        Cursor cursor = db.query("user",null,"num=?",new String[]{num},null,null,null);
        int f = 0;
        if(cursor.getCount() != 0) {
            db.execSQL(sql,new String[]{password, num});
            f = 1;
        }
        db.close();
        cursor.close();
        return f;
    }
    //查询用户
    public static int query(Context context, String account, String password) {
        QQHelper qqHelper = new QQHelper(context);
        SQLiteDatabase db = qqHelper.getWritableDatabase();
        String sql = "select * from user where account=? and password=?";
        Cursor cursor = db.rawQuery(sql,new String[]{account,password});
        int f = 0;
        if(cursor.getCount() != 0) f = 1;
        db.close();
        cursor.close();
        return f;
    }
    //通过注册号码查询账号与密码
    public static Map<String,String> queryUp(Context context, String num) {
        QQHelper qqHelper = new QQHelper(context);
        SQLiteDatabase db = qqHelper.getWritableDatabase();
        String sql = "select * from user where num=?";
        Cursor cursor = db.rawQuery(sql,new String[]{num});
        Map<String,String> map = new HashMap<>();
        if(cursor.moveToNext()) {
            @SuppressLint("Range") String account = cursor.getString(cursor.getColumnIndex("account"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            map.put("account",account);
            map.put("password",password);
        }
        cursor.close();
        db.close();
        return map;
    }
}
