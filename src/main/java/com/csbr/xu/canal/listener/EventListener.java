//package com.csbr.xu.canal.listener;
//
//import com.alibaba.otter.canal.protocol.CanalEntry;
//import com.csbr.cloud.canal.annotation.CanalEventListener;
//import com.csbr.cloud.canal.annotation.DeleteListenPoint;
//import com.csbr.cloud.canal.annotation.InsertListenPoint;
//import com.csbr.cloud.canal.annotation.UpdateListenPoint;
//import com.csbr.cloud.canal.client.core.CanalMsg;
//import com.csbr.xu.canal.common.JudgeTime;
//import com.csbr.xu.canal.common.PrintColumns;
//import com.csbr.xu.canal.service.DwsService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Map;
//
//@CanalEventListener
//@Slf4j
//@Transactional
///**
// * @program: canal_summary
// * @description: 监听表增删改，并记录到dws_summary_tmp表
// * @author: ASUS
// * @create: 2021-11-03 16:32
// **/
//public class EventListener {
//
//    @Autowired
//    private DwsService dwsService;
//
//    @Autowired
//    private PrintColumns printColumns;
//
//    @Autowired
//    private JudgeTime judgeTime;
//
//    @InsertListenPoint
//    public void onEventInsertData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
//        log.info("====================== INSERT START ==========================");
//
//        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
//
//        for (CanalEntry.RowData rowData : rowDatasList) {
//            String sql = "use " + canalMsg.getSchemaName() + ";\n";
//            StringBuffer colums = new StringBuffer();
//            StringBuffer values = new StringBuffer();
//            rowData.getAfterColumnsList().forEach((c) -> {
//                colums.append(c.getName() + ",");
//                values.append("'" + c.getValue() + "',");
//            });
//
//            sql += "INSERT INTO " + canalMsg.getTableName() + "(" + colums.substring(0, colums.length() - 1) + ") VALUES(" + values.substring(0, values.length() - 1) + ");";
//            log.info(sql);
//
//            String tableName = canalMsg.getTableName();
//
//            Map<String, String> afterMap = printColumns.printColumnsInsert(rowData.getAfterColumnsList(), tableName);
//            if (CollectionUtils.isEmpty(afterMap)) {
//                log.warn("afterMap为空");
//                return;
//            } else if (judgeTime.JudgeTime(afterMap)) {
//                return;
//            } else if (tableName.equals("csbr_sales_perform_data.tr_marketing_visit")) {
//                dwsService.insertOneVisit(afterMap);
//            } else {
//                dwsService.insertOne(afterMap);
//            }
//        }
//        log.info("====================== INSERT END =============================");
//
//    }
//
//    @UpdateListenPoint
//    public void onEventUpdateData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
//        log.info("====================== UPDATE START ==========================");
//        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
//        for (CanalEntry.RowData rowData : rowDatasList) {
//
//            String sql = "use " + canalMsg.getSchemaName() + ";\n";
//            StringBuffer updates = new StringBuffer();
//            StringBuffer conditions = new StringBuffer();
//            rowData.getAfterColumnsList().forEach((c) -> {
//                if (c.getIsKey()) {
//                    conditions.append(c.getName() + "='" + c.getValue() + "'");
//                } else {
//                    updates.append(c.getName() + "='" + c.getValue() + "',");
//                }
//            });
//
//            sql += "UPDATE " + canalMsg.getTableName() + " SET " + updates.substring(0, updates.length() - 1) + " WHERE " + conditions;
//            log.info(sql);
//
//            String tableName = canalMsg.getTableName();
//            String schemaTableName = canalMsg.getSchemaName() + '.' + canalMsg.getTableName();
//            Map<String, String> mapBefore = printColumns.printColumnsDelete(rowData.getBeforeColumnsList(), schemaTableName);
//            Map<String, String> mapAfter = printColumns.printColumnsInsert(rowData.getAfterColumnsList(), schemaTableName);
//
//            if (CollectionUtils.isEmpty(mapBefore) || CollectionUtils.isEmpty(mapAfter)) {
//                log.error("mapBefore或mapAfter为空");
//                return;
//            }
//
//            //guid无修改时mapAfter中guid为null,取mapBefore中guid赋给mapAfter
//            if (schemaTableName.equals("csbr_purchase_data.tr_order")) {
//                if (StringUtils.isEmpty(mapAfter.get("GUID"))) {
//                    String guid = mapBefore.get("GUID");
//                    mapAfter.put("GUID", guid);
//                }
//            } else {
//                if (StringUtils.isEmpty(mapAfter.get("guid"))) {
//                    String guid = mapBefore.get("guid");
//                    mapAfter.put("guid", guid);
//                }
//            }
//
//            //记录修改前时间维度
//            if (!judgeTime.JudgeTime(mapBefore)) {
//                //判断更新后核算月是否是近3个月,是则跳过
//                if (schemaTableName.equals("csbr_sales_perform_data.tr_marketing_visit")) {
//                    dwsService.insertOneVisit(mapBefore);
//                } else {
//                    dwsService.insertOne(mapBefore);
//                }
//            }
//
//            //根据guid查修改后的数据
//            Map<String, String> mapSelect = dwsService.selectOne(mapAfter);
//            //修改程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
//            if (mapSelect.isEmpty()) {
//                log.error("mapSelect.isEmpty() == true!");
//                return;
//            } else if (judgeTime.JudgeTime(mapSelect)) {
//                return;
//            }
//            mapSelect.put("table_name", tableName);
//
//            try {
//                if (schemaTableName.equals("csbr_sales_perform_data.tr_marketing_visit")) {
//                    dwsService.insertOneVisit(mapSelect);
//                } else {
//                    dwsService.insertOne(mapSelect);
//                }
//            } catch (Exception e) {
//                log.error("更新后时间维度字段为null，sql异常");
//            }
//        }
//        log.info("\n======================= UPDATE END ===============================");
//    }
//
//    @DeleteListenPoint
//    public void onEventDeleteData(CanalEntry.RowChange rowChange, CanalMsg canalMsg) throws Exception {
//
//        log.info("====================== DELETE START ==========================");
//        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
//        for (CanalEntry.RowData rowData : rowDatasList) {
//
//            if (!CollectionUtils.isEmpty(rowData.getBeforeColumnsList())) {
//                String sql = "use " + canalMsg.getSchemaName() + ";\n";
//
//                sql += "DELETE FROM " + canalMsg.getTableName() + " WHERE ";
//                StringBuffer idKey = new StringBuffer();
//                StringBuffer idValue = new StringBuffer();
//                for (CanalEntry.Column c : rowData.getBeforeColumnsList()) {
//                    if (c.getIsKey()) {
//                        idKey.append(c.getName());
//                        idValue.append(c.getValue());
//                    }
//                }
//
//                sql += idKey + " =" + idValue + ";";
//                log.info(sql);
//
//                String tableName = canalMsg.getTableName();
//
//                Map<String, String> deleteMap = printColumns.printColumnsDelete(rowData.getBeforeColumnsList(), tableName);
//                if (CollectionUtils.isEmpty(deleteMap) || StringUtils.isBlank(deleteMap.get("account_month")) || deleteMap.get("account_month").length() != 6) {
//                    log.error("deleteMap为空或核算月格式错误！");
//                    return;
//                } else if (judgeTime.JudgeTime(deleteMap)) {
//                    return;
//                } else if (tableName.equals("csbr_sales_perform_data.tr_marketing_visit")) {
//                    dwsService.insertOneVisit(deleteMap);
//                } else {
//                    dwsService.insertOne(deleteMap);
//                }
//            }
//            log.info("\n===================== DELETE END =================================");
//        }
//    }
//}
