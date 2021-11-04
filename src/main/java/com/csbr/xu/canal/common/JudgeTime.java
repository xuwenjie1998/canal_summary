package com.csbr.xu.canal.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @program: canal_summary
 * @description: 根据核算月判断，近3个月以内为ture，以外为false
 * @author: xwj
 * @create: 2021-11-03 14:36
 **/
@Slf4j
@Component
public class JudgeTime {
    //判断核算月
    public Boolean JudgeTime(Map<String, String> map) {
        Date dNow = new Date(); //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(calendar.MONTH, -3); //设置为前3月
        dBefore = calendar.getTime(); //得到前3月的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); //设置时间格式
        String beforeThreeMonth = sdf.format(dBefore); //格式化前3月的时间
        int beforeThreeMonth1 = Integer.parseInt(beforeThreeMonth);
        log.info(String.valueOf(beforeThreeMonth1));

        if (!map.isEmpty() && StringUtils.isNotBlank(map.get("account_month")) ) {
            String account_month = map.get("account_month");
            int acc_month = Integer.parseInt(account_month);
            //包含当前月及前两月的不计入
            if (acc_month > beforeThreeMonth1) {
                log.info(acc_month + " > " + beforeThreeMonth1 + " true, break!");
                return true;
            } else {
                log.info(acc_month + " > " + beforeThreeMonth1 + " false, continue");
                return false;
            }
        } else {
            log.error("日期格式不合法！, break!");
            return true;
        }
    }
}