package com.csbr.xu.canal.common;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.csbr.cloud.canal.client.core.CanalMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 抽取监听器增删改的监听代码
 * @author: ASUS
 * @create: 2021-11-04 09:23
 **/
@Slf4j
@Component
public class EventData {

    @Autowired
    private PrintColumns printColumns;

    @Autowired
    private JudgeTime judgeTime;

    public Map<String, String> insertDate(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception{
        log.info("====================== INSERT START ==========================");

        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();

        for (CanalEntry.RowData rowData : rowDatasList) {
            String sql = "use " + canalMsg.getSchemaName() + ";\n";
            StringBuffer colums = new StringBuffer();
            StringBuffer values = new StringBuffer();
            rowData.getAfterColumnsList().forEach((c) -> {
                colums.append(c.getName() + ",");
                values.append("'" + c.getValue() + "',");
            });

            sql += "INSERT INTO " + canalMsg.getTableName() + "(" + colums.substring(0, colums.length() - 1) + ") VALUES(" + values.substring(0, values.length() - 1) + ");";
            log.info(sql);

            String tableName = canalMsg.getTableName();
            String schemaTableName = canalMsg.getSchemaName() + '.' + canalMsg.getTableName();
            Map<String, String> afterMap = printColumns.printColumnsInsert(rowData.getAfterColumnsList(), schemaTableName);
            if (CollectionUtils.isEmpty(afterMap)){
                log.warn("afterMap为空");
                return null;
            }else if(judgeTime.JudgeTime(afterMap)) {
                return null;
            }
            return afterMap;
        }
        return null;
    }

    public Map<String,String> deleteData(CanalEntry.RowChange rowChange, CanalMsg canalMsg) throws Exception{
        log.info("====================== DELETE START ==========================");
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {

            if (!CollectionUtils.isEmpty(rowData.getBeforeColumnsList())) {
                String sql = "use " + canalMsg.getSchemaName() + ";\n";

                sql += "DELETE FROM " + canalMsg.getTableName() + " WHERE ";
                StringBuffer idKey = new StringBuffer();
                StringBuffer idValue = new StringBuffer();
                for (CanalEntry.Column c : rowData.getBeforeColumnsList()) {
                    if (c.getIsKey()) {
                        idKey.append(c.getName());
                        idValue.append(c.getValue());
                    }
                }

                sql += idKey + " =" + idValue + ";";
                log.info(sql);

                String tableName = canalMsg.getTableName();
                String schemaTableName = canalMsg.getSchemaName() + '.' + canalMsg.getTableName();

                Map<String, String> deleteMap = printColumns.printColumnsDelete(rowData.getBeforeColumnsList(), schemaTableName);
                if (CollectionUtils.isEmpty(deleteMap) || StringUtils.isBlank(deleteMap.get("account_month")) || deleteMap.get("account_month").length() != 6) {
                    log.error("deleteMap为空或核算月格式错误！");
                    return null;
                } else if (judgeTime.JudgeTime(deleteMap)) {
                    return null;
                }
                return deleteMap;
            }
        }
        return null;
    }

    public Map<String, Map<String, String>> updateData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception{
        log.info("====================== UPDATE START ==========================");
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {

            String sql = "use " + canalMsg.getSchemaName() + ";\n";
            StringBuffer updates = new StringBuffer();
            StringBuffer conditions = new StringBuffer();
            rowData.getAfterColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    conditions.append(c.getName() + "='" + c.getValue() + "'");
                } else {
                    updates.append(c.getName() + "='" + c.getValue() + "',");
                }
            });

            sql += "UPDATE " + canalMsg.getTableName() + " SET " + updates.substring(0, updates.length() - 1) + " WHERE " + conditions;
            log.info(sql);

            String tableName = canalMsg.getTableName();
            String schemaTableName = canalMsg.getSchemaName() + '.' + canalMsg.getTableName();
            Map<String, String> mapBefore = printColumns.printColumnsDelete(rowData.getBeforeColumnsList(), schemaTableName);
            Map<String, String> mapAfter = printColumns.printColumnsInsert(rowData.getAfterColumnsList(), schemaTableName);

            if (CollectionUtils.isEmpty(mapBefore) || CollectionUtils.isEmpty(mapAfter)) {
                log.error("mapBefore或mapAfter为空");
                return null;
            }

            //guid无修改时mapAfter中guid为null,取mapBefore中guid赋给mapAfter
            if (schemaTableName.equals("csbr_purchase_data.tr_order")) {
                if (StringUtils.isEmpty(mapAfter.get("GUID"))) {
                    String guid = mapBefore.get("GUID");
                    mapAfter.put("GUID", guid);
                }
            } else {
                if (StringUtils.isEmpty(mapAfter.get("guid"))) {
                    String guid = mapBefore.get("guid");
                    mapAfter.put("guid", guid);
                }
            }

            Map<String,Map<String,String>> beforeAndAfterMap = new HashMap<>();
            beforeAndAfterMap.put("beforeMap",mapBefore);
            beforeAndAfterMap.put("afterMap",mapAfter);

            return beforeAndAfterMap;
        }
        return null;
    }
}