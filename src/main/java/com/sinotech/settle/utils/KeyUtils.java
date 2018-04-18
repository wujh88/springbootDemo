package com.sinotech.settle.utils;


import com.sinotech.settle.config.ConfigConsts;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @project：sinoTechOMS
 * @author：liuhe
 * @date：2017/11/18
 */
public class KeyUtils {



    //表名+部门编号+时间（年月日）+流水

    /**
     * 通用的流水生成器
     * @param tableName 表名
     * @param dept 部门信息
     * @param date 年月日（20101212）
     * @param length 流水长度
     * @return
     */
    public static String getKey(String tableName, String dept, Date date, int length){

        StringBuffer stringBuffer = new StringBuffer();
        String dateStr="";
        if (tableName != null){
            stringBuffer.append(tableName.toUpperCase());
        }

        if (dept != null){
            stringBuffer.append(dept);
        }

        if (date != null){

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            dateStr=simpleDateFormat.format(date).toString();
            stringBuffer.append(dateStr);
        }

        if (length == 0){
            length = 6;
        }

        int no;
        synchronized (KeyUtils.class){
            no=(int) RedisCacheUtils.incr(stringBuffer.toString(), 1);
        }

        //主键的长度使用配置项
        length = ConfigConsts.PRIMARY_KEY_LENGTH;
        return dateStr+StringUtils.leftPad(String.valueOf(no), length,"0");


    }
    
    /**
     * 流水生成器，用于生成一批流水号
     * @param tableName 表名
     * @param dept 部门信息
     * @param date 年月日（20101212）
     * @param length 流水长度
     * @param incr 递增量，即要生成流水号的数量
     * @return
     */
    public static long getKeyMax(String tableName, String dept, Date date, int length, int incr){

        StringBuffer stringBuffer = new StringBuffer();
        String dateStr="";
        if (tableName != null){
            stringBuffer.append(tableName.toUpperCase());
        }

        if (dept != null){
            stringBuffer.append(dept);
        }

        if (date != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            dateStr=simpleDateFormat.format(date).toString();
            stringBuffer.append(dateStr);
        }

        if (length == 0){
            length = 6;
        }

        int no;
        synchronized (KeyUtils.class) {
            no = (int) RedisCacheUtils.incr(stringBuffer.toString(), incr);
        }

        //主键的长度使用配置项
        length = ConfigConsts.PRIMARY_KEY_LENGTH;
        return Long.valueOf(dateStr+StringUtils.leftPad(String.valueOf(no), length,"0"));


    }

    /**
     * 生成流水号
     * @param key redis key
     * @param prefix 前缀
     * @param length 长度
     * @param padStr 补充字符
     * @return
     */
    public static String generateSerialNo(String key,String prefix,int length,String padStr){
        int no;
        synchronized (KeyUtils.class){
            no = (int) RedisCacheUtils.incr(key, 1);
        }
        //主键的长度使用配置项
        length = ConfigConsts.PRIMARY_KEY_LENGTH;
        if (prefix == null){
            return StringUtils.leftPad(String.valueOf(no), length,padStr);
        }else {
            return prefix+ StringUtils.leftPad(String.valueOf(no), length,padStr);
        }

    }

    /**
     * 生成流程code TODO zhouxu 暂时按流水自增
     * @return
     */
    public static String getProcessCode(){
        int no;
        synchronized (KeyUtils.class){
            no = (int) RedisCacheUtils.incr("processCode", 1);
        }
        return String.valueOf(no);
    }
}
