package com.csbr.xu.canal.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 记录增删改操作的持久层接口
 * @author: xwj
 * @create: 2021-11-08 11:10
 **/

@Mapper
public interface DwsMapper {

    void insertOrder(List<Map<String,String>> list) throws Exception;

    void insertOrderMap(Map<String,String> map) throws Exception;

    Map<String,String> selectOrder(Map<String,String> map) throws Exception;


    void insertPreOrder(List<Map<String,String>> list) throws Exception;

    void insertPreOrderMap(Map<String,String> map) throws Exception;

    Map<String,String> selectPreOrder(Map<String,String> map) throws Exception;


    void insertFlow(List<Map<String,String>> list) throws Exception;

    void insertFlowMap(Map<String,String> map) throws Exception;

    Map<String,String> selectFlow(Map<String,String> map) throws Exception;


    void insertClock(List<Map<String,String>> list) throws Exception;

    void insertClockMap(Map<String,String> map) throws Exception;

    Map<String,String> selectClock(Map<String,String> map) throws Exception;


    void insertSupport(List<Map<String,String>> list) throws Exception;

    void insertSupportMap(Map<String,String> map) throws Exception;

    Map<String,String> selectSupport(Map<String,String> map) throws Exception;


    void insertVisit(List<Map<String,String>> list) throws Exception;

    void insertVisitMap(Map<String,String> map) throws Exception;

    Map<String,String> selectVisit(Map<String,String> map) throws Exception;

}
