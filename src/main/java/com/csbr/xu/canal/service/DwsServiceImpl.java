package com.csbr.xu.canal.service;

import com.csbr.xu.canal.common.JudgeTime;
import com.csbr.xu.canal.mapper.DwsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 增删改的业务处理
 * @author: xwj
 * @create: 2021-11-08 11:09
 **/
@Service
@Slf4j
@Transactional
public class DwsServiceImpl implements DwsService {

    @Autowired
    private DwsMapper dwsMapper;

    @Autowired
    private JudgeTime judgeTime;

    @Override
    public void insertOrder(List<Map<String, String>> orderList) throws Exception {
        dwsMapper.insertOrder(orderList);
    }

    @Override
    public void updateOrder(List<Map<String, Map<String, String>>> maps) throws Exception {
        for (Map<String, Map<String, String>> map : maps) {
            Map<String, String> beforeMap = map.get("beforeMap");
            Map<String, String> afterMap = map.get("afterMap");

            //更新前核算月是否是近3个月,是则跳过
            if (judgeTime.JudgeTime(beforeMap)) {
                //记录修改前时间维度
                dwsMapper.insertOrderMap(beforeMap);
            }

            //根据guid查更新后数据
            Map<String, String> selectMap = dwsMapper.selectOrder(afterMap);
            //程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
            if (CollectionUtils.isEmpty(selectMap)) {
                log.error("查不到修改后数据,已删除！");
                return;
            } else if (judgeTime.JudgeTime(selectMap)) {
                try {
                    String table_name = afterMap.get("table_name");
                    selectMap.put("table_name", table_name);
                    dwsMapper.insertOrderMap(selectMap);
                } catch (Exception e) {
                    log.error("更新后时间维度字段为null，sql异常");
                }
            }
        }
    }

    @Override
    public void insertPreOrder(List<Map<String, String>> preOrderList) throws Exception {
        dwsMapper.insertPreOrder(preOrderList);
    }

    @Override
    public void updatePreOrder(List<Map<String, Map<String, String>>> maps) throws Exception {
        for (Map<String, Map<String, String>> map : maps) {
            Map<String, String> beforeMap = map.get("beforeMap");
            Map<String, String> afterMap = map.get("afterMap");

            //更新前核算月是否是近3个月,是则跳过
            if (judgeTime.JudgeTime(beforeMap)) {
                //记录修改前时间维度
                dwsMapper.insertPreOrderMap(beforeMap);
            }

            //根据guid查更新后数据
            Map<String, String> selectMap = dwsMapper.selectPreOrder(afterMap);
            //程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
            if (CollectionUtils.isEmpty(selectMap)) {
                log.error("查不到修改后数据,已删除！");
                return;
            } else if (judgeTime.JudgeTime(selectMap)) {
                try {
                    String table_name = afterMap.get("table_name");
                    selectMap.put("table_name", table_name);
                    dwsMapper.insertPreOrderMap(selectMap);
                } catch (Exception e) {
                    log.error("更新后时间维度字段为null，sql异常");
                }
            }
        }
    }

    @Override
    public void insertFlow(List<Map<String, String>> mapList) throws Exception {
        dwsMapper.insertFlow(mapList);
    }

    @Override
    public void updateFlow(List<Map<String, Map<String, String>>> maps) throws Exception {
        for (Map<String, Map<String, String>> map : maps) {
            Map<String, String> beforeMap = map.get("beforeMap");
            Map<String, String> afterMap = map.get("afterMap");

            //更新前核算月是否是近3个月,是则跳过
            if (judgeTime.JudgeTime(beforeMap)) {
                //记录修改前时间维度
                dwsMapper.insertFlowMap(beforeMap);
            }

            //根据guid查更新后数据
            Map<String, String> selectMap = dwsMapper.selectFlow(afterMap);
            //程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
            if (CollectionUtils.isEmpty(selectMap)) {
                log.error("查不到修改后数据,已删除！");
                return;
            } else if (judgeTime.JudgeTime(selectMap)) {
                try {
                    String table_name = afterMap.get("table_name");
                    selectMap.put("table_name", table_name);
                    dwsMapper.insertFlowMap(selectMap);
                } catch (Exception e) {
                    log.error("更新后时间维度字段为null，sql异常");
                }
            }
        }
    }

