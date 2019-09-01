package com.xlh.raccoon.simplelog.core;

import android.util.Log;

import com.xlh.raccoon.simplelog.SLog;
import com.xlh.raccoon.simplelog.constant.ListTypeEnum;
import com.xlh.raccoon.simplelog.constant.MapTypeEnum;
import com.xlh.raccoon.simplelog.db.SLogPosition;
import com.xlh.raccoon.simplelog.db.SQLiteLogData;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class DebugSLogMethod implements SLogMethod {

  private static final String TAG = DebugSLogMethod.class.getName();

  /**
   * 扩展输出逻辑。
   *
   * @param msg
   * @return
   */
  public String outPrint(String msg) {
    SLogPosition position = buildLogPosition();
    String positionStr = position.getFilePositionString();
    Log.i(positionStr, msg);
    String out = String.format("%s: %s", positionStr, msg);
    if (SLog.configuration.saveLog) {
      SQLiteLogData logData = new SQLiteLogData(position, msg);
      logData.save();
    }
    return out;
  }

  @Override
  public void print(int num) {
    outPrint(num + "");
  }

  @Override
  public void print(float num) {
    outPrint(num + "");
  }

  @Override
  public void print(long num) {
    outPrint(num + "");
  }

  @Override
  public void print(double num) {
    outPrint(num + "");
  }

  /**
   * @param msg 打印字符串。
   */
  @Override
  public void print(String msg) {
    outPrint(msg);
  }

  /**
   * 打印int数组。
   *
   * @param arr
   */
  @Override
  public void print(int[] arr) {
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    for (int item : arr) {
      sb.append(item);
      sb.append(" , ");
    }
    sb.setLength(sb.length() - 3);
    sb.append(" ]");
    outPrint(sb.toString());
  }

  /**
   * 打印二维int数组。
   *
   * @param arr
   */
  @Override
  public void print(int[][] arr) {
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
    outPrint(sb.toString());
  }

  /**
   * 打印集合。
   *
   * @param list
   * @param listTypeEnum
   */
  @Override
  public void print(List list, ListTypeEnum listTypeEnum) {
    if (listTypeEnum.flag == ListTypeEnum.NORMAL.flag) {
      outPrint(list.toString());
    }
  }

  @Override
  public void print(List list) {
    outPrint(list.toString());
  }

  /**
   * 打印集合。
   *
   * @param map
   * @param mapTypeEnum
   */
  @Override
  public void print(Map map, MapTypeEnum mapTypeEnum) {
    if (mapTypeEnum == MapTypeEnum.TABLE) {
      printMapTable(map);
    } else {
      outPrint(map.toString());
    }
  }

  @Override
  public void print(Map map) {
    outPrint(map.toString());
  }

  /**
   * 打印异常
   *
   * @param ex
   */
  @Override
  public void print(Exception ex) {
    print(ex.getMessage());
  }

  /**
   * @param sb
   */
  @Override
  public void print(StringBuffer sb) {
    if (sb != null) {
      outPrint(sb.toString());
    } else {
      outPrint("StringBuffer is null");
    }
  }

  /**
   * @param sb
   */
  @Override
  public void print(StringBuilder sb) {
    if (sb != null) {
      outPrint(sb.toString());
    } else {
      outPrint("StringBuilder is null");
    }
  }

  /**
   * 格式化输出。
   *
   * @param format
   * @param objects
   */
  @Override
  public void print(String format, Object... objects) {
    outPrint(String.format(format, objects));
  }

  /**
   * 打印字段类型。
   *
   * @param field
   */
  @Override
  public void print(Field field) {
    if (field == null) {
      outPrint("Field is null");
      return;
    }
    print("Field Type:%s", field.getGenericType());
  }

  /**
   * 打印字段信息。
   *
   * @param field
   */
  @Override
  public void print(Field field, Object obj) {
    if (field == null) {
      outPrint("Field is null");
      return;
    }
    String val = "null";
    try {
      val = field.get(obj).toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    print("Type:%s Value:%s", field.getGenericType(), val);
  }

  /**
   * method:print(Map map, MapTypeEnum mapTypeEnum)
   *
   * @param map
   */
  private void printMapTable(Map<Object, Object> map) {
    StringBuffer sb = new StringBuffer("---👇---\n");
    for (Map.Entry<Object, Object> entry : map.entrySet()) {
      sb.append("[ ");
      sb.append(entry.getKey());
      sb.append(" | ");
      sb.append(entry.getValue());
      sb.append(" ]\n");
    }
    outPrint(sb.toString());
  }

  /**
   * 例如：[1:abcd] [2:123] [3:true] [4:^##]
   *
   * @param objArr 打印多个参数
   */
  @Override
  public void printObj(Object... objArr) {
    StringBuffer sb = new StringBuffer();
    int len = objArr.length;
    for (int i = 0; i < len; i++) {
      sb.append("[ ");
      sb.append(i + 1);
      sb.append(" : ");
      sb.append(objArr[i].toString());
      sb.append(" ] ");
    }
    outPrint(sb.toString());
  }

  /**
   * @return StackTraceElement 转换成 SLogPosition
   */
  private static SLogPosition buildLogPosition() {
    StackTraceElement element = getTargetStackTraceElement();
    return new SLogPosition(
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
      if (stackTrace[index].getClassName().equals(TAG)
          && !stackTrace[index + 1].getClassName().equals(TAG)) {
        return stackTrace[index + 1];
      }
    }
    return new StackTraceElement("Not Found", "Not Found",
        "Not Found", 0);
  }
}
