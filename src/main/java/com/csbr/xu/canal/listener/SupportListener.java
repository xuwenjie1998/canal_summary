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

import java.util.Map;

/**
 * @program: canal_summary
 * @description: csbr_sales_perform_data.tr_customer_support_price
 * @author: ASUS
 * @create: 2021-11-04 14:32
 **/
@CanalEventListener
@Slf4j
@Transactional
public class SupportListener {
    @Autowired
    private DwsService dwsService;

    @Autowired
    private EventData eventData;

    @InsertListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_customer_support_price")
    public void onEventInsertData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
        Map<String, String> supportMap = eventData.insertDate(canalMsg, rowChange);
        if (CollectionUtils.isEmpty(supportMap)) {
            return;
        }
        dwsService.insertSupport(supportMap);
        log.info("====================== INSERT END =============================");
    }

    @DeleteListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_customer_support_price")
    public void onEventDeleteData(CanalEntry.RowChange rowChange, CanalMsg canalMsg) throws Exception {
        Map<String, String> deleteMap = eventData.deleteData(rowChange, canalMsg);
        if (CollectionUtils.isEmpty(deleteMap)) {
            return;
        }
        dwsService.insertSupport(deleteMap);
        log.info("===================== DELETE END =================================");
    }

    @UpdateListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_customer_support_price")
    public void onEventUpdateData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
        Map<String, Map<String, String>> deleteAfterMap = eventData.updateData(canalMsg, rowChange);

        dwsService.updateSupport(deleteAfterMap);

        log.info("======================= UPDATE END ===============================");
    }
}