package com.csbr.xu.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.csbr.cloud.canal.annotation.CanalEventListener;
import com.csbr.cloud.canal.annotation.DeleteListenPoint;
import com.csbr.cloud.canal.annotation.InsertListenPoint;
import com.csbr.cloud.canal.annotation.UpdateListenPoint;
import com.csbr.cloud.canal.client.core.CanalMsg;
import com.csbr.xu.canal.common.EventData;
import com.csbr.xu.canal.service.DwsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 监听csbr_sales_perform_data.tr_marketing_visit表增删改
 * @author: xwj
 * @create: 2021-11-04 14:44
 **/
@CanalEventListener
@Slf4j
@Transactional
public class VisitListener {
    @Autowired
    private DwsService dwsService;

    @Autowired
    private EventData eventData;

    /***
     * 监听插入操作
     * @author: xwj
     * @create: 2021/11/8 11:06
     * @param: [canalMsg, rowChange]
     * @return: void
     **/
    @InsertListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_marketing_visit")
    public void onEventInsertData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
        List<Map<String, String>> visitList = eventData.insertDate(canalMsg, rowChange);
        if (CollectionUtils.isEmpty(visitList)) {
            return;
        }
        dwsService.insertVisit(visitList);
        log.info("====================== INSERT END =============================");
    }

    /***
     * 监听删除操作
     * @author: xwj
     * @create: 2021/11/8 11:06
     * @param: [rowChange, canalMsg]
     * @return: void
     **/
    @DeleteListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_marketing_visit")
    public void onEventDeleteData(CanalEntry.RowChange rowChange, CanalMsg canalMsg) throws Exception {
        List<Map<String, String>> deleteList = eventData.deleteData(rowChange, canalMsg);
        if (CollectionUtils.isEmpty(deleteList)) {
            return;
        }
        dwsService.insertVisit(deleteList);
        log.info("===================== DELETE END =================================");
    }

    /***
     * 监听更新操作
     * @author: xwj
     * @create: 2021/11/8 11:06
     * @param: [canalMsg, rowChange]
     * @return: void
     **/
    @UpdateListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_marketing_visit")
    public void onEventUpdateData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
        List<Map<String, Map<String, String>>> deleteAfterMap = eventData.updateData(canalMsg, rowChange);

        dwsService.updateVisit(deleteAfterMap);

        log.info("======================= UPDATE END ===============================");
    }
}