package com.xlh.raccoon.simplelog.db;

/**
 * Log打印位置信息实体类。
 */
public class SLogPosition extends SQLiteCore {
  /**
   * 位置所在的类名。
   */
  public String className;
  /**
   * 位置所在java文件名。
   */
  public String fileName;
  /**
   * 位置所在的函数名。
   */
  public String methodName;
  /**
   * 位置所在java文件的行号。
   */
  public String line;

  public SLogPosition(String className, String fileName, String methodName, String line) {
    this.className = className;
    this.fileName = fileName;
    this.methodName = methodName;
    this.line = line;
  }

  /**
   * 打印定位信息(IDE规范，点击能点位到指定位置)。
   * 例如：(xxxx.java:12)
   */
  public String getFilePositionString() {
    return String.format("(%s:%s)", fileName, line);
  }

  /**
   * 打印位置信息。
   */
  public String toPrintString() {
    return String.format("[className:%s fileName:%s methodName:%s line:%s]",
        className, fileName, methodName, line);
  }
}
