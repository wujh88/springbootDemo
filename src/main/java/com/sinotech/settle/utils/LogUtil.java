package com.sinotech.settle.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * 日志处理封装工具类
 * @author Haakon 2017-05-09
 *
 */
public class LogUtil {

	/**
	 * 封装输出日志中的类名和时间戳
	 * @param clazz
	 * @return
	 */
	public static String logStr(Class<?>  clazz) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("##").append(clazz).append("##").append(DateUtil.getDateTimeMilFormat(new Date()));
		return sBuffer.toString();
	}

	/**
	 * 整理日志
	 * @param t
	 * @return
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}


	/**
	 *	格式化日志内容
	 *
	 *  OMS##订单新增接口addOrder()##传入参数param=····
	 * 系统##接口名#日志内容##日志生成时间
	 *
	 * @param moduleAndMethod  模块和方法名
	 * @param msgAndParams	消息和请求参数
	 * @return
	 */
	public static String format(String moduleAndMethod,String msgAndParams){

		StringBuffer stringBuffer = new StringBuffer("OA##");

		stringBuffer.append(moduleAndMethod)
				.append("##")
				.append(msgAndParams)
				.append("##")
				.append(DateUtil.getDateTimeMilFormat(new Date()));
		return stringBuffer.toString();
	}

	/**
	 *	格式化日志内容
	 *
	 * 系统##日志内容##日志生成时间
	 *
	 * @param msg  日志信息
	 * @param
	 * @return
	 */
	public static String format(String msg){

		StringBuffer stringBuffer = new StringBuffer("OA##");

		stringBuffer.append(msg)
				.append("##")
				.append(DateUtil.getDateTimeMilFormat(new Date()));
		return stringBuffer.toString();
	}
}
