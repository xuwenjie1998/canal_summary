package com.csbr.xu.canal.service;

import java.util.List;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 增删改的业务处理接口
 * @author: xwj
 * @create: 2021-11-08 11:10
 **/
public interface DwsService {

    /***
     * csbr_purchase_data.tr_order表的插入
     * @author: xwj
     * @create: 2021/11/8 15:10
     * @param: [list]
     * @return: void
     **/
    public void insertOrder(List<Map<String,String>> list) throws Exception;

    /***
     * csbr_purchase_data.tr_order表的更新
     * @author: xwj
     * @create: 2021/11/8 15:11
     * @param: [list]
     * @return: void
     **/
    public void updateOrder(List<Map<String, Map<String, String>>> list) throws Exception;

    /***
     * csbr_purchase_data.tr_prescription_order表的插入
     * @author: xwj
     * @create: 2021/11/8 15:12
     * @param: [list]
     * @return: void
     **/
    public void insertPreOrder(List<Map<String,String>> list) throws Exception;

    /***
     * csbr_purchase_data.tr_prescription_order表的更新
     * @author: xwj
     * @create: 2021/11/8 15:12
     * @param: [list]
     * @return: void
     **/
    public void updatePreOrder(List<Map<String, Map<String, String>>> list) throws Exception;

    /***
     * csbr_purchase_data.tr_sales_flow表的插入
     * @author: xwj
     * @create: 2021/11/8 15:12
     * @param: [list]
     * @return: void
     **/
    public void insertFlow(List<Map<String,String>> list) throws Exception;

    /***
     * csbr_purchase_data.tr_sales_flow表的更新
     * @author: xwj
     * @create: 2021/11/8 15:13
     * @param: [list]
     * @return: void
     **/
    public void updateFlow(List<Map<String, Map<String, String>>> list) throws Exception;

    /***
     * csbr_sales_perform_data.tr_customer_clock_in表的插入
     * @author: xwj
     * @create: 2021/11/8 15:13
     * @param: [list]
     * @return: void
     **/
    void insertClock(List<Map<String,String>> list) throws Exception;

    /***
     * csbr_sales_perform_data.tr_customer_clock_in表的更新
     * @author: xwj
     * @create: 2021/11/8 15:13
     * @param: [list]
     * @return: void
     **/
    public void updateClock(List<Map<String, Map<String, String>>> list) throws Exception;

    /***
     * csbr_sales_perform_data.tr_customer_support_price表的插入
     * @author: xwj
     * @create: 2021/11/8 15:13
     * @param: [list]
     * @return: void
     **/
    void insertSupport(List<Map<String,String>> list) throws Exception;

    /***
     * csbr_sales_perform_data.tr_customer_support_price表的更新
     * @author: xwj
     * @create: 2021/11/8 15:13
     * @param: [list]
     * @return: void
     **/
    public void updateSupport(List<Map<String, Map<String, String>>> list) throws Exception;

    /***
     * csbr_sales_perform_data.tr_marketing_visit表的更新
     * @author: xwj
     * @create: 2021/11/8 15:13
     * @param: [list]
     * @return: void
     **/
    void insertVisit(List<Map<String,String>> list) throws Exception;

    /***
     * csbr_sales_perform_data.tr_marketing_visit表的更新
     * @author: xwj
     * @create: 2021/11/8 15:13
     * @param: [list]
     * @return: void
     **/
    public void updateVisit(List<Map<String, Map<String, String>>> list) throws Exception;

}
