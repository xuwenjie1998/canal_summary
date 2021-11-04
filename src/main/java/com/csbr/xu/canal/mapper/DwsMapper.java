package com.csbr.xu.canal.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DwsMapper {

    void insertOrder(Map<String,String> orderMap) throws Exception;

    Map<String,String> selectOrder(Map<String,String> map) throws Exception;

    void insertPreOrder(Map<String,String> orderMap) throws Exception;

    Map<String,String> selectPreOrder(Map<String,String> map) throws Exception;

    void insertFlow(Map<String,String> orderMap) throws Exception;

    Map<String,String> selectFlow(Map<String,String> map) throws Exception;

    void insertClock(Map<String,String> orderMap) throws Exception;

    Map<String,String> selectClock(Map<String,String> map) throws Exception;

    void insertSupport(Map<String,String> orderMap) throws Exception;

    Map<String,String> selectSupport(Map<String,String> map) throws Exception;

    void insertVisit(Map<String,String> orderMap) throws Exception;

    Map<String,String> selectVisit(Map<String,String> map) throws Exception;



//    void insertOne(Map<String,String> map) throws Exception;
//
//    void insertOrder(Map<String,String> map) throws Exception;
//
//    void insertOneVisit(Map<String,String> map) throws Exception;
//
//    Map<String,String> selectOne(Map<String,String> map) throws Exception;
}
