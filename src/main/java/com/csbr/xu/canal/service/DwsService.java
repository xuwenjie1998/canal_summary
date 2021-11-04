package com.csbr.xu.canal.service;

import java.util.Map;

public interface DwsService {

    public void insertOrder(Map<String,String> map) throws Exception;

    public void updateOrder(Map<String, Map<String, String>> map) throws Exception;

    public void insertPreOrder(Map<String,String> map) throws Exception;

    public void updatePreOrder(Map<String, Map<String, String>> map) throws Exception;

    public void insertFlow(Map<String,String> map) throws Exception;

    public void updateFlow(Map<String, Map<String, String>> map) throws Exception;

    void insertClock(Map<String,String> map) throws Exception;

    public void updateClock(Map<String, Map<String, String>> map) throws Exception;

    void insertSupport(Map<String,String> map) throws Exception;

    public void updateSupport(Map<String, Map<String, String>> map) throws Exception;

    void insertVisit(Map<String,String> map) throws Exception;

    public void updateVisit(Map<String, Map<String, String>> map) throws Exception;




//    public void insertOne(Map<String,String> map) throws Exception;
//
//    public void insertOrder(Map<String,String> map) throws Exception;
//
//    public void insertOneVisit(Map<String,String> map) throws Exception;
//
//    /**
//     * 更新时查询更新前某些字段
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public Map<String,String> selectOne(Map<String,String> map) throws Exception;
}
