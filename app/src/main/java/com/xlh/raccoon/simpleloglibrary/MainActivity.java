package com.xlh.raccoon.simpleloglibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xlh.raccoon.simplelog.ListTypeEnum;
import com.xlh.raccoon.simplelog.MapTypeEnum;
import com.xlh.raccoon.simplelog.SLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SLog.printObj("abcd", 123, true, "^##");
    SLog.print(new int[]{1, 2, 3, 4});
    SLog.print(new int[][]{
        new int[]{1, 2, 3},
        new int[]{4, 5, 6},
        new int[]{7, 8, 9}
    });
    List<Integer> list = new ArrayList<>();
    list.add(12);
    list.add(1);
    list.add(45);
    SLog.print(list, ListTypeEnum.NORMAL);
    Map<String, Boolean> map = new HashMap<>();
    map.put("xx", false);
    map.put("yy", true);
    map.put("zz", null);
    SLog.print(map, MapTypeEnum.TABLE);
  }
}
