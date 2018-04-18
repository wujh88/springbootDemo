package com.sinotech.settle.utils;

import com.google.common.collect.Maps;
import com.sinotech.settle.exception.IllegalParametersException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* String工具类
* @author ZTF
* @date 2017年4月26日 上午10:21:13
**/
public class StrUtils {
	
	/**
	 * 判断多个字符串是否包含空值
	 * @param val 
	 * @param values 可变长度参数
	 * @return   有空值返回：true，无空值返回：false
	 * @author ZTF
	 * @date 2017年4月26日 上午10:59:00
	 */
	public static boolean isEmpty(String val,String... values) {
		if (isEmpty(val)) {
			return true;
		}
		if (values == null || values.length == 0) {
			return true;
		}
		for (String string : values) {
			 if (isEmpty(string)) {
		            return true;
		        }
		}
		
		return false;
	}
	
	public static boolean isEmpty(String value) {
        if (value == null || value.length() == 0 || "null".equals(value)) {
            return true;
        }

		return false;
	}
	
    public static boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        }
        
        return a.equals(b);
    }
    /**
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr.toUpperCase();
     }
    /**
     * 判断对象是否为空 或者空字符 或者null
     * @param object 要验证的对象
     * @return
     */
    public static boolean isNull(Object object) {
		if (object == null || object.equals("null") || object.equals("")) {
			return true;
		}
		return false;
	}
    /**
     * 根据map 取出key的值
     * @param valueMap 要取值MAP
     * @param key 取值map中的key
     * @return
     */
	public static String convertMapString(Map valueMap, String key) {
		if (valueMap == null || key == null)
			return null;
		if (valueMap.get(key) == null) {
			return null;
		}
		if (valueMap.get(key) instanceof String) {
			return (String) valueMap.get(key);
		}
		return valueMap.get(key).toString();
	}

	/**
	 * 提取存储过程中异常的信息
	 * @param e 存储过程中的异常
	 * @return
	 */
	public static String getProduceErrorMsg(Exception e) {
		String errorMsg = e.getCause().toString();

		String[] errorMsgArray = errorMsg.split("\n");
		String errorLine = "";
		for (int i = 0; i < errorMsgArray.length; i++) {
			if (errorMsgArray[i].contains("ORA-")){
				errorLine = errorMsgArray[i];
				break;
			}
		}
		if (errorMsgArray.length>1){
			return errorLine.substring(errorLine.lastIndexOf(":")+2);
		}else {
			return errorMsg;
		}
	}

	/**
	 * 下划线转驼峰法
	 * @param line 源字符串
	 * @param smallCamel 大小驼峰,是否为小驼峰
	 * @return 转换后的字符串
	 */
	public static String underline2Camel(String line,boolean smallCamel){
		if(line==null||"".equals(line)){
			return "";
		}
		StringBuffer sb=new StringBuffer();
		Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher=pattern.matcher(line);
		while(matcher.find()){
			String word=matcher.group();
			sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
			int index=word.lastIndexOf('_');
			if(index>0){
				sb.append(word.substring(1, index).toLowerCase());
			}else{
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}
	/**
	 * 驼峰法转下划线
	 * @param line 源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line){
		if(line==null||"".equals(line)){
			return "";
		}
		line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb=new StringBuffer();
		Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher=pattern.matcher(line);
		while(matcher.find()){
			String word=matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end()==line.length()?"":"_");
		}
		return sb.toString();
	}


	/**
	 * 转换map的key为驼峰命名
	 * @param map
	 * @param smallCamel
	 * @return
	 */
	public static  Map mapKeyUnderline2Camel(Map<String,String> map,boolean smallCamel){
		HashMap<String,Object> newMap = Maps.newHashMap();

		if (map == null){
			throw new IllegalParametersException("查询结果不存在");
		}

		map.entrySet().stream().forEach(entry -> {
			//下划线转小驼峰
			String newKey = StrUtils.underline2Camel(entry.getKey(), smallCamel);
			newMap.put(newKey, entry.getValue());
		});

		return newMap;
	}

	/**
	 * 批量转换map的key为驼峰命名
	 * @param list
	 * @param smallCamel
	 * @return
	 */
	public static List<Map<String,String>> mapKeyUnderline2Camel(List<Map<String,String>> list, boolean smallCamel){
		List<Map<String,String>> resultList = new ArrayList<>();

		if (list.size() > 0){
			list.stream().forEach( map -> resultList.add(mapKeyUnderline2Camel(map,smallCamel)));
		}
		return resultList;
	}


}
