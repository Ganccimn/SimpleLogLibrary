package com.xlh.raccoon.simplelog.core;

import com.xlh.raccoon.simplelog.constant.ListTypeEnum;
import com.xlh.raccoon.simplelog.constant.MapTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * 关闭 SLog 日志输出 调用该类，该类方法都为空。
 */
public class ReleaseSLogMethod implements SLogMethod {
  @Override
  public void print(String msg) {

  }

  @Override
  public void print(int[] arr) {

  }

  @Override
  public void print(int[][] arr) {

  }

  @Override
  public void print(List list, ListTypeEnum listTypeEnum) {

  }

  @Override
  public void print(Map map, MapTypeEnum mapTypeEnum) {

  }

  @Override
  public void print(Exception ex) {

  }

  @Override
  public void printObj(Object... objArr) {

  }
}
