package com.xlh.raccoon.simplelog;

/**
 * Map打印类型
 */
public enum MapTypeEnum {
  NORMAL(0, "normal"), TABLE(1, "table");

  public int flag;
  public String name;

  MapTypeEnum(int flag, String name) {
    this.flag = flag;
    this.name = name;
  }
}
