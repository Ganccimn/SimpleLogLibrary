package com.xlh.raccoon.simplelog;

import android.content.Context;
import android.util.Log;

import com.xlh.raccoon.simplelog.config.SLogConfig;
import com.xlh.raccoon.simplelog.config.SLogConfiguration;
import com.xlh.raccoon.simplelog.constant.SLogStatusEnum;
import com.xlh.raccoon.simplelog.core.DebugSLogMethod;
import com.xlh.raccoon.simplelog.core.ReleaseSLogMethod;
import com.xlh.raccoon.simplelog.core.SLogMethod;

/**
 * 主类。
 */
public class SLog {
  /**
   * SLog 配置类。
   */
  public static SLogConfiguration configuration = new SLogConfiguration();
  /**
   * SLog 方法类。
   */
  public static SLogMethod out = new DebugSLogMethod();

  /**
   * 初始化 SLogConfig 配置信息
   * 根据环境和配置实例化 SLogMethod。
   *
   * @param logConfig
   */
  public static void init(Context context, SLogConfig logConfig) {
    if (context != null) {
      configuration.setContext(context);
    }
    if (logConfig != null) {
      logConfig.config(configuration);
      config();
    } else {
      out = getReleaseInstance();
      Log.e(SLog.class.getSimpleName(), "init: SLog 未配置！");
    }
  }

  /**
   * 读取配置信息。
   */
  private static void config() {
    if (configuration == null) {
      out = getDebugInstance();
      return;
    }
    //配置 SLog 输出开关
    if (configuration.status == SLogStatusEnum.AUTO) {
      //自动模式下根据BuildType来控制开关。
      if (BuildConfig.DEBUG) {
        out = getDebugInstance();
      } else {
        out = getReleaseInstance();
      }
    } else if (configuration.status == SLogStatusEnum.ON) {
      out = getDebugInstance();
    } else if (configuration.status == SLogStatusEnum.OFF) {
      out = getReleaseInstance();
    }
  }

  /**
   * 获得 DebugSLogMethod (拥有真正的打印逻辑)。
   *
   * @return
   */
  private static SLogMethod getDebugInstance() {
    if (out == null) {
      return new DebugSLogMethod();
    }
    if (out instanceof DebugSLogMethod) {
      return out;
    } else {
      return new DebugSLogMethod();
    }
  }

  /**
   * 获取 ReleaseSLogMethod (该类的方法都是空方法)。
   *
   * @return
   */
  private static SLogMethod getReleaseInstance() {
    if (out == null) {
      return new ReleaseSLogMethod();
    }
    if (out instanceof ReleaseSLogMethod) {
      return out;
    } else {
      return new ReleaseSLogMethod();
    }
  }
}
