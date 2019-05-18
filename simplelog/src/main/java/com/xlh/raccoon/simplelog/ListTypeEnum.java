package com.xlh.raccoon.simplelog;

/**
 * List打印类型
 */
public enum ListTypeEnum {
  NORMAL(0, "normal");

  public int flag;
  public String name;

  ListTypeEnum(int flag, String name) {
    this.flag = flag;
    this.name = name;
  }
}
