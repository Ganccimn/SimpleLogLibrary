package com.xlh.raccoon.simplelog.core;

import com.xlh.raccoon.simplelog.constant.ListTypeEnum;
import com.xlh.raccoon.simplelog.constant.MapTypeEnum;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * SLog 方法接口。
 */
public interface SLogMethod {

  void print(int num);

  void print(float num);

  void print(long num);

  void print(double num);

  /**
   * @param msg 打印字符串。
   */
  void print(String msg);

  /**
   * 打印int数组。
   *
   * @param arr
   */
  void print(int[] arr);

  /**
   * 打印二维int数组。
   *
   * @param arr
   */
  void print(int[][] arr);

  /**
   * 打印集合。
   *
   * @param list
   * @param listTypeEnum
   */
  void print(List list, ListTypeEnum listTypeEnum);

  void print(List list);

  /**
   * 打印集合。
   *
   * @param map
   * @param mapTypeEnum
   */
  void print(Map map, MapTypeEnum mapTypeEnum);

  void print(Map map);

  /**
   * 打印异常。
   *
   * @param ex
   */
  void print(Exception ex);

  /**
   * @param sb
   */
  void print(StringBuffer sb);

  /**
   * @param sb
   */
  void print(StringBuilder sb);

  /**
   * 格式化输出。
   *
   * @param format
   * @param objects
   */
  void print(String format, Object... objects);

  /**
   * 打印字段类型。
   *
   * @param field
   */
  void print(Field field);

  /**
   * 打印字段信息。
   *
   * @param field
   */
  void print(Field field, Object obj);

  /**
   * 例如：[1:abcd] [2:123] [3:true] [4:^##]。
   *
   * @param objArr 打印多个参数。
   */
  void printObj(Object... objArr);
}
