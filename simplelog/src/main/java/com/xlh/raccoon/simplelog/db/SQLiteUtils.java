package com.xlh.raccoon.simplelog.db;

import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;

/**
 * 数据库工具类
 */
public class SQLiteUtils {

  public static SQLiteDatabase sqLiteDatabase;
  public static String insertSql;

  public static SQLiteDatabase getInstance() {
    if (sqLiteDatabase == null) {
      sqLiteDatabase = new SQLiteHelper().getWritableDatabase();
    }
    return sqLiteDatabase;
  }

  public static String buildCreateSql() {
    StringBuffer sql = new StringBuffer("create table ");
    sql.append(SQLiteHelper.TABLE_NAME);
    sql.append(" ( ");
    try {
      Class cls = Class.forName(SQLiteLogData.class.getName());
      Field[] fields = cls.getFields();
      for (Field field : fields) {
        sql.append(field.getName().toLowerCase());
        sql.append(" ");
        sql.append(convertSqlType(field));
        sql.append(" , ");
      }
      fields = cls.getDeclaredFields();
      for (Field field : fields) {
        sql.append(field.getName().toLowerCase());
        sql.append(" ");
        sql.append(convertSqlType(field));
        sql.append(" , ");
      }
      sql.replace(sql.lastIndexOf(", "), sql.length(), ")");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return sql.toString();
  }

  public static String convertSqlType(Field field) {
    String sqlType = "text";
    if (field.getGenericType().toString().equals("long")) {
      sqlType = "integer";
    }
    return sqlType;
  }
}
