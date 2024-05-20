package com.example.travelhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class QQHelper extends SQLiteOpenHelper {
    public QQHelper(@Nullable Context context) {
        super(context, "qq.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table user(_id Integer primary key AUTOINCREMENT,  account varchar(20) , num varchar(20), password varchar(20))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
