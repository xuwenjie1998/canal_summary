package com.csbr.xu.canal.common;

import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 收集RowDate数据,转Map
 * @author: xwj
 * @create: 2021-11-03 14:45
 **/
@Component
public class PrintColumns {
    /***
     * 记录数据库插入操作的数据，insert和update都走此逻辑
     * @author: xwj
     * @create: 2021/11/8 11:20
     * @param: [columns, tableName]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     **/
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

    /***
     * 记录删除操作的数据，delete操作
     * @author: xwj
     * @create: 2021/11/8 11:20
     * @param: [columns, tableName]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     **/
    public Map<String, String> printColumnsDelete(List<CanalEntry.Column> columns, String tableName) {

        Map<String, String> map = new HashMap<>();
        map.put("table_name", tableName);
        for (CanalEntry.Column column : columns) {
            map.put(column.getName(), column.getValue());
        }
        return map;
    }
}