    @Override
    public void insertClock(List<Map<String, String>> mapList) throws Exception {
        dwsMapper.insertClock(mapList);
    }

    @Override
    public void updateClock(List<Map<String, Map<String, String>>> maps) throws Exception {
        for (Map<String, Map<String, String>> map : maps) {
            Map<String, String> beforeMap = map.get("beforeMap");
            Map<String, String> afterMap = map.get("afterMap");

            //更新前核算月是否是近3个月,是则跳过
            if (judgeTime.JudgeTime(beforeMap)) {
                //记录修改前时间维度
                dwsMapper.insertClockMap(beforeMap);
            }

            //根据guid查更新后数据
            Map<String, String> selectMap = dwsMapper.selectClock(afterMap);
            //程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
            if (CollectionUtils.isEmpty(selectMap)) {
                log.error("查不到修改后数据,已删除！");
                return;
            } else if (judgeTime.JudgeTime(selectMap)) {
                try {
                    String table_name = afterMap.get("table_name");
                    selectMap.put("table_name", table_name);
                    dwsMapper.insertClockMap(selectMap);
                } catch (Exception e) {
                    log.error("更新后时间维度字段为null，sql异常");
                }
            }
        }
    }

    @Override
    public void insertSupport(List<Map<String, String>> mapList) throws Exception {
        dwsMapper.insertSupport(mapList);
    }

    @Override
    public void updateSupport(List<Map<String, Map<String, String>>> maps) throws Exception {
        for (Map<String, Map<String, String>> map : maps) {
            Map<String, String> beforeMap = map.get("beforeMap");
            Map<String, String> afterMap = map.get("afterMap");

            //更新前核算月是否是近3个月,是则跳过
            if (judgeTime.JudgeTime(beforeMap)) {
                //记录修改前时间维度
                dwsMapper.insertSupportMap(beforeMap);
            }

            //根据guid查更新后数据
            Map<String, String> selectMap = dwsMapper.selectSupport(afterMap);
            //程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
            if (CollectionUtils.isEmpty(selectMap)) {
                log.error("查不到修改后数据,已删除！");
                return;
            } else if (judgeTime.JudgeTime(selectMap)) {
                try {
                    String table_name = afterMap.get("table_name");
                    selectMap.put("table_name", table_name);
                    dwsMapper.insertSupportMap(selectMap);
                } catch (Exception e) {
                    log.error("更新后时间维度字段为null，sql异常");
                }
            }
        }
    }

    @Override
    public void insertVisit(List<Map<String, String>> mapList) throws Exception {
        dwsMapper.insertVisit(mapList);
    }

    @Override
    public void updateVisit(List<Map<String, Map<String, String>>> maps) throws Exception {
        for (Map<String, Map<String, String>> map : maps) {
            Map<String, String> beforeMap = map.get("beforeMap");
            Map<String, String> afterMap = map.get("afterMap");

            //更新前核算月是否是近3个月,是则跳过
            if (judgeTime.JudgeTime(beforeMap)) {
                //记录修改前时间维度
                dwsMapper.insertVisitMap(beforeMap);
            }

            //根据guid查更新后数据
            Map<String, String> selectMap = dwsMapper.selectVisit(afterMap);
            //程序未启动时，修改后删除数据会导致启动程序查不到guid而一直NPE
            if (CollectionUtils.isEmpty(selectMap)) {
                log.error("查不到修改后数据,已删除！");
                return;
            } else if (judgeTime.JudgeTime(selectMap)) {
                try {
                    String table_name = afterMap.get("table_name");
                    selectMap.put("table_name", table_name);
                    dwsMapper.insertVisitMap(selectMap);
                } catch (Exception e) {
                    log.error("更新后时间维度字段为null，sql异常");
                }
            }
        }
    }

}
