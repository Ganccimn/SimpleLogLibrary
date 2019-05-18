package com.xlh.raccoon.simplelog.constant;

/**
 * SLog 状态。
 */
public enum SLogStatusEnum {
  ON(0, "no"), OFF(1, "off"), AUTO(2, "auto");

  public int flag;
  public String name;

  SLogStatusEnum(int flag, String name) {
    this.flag = flag;
    this.name = name;
  }
}
