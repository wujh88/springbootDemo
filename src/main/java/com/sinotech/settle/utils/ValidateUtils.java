package com.sinotech.settle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 
 * @className:ValidateUtils
 * @Description: 数据校验类
 * @author zhouxu
 * @date 2017年10月24日下午4:07:11
 */
public class ValidateUtils {
	
	/**
	 * 判断字符串是否为date类型
	 * @param value 要判断的字符串
	 * @return
	 */
	public static boolean isDate(String value) {
		try {
			SimpleDateFormat format = null;

			if (value == null) {
				return false;
			}
			if (value.trim().length() == 0) {
				return false;
			}
			
			if (value.indexOf("-") > -1) {
				format = new  SimpleDateFormat("yyyy-MM-dd");
			}
			else if (value.indexOf("/") > -1) {
				format = new  SimpleDateFormat("yyyy/MM/dd");
			}
			else {
				format = new  SimpleDateFormat("yyyyMMdd");
			}
			Date date = format.parse(value);
			if (format.format(date).equals(value.trim())) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (ParseException e) {
			return false;
		}
	}
	/**
	 * 正则表达式验证
	 * @param value 要验证的值
	 * @param parttern 表达式
	 * @return
	 */
	public static boolean isMatcher(String value, String parttern) {
		return Pattern.matches(parttern, value);
	}
	/**
	 * 是否为整数
	 * @param value 待校验的值
	 * @return
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.valueOf(value);
		}
		catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	/**
	 * 判断值是否为固定位数整数
	 * @param value 待验证的值
	 * @param num 长度
	 * @return
	 */
	public static boolean isInteger(String value, Integer num) {
		/*
		 * try { Integer.valueOf(value); } catch (NumberFormatException e) {
		 * return false; }
		 */
		if (null == value) {
			return false;
		}
		return isMatcher(value, "[0-9]{1," + num + "}");

	}
	/**
	 * 验证值是否为浮点类型
	 * @param value 待验证的值
	 * @param precision 长度
	 * @param scale精度
	 * @return
	 */
	public static boolean isDouble(String value, Integer precision,
			Integer scale) {
		if (precision < scale) {
			return false;
		} else if (precision == scale) {
			return isMatcher(value, "0?.[0-9]{1," + scale + "}")
					|| isMatcher(value, "0");
		} else {
			return isMatcher(value, "[0-9]{0," + (precision - scale)
					+ "}.[0-9]{" + scale + "}")
					|| isMatcher(value, "[0-9]{0," + (precision - scale) + "}");
		}
	}
	/**
	 * 验证值是否为浮点类型
	 * @param value 待验证的值
	 * @return
	 */
	public static boolean isDouble(String value) {
		return isDouble(value,6,3);
	}
	/**
	 * 必填校验
	 * @param value 待校验的值
	 * @return
	 */
	public static boolean isRequired(String value) {
		return !((value == null) || "".equals(value.trim()));
	}
	/**
	 * 组合必填校验 都为空或都不为空时返回true
	 * @param basevalue 基础必填值
	 * @param values 其他值
	 * @return
	 */
	public static boolean isRequired(String basevalue, String... values) {
		for (String value : values) {
			if (isRequired(basevalue) ^ isRequired(value)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 长度校验（只写一个len判断value长度 两个值，判断value长度是否在这个区间）
	 * @param value
	 * @param len
	 * @return
	 */
	public static boolean verifyLength(String value, int... len) {
		if (null == value) {
			return false;
		}
		int strLen = value.trim().length();
		if (len.length == 1) {
			if (strLen > len[0]) {
				return false;
			} else {
				return true;
			}
		} else if (len.length == 2) {
			if (strLen >= len[0] && strLen <= len[1]) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
//	public static void main(String[] args) {
//		String s="100.1";
//		System.out.println(isDouble(s,3,1));
//	}
//	
}
