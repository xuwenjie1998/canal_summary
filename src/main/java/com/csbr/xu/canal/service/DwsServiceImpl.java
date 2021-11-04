package com.csbr.xu.canal.service;

import com.csbr.xu.canal.common.JudgeTime;
import com.csbr.xu.canal.mapper.DwsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Service
@Slf4j
@Transactional
public class DwsServiceImpl implements DwsService {

    @Autowired
    private DwsMapper dwsMapper;

    @Autowired
    private JudgeTime judgeTime;

    @Override
    public void insertOrder(Map<String,String> orderMap) throws Exception {
        dwsMapper.insertOrder(orderMap);
    }

    @Override
    public void updateOrder(Map<String, Map<String, String>> map) throws Exception {
        Map<String, String> beforeMap = map.get("beforeMap");
        Map<String, String> afterMap = map.get("afterMap");

        //记录修改前时间维度
        if (judgeTime.JudgeTime(beforeMap)) {
            //判断更新后核算月是否是近3个月,是则跳过
            return;
        }
        dwsMapper.insertOrder(beforeMap);

        //根据guid查修改后的数据
        Map<String, String> selectMap = dwsMapper.selectOrder(afterMap);
        //修改程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
        if (CollectionUtils.isEmpty(selectMap)) {
            log.error("selectMap.isEmpty() == true!");
            return ;
        } else if (judgeTime.JudgeTime(selectMap)) {
            return ;
        }

        try {
            String table_name = afterMap.get("table_name");
            selectMap.put("table_name",table_name);
            dwsMapper.insertOrder(selectMap);
        } catch (Exception e) {
            log.error("更新后时间维度字段为null，sql异常");
        }
    }

    @Override
    public void insertPreOrder(Map<String, String> map) throws Exception {
        dwsMapper.insertPreOrder(map);
    }

    @Override
    public void updatePreOrder(Map<String, Map<String, String>> map) throws Exception {
        Map<String, String> beforeMap = map.get("beforeMap");
        Map<String, String> afterMap = map.get("afterMap");

        //记录修改前时间维度
        if (judgeTime.JudgeTime(beforeMap)) {
            //判断更新后核算月是否是近3个月,是则跳过
            return;
        }
        dwsMapper.insertPreOrder(beforeMap);

        //根据guid查修改后的数据
        Map<String, String> selectMap = dwsMapper.selectPreOrder(afterMap);
        //修改程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
        if (CollectionUtils.isEmpty(selectMap)) {
            log.error("selectMap.isEmpty() == true!");
            return ;
        } else if (judgeTime.JudgeTime(selectMap)) {
            return ;
        }

        try {
            String table_name = afterMap.get("table_name");
            selectMap.put("table_name",table_name);
            dwsMapper.insertPreOrder(selectMap);
        } catch (Exception e) {
            log.error("更新后时间维度字段为null，sql异常");
        }
    }

    @Override
    public void insertFlow(Map<String, String> map) throws Exception {
        dwsMapper.insertFlow(map);
    }

    @Override
    public void updateFlow(Map<String, Map<String, String>> map) throws Exception {
        Map<String, String> beforeMap = map.get("beforeMap");
        Map<String, String> afterMap = map.get("afterMap");

        //记录修改前时间维度
        if (judgeTime.JudgeTime(beforeMap)) {
            //判断更新后核算月是否是近3个月,是则跳过
            return;
        }
        dwsMapper.insertFlow(beforeMap);

        //根据guid查修改后的数据
        Map<String, String> selectMap = dwsMapper.selectFlow(afterMap);
        //修改程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
        if (CollectionUtils.isEmpty(selectMap)) {
            log.error("selectMap.isEmpty() == true!");
            return ;
        } else if (judgeTime.JudgeTime(selectMap)) {
            return ;
        }

        try {
            String table_name = afterMap.get("table_name");
            selectMap.put("table_name",table_name);
            dwsMapper.insertFlow(selectMap);
        } catch (Exception e) {
            log.error("更新后时间维度字段为null，sql异常");
        }
    }


