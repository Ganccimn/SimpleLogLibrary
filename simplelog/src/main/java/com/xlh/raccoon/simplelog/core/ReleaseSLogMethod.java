package com.xlh.raccoon.simplelog.core;

import com.xlh.raccoon.simplelog.constant.ListTypeEnum;
import com.xlh.raccoon.simplelog.constant.MapTypeEnum;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 关闭 SLog 日志输出 调用该类，该类方法都为空。
 */
public class ReleaseSLogMethod implements SLogMethod {
  @Override
  public void print(int num) {

  }

  @Override
  public void print(float num) {

  }

  @Override
  public void print(long num) {

  }

  @Override
  public void print(double num) {

  }

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
  public void print(List list) {
    
  }

  @Override
  public void print(Map map, MapTypeEnum mapTypeEnum) {

  }

  @Override
  public void print(Map map) {

  }

  @Override
  public void print(Exception ex) {

  }

  @Override
  public void print(StringBuffer sb) {

  }

  @Override
  public void print(StringBuilder sb) {

  }

  @Override
  public void print(String format, Object... objects) {

  }

  @Override
  public void print(Field field) {

  }

  @Override
  public void print(Field field, Object obj) {

  }

  @Override
  public void printObj(Object... objArr) {

  }
}
