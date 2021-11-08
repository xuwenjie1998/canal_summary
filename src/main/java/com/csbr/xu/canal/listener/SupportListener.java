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
 * @description: 监听csbr_sales_perform_data.tr_customer_support_price表增删改
 * @author: xwj
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

    /***
     * 监听插入操作
     * @author: xwj
     * @create: 2021/11/8 11:05
     * @param: [canalMsg, rowChange]
     * @return: void
     **/
    @InsertListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_customer_support_price")
    public void onEventInsertData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
        List<Map<String, String>> supportList = eventData.insertDate(canalMsg, rowChange);
        if (CollectionUtils.isEmpty(supportList)) {
            return;
        }
        dwsService.insertSupport(supportList);
        log.info("====================== INSERT END =============================");
    }

    /***
     * 监听删除操作
     * @author: xwj
     * @create: 2021/11/8 11:06
     * @param: [rowChange, canalMsg]
     * @return: void
     **/
    @DeleteListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_customer_support_price")
    public void onEventDeleteData(CanalEntry.RowChange rowChange, CanalMsg canalMsg) throws Exception {
        List<Map<String, String>> deleteList = eventData.deleteData(rowChange, canalMsg);
        if (CollectionUtils.isEmpty(deleteList)) {
            return;
        }
        dwsService.insertSupport(deleteList);
        log.info("===================== DELETE END =================================");
    }

    /***
     * 监听更新操作
     * @author: xwj
     * @create: 2021/11/8 11:06
     * @param: [canalMsg, rowChange]
     * @return: void
     **/
    @UpdateListenPoint(destination = "example", schema = "csbr_sales_perform_data", table = "tr_customer_support_price")
    public void onEventUpdateData(CanalMsg canalMsg, CanalEntry.RowChange rowChange) throws Exception {
        List<Map<String, Map<String, String>>> deleteAfterMap = eventData.updateData(canalMsg, rowChange);

        dwsService.updateSupport(deleteAfterMap);

        log.info("======================= UPDATE END ===============================");
    }
}