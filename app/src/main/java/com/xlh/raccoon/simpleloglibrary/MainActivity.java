package com.xlh.raccoon.simpleloglibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xlh.raccoon.simplelog.SLog;
import com.xlh.raccoon.simplelog.config.SLogConfig;
import com.xlh.raccoon.simplelog.config.SLogConfiguration;
import com.xlh.raccoon.simplelog.constant.ListTypeEnum;
import com.xlh.raccoon.simplelog.constant.MapTypeEnum;
import com.xlh.raccoon.simplelog.constant.SLogStatusEnum;
import com.xlh.raccoon.simplelog.db.SQLiteLogData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SLogConfig {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //配置 SLog
    SLog.init(this, this);
    SQLiteLogData.clear();
    SLog.out.printObj("abcd", 123, true, "^##");
    SLog.out.print(new int[]{1, 2, 3, 4});
    SLog.out.print(new int[][]{
        new int[]{1, 2, 3},
        new int[]{4, 5, 6},
        new int[]{7, 8, 9}
    });
    List<Integer> list = new ArrayList<>();
    list.add(12);
    list.add(1);
    list.add(45);
    SLog.out.print(list, ListTypeEnum.NORMAL);
    SLog.out.print(list);
    Map<String, Boolean> map = new HashMap<>();
    map.put("xx", false);
    map.put("yy", true);
    map.put("zz", null);
    SLog.out.print(map, MapTypeEnum.TABLE);
    SLog.out.print(map);
  }

  @Override
  public void config(SLogConfiguration config) {
    config.status = SLogStatusEnum.AUTO;
    config.saveLog = true;
  }
}
