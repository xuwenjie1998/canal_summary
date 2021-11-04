package com.csbr.xu.canal.common;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 收集RowDate
 * @author: ASUS
 * @create: 2021-11-03 14:45
 **/
@Component
public class PrintColumns {
    public Map<String, String> printColumnsInsert(List<CanalEntry.Column> columns, String tableName) {

        Map<String, String> map = new HashMap<>();
        map.put("table_name", tableName);
        for (CanalEntry.Column column : columns) {
            if (column.getUpdated()) {
                map.put(column.getName(), column.getValue());
            }
        }
        return map;
    }

    public Map<String, String> printColumnsDelete(List<CanalEntry.Column> columns, String tableName) {

        Map<String, String> map = new HashMap<>();
        map.put("table_name", tableName);
        for (CanalEntry.Column column : columns) {
            map.put(column.getName(), column.getValue());
        }
        return map;
    }
}