    @Override
    public void insertClock(Map<String, String> map) throws Exception {
        dwsMapper.insertClock(map);
    }

    @Override
    public void updateClock(Map<String, Map<String, String>> map) throws Exception {
        Map<String, String> beforeMap = map.get("beforeMap");
        Map<String, String> afterMap = map.get("afterMap");

        //记录修改前时间维度
        if (judgeTime.JudgeTime(beforeMap)) {
            //判断更新后核算月是否是近3个月,是则跳过
            return;
        }
        dwsMapper.insertClock(beforeMap);

        //根据guid查修改后的数据
        Map<String, String> selectMap = dwsMapper.selectClock(afterMap);
        //修改程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
        if (CollectionUtils.isEmpty(selectMap)) {
            log.error("selectMap.isEmpty() == true!");
            return ;
        } else if (judgeTime.JudgeTime(selectMap)) {
            return ;
        }

        try {
            String table_name = afterMap.get("table_name");
            selectMap.put("table_name",table_name);
            dwsMapper.insertClock(selectMap);
        } catch (Exception e) {
            log.error("更新后时间维度字段为null，sql异常");
        }
    }

    @Override
    public void insertSupport(Map<String, String> map) throws Exception {
        dwsMapper.insertSupport(map);
    }

    @Override
    public void updateSupport(Map<String, Map<String, String>> map) throws Exception {
        Map<String, String> beforeMap = map.get("beforeMap");
        Map<String, String> afterMap = map.get("afterMap");

        //记录修改前时间维度
        if (judgeTime.JudgeTime(beforeMap)) {
            //判断更新后核算月是否是近3个月,是则跳过
            return;
        }
        dwsMapper.insertSupport(beforeMap);

        //根据guid查修改后的数据
        Map<String, String> selectMap = dwsMapper.selectSupport(afterMap);
        //修改程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
        if (CollectionUtils.isEmpty(selectMap)) {
            log.error("selectMap.isEmpty() == true!");
            return ;
        } else if (judgeTime.JudgeTime(selectMap)) {
            return ;
        }

        try {
            String table_name = afterMap.get("table_name");
            selectMap.put("table_name",table_name);
            dwsMapper.insertSupport(selectMap);
        } catch (Exception e) {
            log.error("更新后时间维度字段为null，sql异常");
        }
    }

    @Override
    public void insertVisit(Map<String, String> map) throws Exception {
        dwsMapper.insertVisit(map);
    }

    @Override
    public void updateVisit(Map<String, Map<String, String>> map) throws Exception {
        Map<String, String> beforeMap = map.get("beforeMap");
        Map<String, String> afterMap = map.get("afterMap");

        //记录修改前时间维度
        if (judgeTime.JudgeTime(beforeMap)) {
            //判断更新后核算月是否是近3个月,是则跳过
            return;
        }
        dwsMapper.insertVisit(beforeMap);

        //根据guid查修改后的数据
        Map<String, String> selectMap = dwsMapper.selectVisit(afterMap);
        //修改程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
        if (CollectionUtils.isEmpty(selectMap)) {
            log.error("selectMap.isEmpty() == true!");
            return ;
        } else if (judgeTime.JudgeTime(selectMap)) {
            return ;
        }

        try {
            String table_name = afterMap.get("table_name");
            selectMap.put("table_name",table_name);
            dwsMapper.insertVisit(selectMap);
        } catch (Exception e) {
            log.error("更新后时间维度字段为null，sql异常");
        }
    }


//    @Override
//    public void insertOne(Map<String, String> map) throws Exception{
//        dwsMapper.insertOne(map);
//    }
//
//    @Override
//    public void insertOrder(Map<String, String> map) throws Exception{
//        dwsMapper.insertOrder(map);
//    }
//
//    @Override
//    public void insertOneVisit(Map<String, String> map) throws Exception{
//        dwsMapper.insertOneVisit(map);
//    }
//
//    @Override
//    public Map<String,String> selectOne(Map<String, String> map) throws Exception{
//        Map<String, String> map1 = dwsMapper.selectOne(map);
//        return map1;
//    }
}
