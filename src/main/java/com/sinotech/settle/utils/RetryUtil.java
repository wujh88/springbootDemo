package com.sinotech.settle.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 重试工具类
 * @author wujh
 * @date 2019/5/8 22:37
 */
public class RetryUtil {
    /**
     * 比较两个时间差是否满足重试条件
     *
     * <pre>
     *     公式为：当前时间 - 创建时间 >= 2的n次幂 * 间隔秒因子
     *     <li>当前时间从ThreadLocal中获取传入</li>
     *     <li>n次幂由重试次数决定</li>
     *     <li>间隔秒因子由系统配置，如30s、60s等</li>
     * </pre>
     *
     * @param currentTime       当前时间
     * @param gmtProcess        推进时间(发起网关提现或充值的时间)
     * @param retryCount        重试次数
     * @param intervalSec       间隔秒因子
     * @return
     */
    public static boolean ifRetry(Date currentTime, Date gmtProcess, int retryCount, int intervalSec) {
        //【2】获取时间差
        long offsetSec = getDiffSeconds(currentTime, gmtProcess);

        //【3】得到2的n次幂
        long power = new Double(Math.pow(2, retryCount)).longValue();

        //【4】返回是否满足重试条件
        return offsetSec >= power * intervalSec;
    }
    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one
     *            日期1
     * @param two
     *            日期2
     *
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();


        sysDate.setTime(one);


        Calendar failDate = new GregorianCalendar();


        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }
}
