package com.xlh.raccoon.simplelog.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SQLite 数据模型 增删改查。
 *
 * @see SQLiteLogData
 * @see SLogPosition
 */
public abstract class SQLiteCore {

  /**
   * 插入一条日志数据。
   */
  public void save() {
    Class cls = this.getClass();
    List<Field> fieldList = new ArrayList<>();
    fieldList.addAll(Arrays.asList(cls.getFields()));
    fieldList.addAll(Arrays.asList(cls.getDeclaredFields()));
    //insert sql 语句的 field 字符子串
    StringBuffer fieldSql = new StringBuffer("(");
    //insert sql 语句的 values 字符子串
    StringBuffer valSql = new StringBuffer("values(");
    for (Field field : fieldList) {
      field.setAccessible(true);
      fieldSql.append(field.getName());
      fieldSql.append(" , ");
      valSql.append(convertSqlStr(field));
      valSql.append(" , ");
    }
    fieldSql.replace(fieldSql.lastIndexOf(", "), fieldSql.length(), ")");
    valSql.replace(valSql.lastIndexOf(", "), valSql.length(), ")");
    String insertSql = String.format("insert into %s %s %s", SQLiteHelper.TABLE_NAME,
        fieldSql.toString(), valSql.toString());
    SQLiteUtils.getInstance().execSQL(insertSql);
  }

  /**
   * 清空所有日志数据。
   */
  public static void clear() {
    SQLiteUtils.getInstance().execSQL(String.format("delete from %s", SQLiteHelper.TABLE_NAME));
  }

  /**
   * 将对象的属性值进行处理，属性值转 sql 字符串。
   *
   * @param field
   * @return
   */
  private String convertSqlStr(Field field) {
    //对字段的值进行空判断，为空则不处理。
    Object valObj;
    try {
      valObj = field.get(this);
      if (valObj == null) {
        return "null";
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return "null";
    }
    //对值进行类型判断。
    String val = String.format("'%s'", valObj.toString());
    String fieldTypeStr = field.getGenericType().toString();
    if (fieldTypeStr.equals(int.class.getName()) || fieldTypeStr.equals(long.class.getName()) ||
        fieldTypeStr.equals(float.class.getName()) || fieldTypeStr.equals(double.class.getName())) {
      val = valObj.toString();
    }
    return val;
  }
}
