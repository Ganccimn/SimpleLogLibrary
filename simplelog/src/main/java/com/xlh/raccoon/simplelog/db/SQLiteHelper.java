package com.xlh.raccoon.simplelog.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xlh.raccoon.simplelog.SLog;

public class SQLiteHelper extends SQLiteOpenHelper {

  public static String DB_NAME = "s_log";
  public static String TABLE_NAME = "log_data";
  public static int DB_VERSION = 1;

  public SQLiteHelper() {
    super(SLog.configuration.getContext(), DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQLiteUtils.buildCreateSql());
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
