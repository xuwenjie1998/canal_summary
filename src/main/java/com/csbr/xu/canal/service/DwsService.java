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

    public void insertOrder(List<Map<String,String>> map) throws Exception;

    public void updateOrder(List<Map<String, Map<String, String>>> map) throws Exception;


    public void insertPreOrder(List<Map<String,String>> map) throws Exception;

    public void updatePreOrder(List<Map<String, Map<String, String>>> map) throws Exception;


    public void insertFlow(List<Map<String,String>> map) throws Exception;

    public void updateFlow(List<Map<String, Map<String, String>>> map) throws Exception;


    void insertClock(List<Map<String,String>> map) throws Exception;

    public void updateClock(List<Map<String, Map<String, String>>> map) throws Exception;


    void insertSupport(List<Map<String,String>> map) throws Exception;

    public void updateSupport(List<Map<String, Map<String, String>>> map) throws Exception;


    void insertVisit(List<Map<String,String>> map) throws Exception;

    public void updateVisit(List<Map<String, Map<String, String>>> map) throws Exception;

}
