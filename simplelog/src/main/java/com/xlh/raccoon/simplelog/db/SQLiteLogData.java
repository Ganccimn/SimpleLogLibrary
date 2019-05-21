package com.xlh.raccoon.simplelog.db;

import java.util.Date;

/**
 * 数据库 SLog 日志模型。
 *
 * @see SLogPosition SLog 位置信息模型
 */
public class SQLiteLogData extends SLogPosition {
  //日志信息
  private String logMsg;
  //
  private long createTime;

  public SQLiteLogData(String className, String fileName, String methodName,
                       String line, String logMsg) {
    super(className, fileName, methodName, line);
    this.logMsg = logMsg;
    this.createTime = new Date().getTime();
  }

  public SQLiteLogData(SLogPosition position, String logMsg) {
    super(position.className, position.fileName, position.methodName, position.line);
    this.logMsg = logMsg;
    this.createTime = new Date().getTime();
  }
}
