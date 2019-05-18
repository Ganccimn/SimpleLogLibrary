package com.xlh.raccoon.simplelog;

import android.util.Log;

import java.util.List;
import java.util.Map;

public class SLog {
  /**
   * @param msg 打印字符串
   */
  public static void print(String msg) {
    Log.i(buildLogPosition().getFilePositionString(), msg);
  }

  /**
   * 打印int数组
   *
   * @param arr
   */
  public static void print(int[] arr) {
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    for (int item : arr) {
      sb.append(item);
      sb.append(" , ");
    }
    sb.setLength(sb.length() - 3);
    sb.append(" ]");
    Log.i(buildLogPosition().getFilePositionString(), sb.toString());
  }

  /**
   * 打印二维int数组
   *
   * @param arr
   */
  public static void print(int[][] arr) {
    StringBuffer sb = new StringBuffer("---👇---\n");
    for (int[] row : arr) {
      sb.append("[ ");
      for (int col : row) {
        sb.append(col);
        sb.append(" , ");
      }
      sb.setLength(sb.length() - 3);
      sb.append(" ]\n");
    }
    Log.i(buildLogPosition().getFilePositionString(), sb.toString());
  }

  /**
   * 打印集合。
   *
   * @param list
   * @param listTypeEnum
   */
  public static void print(List list, ListTypeEnum listTypeEnum) {
    if (listTypeEnum.flag == ListTypeEnum.NORMAL.flag) {
      Log.i(buildLogPosition().getFilePositionString(), list.toString());
    }
  }

  /**
   * 打印集合。
   *
   * @param map
   * @param mapTypeEnum
   */
  public static void print(Map map, MapTypeEnum mapTypeEnum) {
    if (mapTypeEnum == MapTypeEnum.TABLE) {
      printMapTable(map);
    } else {
      Log.i(buildLogPosition().getFilePositionString(), map.toString());
    }
  }

  /**
   * 打印异常
   *
   * @param ex
   */
  public static void print(Exception ex) {
    print(ex.getMessage());
  }

  private static void printMapTable(Map<Object, Object> map) {
    StringBuffer sb = new StringBuffer("---👇---\n");
    for (Map.Entry<Object, Object> entry : map.entrySet()) {
      sb.append("[ ");
      sb.append(entry.getKey());
      sb.append(" | ");
      sb.append(entry.getValue());
      sb.append(" ]\n");
    }
    Log.i(buildLogPosition().getFilePositionString(), sb.toString());
  }

  /**
   * 例如：[1:abcd] [2:123] [3:true] [4:^##]
   *
   * @param objArr 打印多个参数
   */
  public static void printObj(Object... objArr) {
    StringBuffer sb = new StringBuffer();
    int len = objArr.length;
    for (int i = 0; i < len; i++) {
      sb.append("[ ");
      sb.append(i + 1);
      sb.append(" : ");
      sb.append(objArr[i].toString());
      sb.append(" ] ");
    }
    Log.i(buildLogPosition().getFilePositionString(), sb.toString());
  }

  /**
   * @return StackTraceElement 转换成 LogPosition
   */
  private static LogPosition buildLogPosition() {
    StackTraceElement element = getTargetStackTraceElement();
    return new LogPosition(
        element.getClassName(),
        element.getFileName(),
        element.getMethodName(),
        "" + element.getLineNumber());
  }

  /**
   * @return 从堆栈轨迹(StackTraceElement)中获取调用 SLog 的位置信息
   */
  private static StackTraceElement getTargetStackTraceElement() {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    int len = stackTrace.length;
    for (int index = 0; index < len; index++) {
      //此处判断：遍历找到第一条 SLog 的栈信息，并判断下一条栈信息是否还是 SLog，如果是就继续循环。
      // 直到找到最后一条 SLog 的栈信息，它的下一条就是 SLog 所在位置的栈信息。
      if (stackTrace[index].getClassName().equals(SLog.class.getName())
          && !stackTrace[index + 1].getClassName().equals(SLog.class.getName())) {
        return stackTrace[index + 1];
      }
    }
    return new StackTraceElement("Not Found", "Not Found",
        "Not Found", 0);
  }
}
