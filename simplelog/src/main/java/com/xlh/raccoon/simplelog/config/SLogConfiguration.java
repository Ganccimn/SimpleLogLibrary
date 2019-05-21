package com.xlh.raccoon.simplelog.config;

import android.content.Context;

import com.xlh.raccoon.simplelog.constant.SLogStatusEnum;

/**
 * SLog 配置类。
 */
public class SLogConfiguration {
  /**
   * 程序上下文引用（提供给Sqlite数据库）。
   */
  private Context context;

  /**
   * SLog 日志输出开关。
   */
  public SLogStatusEnum status = SLogStatusEnum.AUTO;

  /**
   * 是否记录日志信息。
   */
  public boolean saveLog;

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }
}
