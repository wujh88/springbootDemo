package com.sinotech.settle.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class JsonUtil {
	
	/**
     * 将xml字符串<STRONG>转换</STRONG>为map字符串
     * 
     * @param xml String
     *            xml字符串
     * @return map<STRONG>对象</STRONG>
     */
	public static HashMap<String, Object> xml2Map(String xml) throws JDOMException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringReader reader = new StringReader(xml);
		SAXBuilder builder = new SAXBuilder();
		Document build = builder.build(reader);
		Element root = build.getRootElement();
		List<Element> elements = root.getChildren();
		for (Element e : elements) {
			List<Element> cs = e.getChildren();
			if (cs.isEmpty()) {
				map.put(e.getName(), e.getText());
			} else {
				map.put(e.getName(), ele2Map(e));
			}
		}
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map ele2Map(Element e) {
		Map map = new HashMap();
		List list = e.getChildren();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.getChildren().size() > 0) {
					Map m = ele2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else
			map.put(e.getName(), e.getText());
		return map;
	}
	
	/**
	 * 添加json对象为新的json对象
	 * @param key 新对象的键名
	 * @param jsonObject 新对象
	 * @param jsonObject2  原对象
	 * @return
	 */
	public static JSONObject addJsonObject(String key,Object jsonObject,Object jsonObject2) {
		JSONObject rsJsonObject = new JSONObject(); 
		Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String gsont = gson.toJson(jsonObject);
	//	System.out.println("gsont: "+gsont);
		rsJsonObject.put(key, gsont);
		rsJsonObject.putAll(toHashMap(jsonObject2));
	//	System.out.println(rsJsonObject);
		return rsJsonObject;
	}
	
	/**
	 * 
	 * @param key
	 * @param jsonObject
	 * @param jsonObject2
	 * @return
	 */
	public static JSONObject add2JsonObject(String key,Object jsonObject,Object jsonObject2) {
		JSONObject rsJsonObject = new JSONObject(); 
		Gson gson = new Gson();
		String gsont = gson.toJson(jsonObject).replace("\"", "");
	//	System.out.println("gsont: "+gsont);
		rsJsonObject.put(key, gsont);
		rsJsonObject.putAll(toHashMap(jsonObject2));
	//	System.out.println(rsJsonObject);
		return rsJsonObject;
	}
	
	/**
	 * 将jsonobject作为一个元素对应主键key得到新的jsonobject
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	public static JSONObject add1JsonObject(String key,Object jsonObject) {
		JSONObject rsJsonObject = new JSONObject(); 
		Gson gson = new Gson();
		String gsont = gson.toJson(jsonObject);
	//	System.out.println("gsont: "+gsont);
		rsJsonObject.put(key, gsont);
	//	System.out.println(rsJsonObject);
		return rsJsonObject;
	}
	
	/***
     * 将List对象序列化为JSON文本
     */
    public static <T> String toJSONString(List<T> list)
    {
        JSONArray jsonArray = JSONArray.fromObject(list);

        return jsonArray.toString();
    }
    
    /***
     * 将对象序列化为JSONARRAY文本
     * @param object
     * @return
     */
    public static String toJSONArrayString(Object object)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return jsonArray.toString();
    }

    /**
     * 将对象序列化为json格式的字符串
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}
    
    /***
     * 将JSON对象数组序列化为JSON文本
     * @param jsonArray
     * @return
     */
    public static String toJSONString(JSONArray jsonArray)
    {
        return jsonArray.toString();
    }

    /***
     * 将JSON对象序列化为JSON文本
     * @param jsonObject
     * @return
     */
    public static String toJSONString(JSONObject jsonObject)
    {
        return jsonObject.toString();
    }
    
    /***
     * 将对象转换为List对象
     * @param object
     * @return
     */
    public static List toArrayList(Object object)
    {
        List arrayList = new ArrayList();

        JSONArray jsonArray = JSONArray.fromObject(object);

        Iterator it = jsonArray.iterator();
        while (it.hasNext())
        {
            JSONObject jsonObject = (JSONObject) it.next();

            Iterator keys = jsonObject.keys();
            while (keys.hasNext())
            {
                Object key = keys.next();
                Object value = jsonObject.get(key);
                arrayList.add(value);
            }
        }

        return arrayList;
    }

    /***
     * 将对象转换为Collection对象
     * @param object
     * @return
     */
    public static Collection toCollection(Object object)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return JSONArray.toCollection(jsonArray);
    }

    /***
     * 将对象转换为JSON对象数组
     * @param object
     * @return
     */
    public static JSONArray toJSONArray(Object object)
    {
        return JSONArray.fromObject(object);
    }

    /***
     * 将对象转换为JSON对象
     * @param object
     * @return
     */
    public static JSONObject toJSONObject(Object object)
    {
        return JSONObject.fromObject(object);
    }

    /***
     * 将对象转换为HashMap
     * @param object
     * @return
     */
    public static HashMap toHashMap(Object object)
    {
        HashMap<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JsonUtil.toJSONObject(object);
        Iterator it = jsonObject.keys();
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    /***
     * 将对象转换为List>
     * @param object
     * @return
     */
    // 返回非实体类型(Map)的List
    public static List<Map<String, Object>> toList(Object object)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONArray jsonArray = JSONArray.fromObject(object);
        for (Object obj : jsonArray)
        {
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<String, Object>();
            Iterator it = jsonObject.keys();
            while (it.hasNext())
            {
                String key = (String) it.next();
                Object value = jsonObject.get(key);
                map.put((String) key, value);
            }
            list.add(map);
        }
        return list;
    }

    /***
     * 将JSON对象数组转换为传入类型的List
     * @param
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass)
    {
        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * 将对象转换为传入类型的List
     * @param
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(Object object, Class<T> objectClass)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * 将JSON对象转换为传入类型的对象
     * @param
     * @param jsonObject
     * @param beanClass
     * @return
     */
    public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass)
    {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * 将将对象转换为传入类型的对象
     * @param
     * @param object
     * @param beanClass
     * @return
     */
    public static <T> T toBean(Object object, Class<T> beanClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(object);

        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * 将JSON文本反序列化为主从关系的实体
     * 泛型T 代表主实体类型
     * 泛型D 代表从实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailName 从实体类在主实体类中的属性名称
     * @param detailClass 从实体类型
     * @return
     */
    public static <T, D> T toBean(String jsonString, Class<T> mainClass,
            String detailName, Class<D> detailClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);

        T mainEntity = JsonUtil.toBean(jsonObject, mainClass);
        List<D> detailList = JsonUtil.toList(jsonArray, detailClass);

        try
        {
            BeanUtils.setProperty(mainEntity, detailName, detailList);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }

    /***
     * 将JSON文本反序列化为主从关系的实体
     * 泛型T 代表主实体类型
     * 泛型D1 代表从实体类型
     * 泛型D2 代表从实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailName1 从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2 从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @return
     */
    public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass,
            String detailName1, Class<D1> detailClass1, String detailName2,
            Class<D2> detailClass2)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);

        T mainEntity = JsonUtil.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JsonUtil.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JsonUtil.toList(jsonArray2, detailClass2);

        try
        {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }
    
    /***
     * 将JSON文本反序列化为主从关系的实体
     * 泛型T 代表主实体类型
     * 泛型D1 代表从实体类型
     * 泛型D2 代表从实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailName1 从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2 从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @param detailName3 从实体类在主实体类中的属性
     * @param detailClass3 从实体类型
     * @return
     */
    public static <T, D1, D2, D3> T toBean(String jsonString,
            Class<T> mainClass, String detailName1, Class<D1> detailClass1,
            String detailName2, Class<D2> detailClass2, String detailName3,
            Class<D3> detailClass3)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
        JSONArray jsonArray3 = (JSONArray) jsonObject.get(detailName3);

        T mainEntity = JsonUtil.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JsonUtil.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JsonUtil.toList(jsonArray2, detailClass2);
        List<D3> detailList3 = JsonUtil.toList(jsonArray3, detailClass3);

        try
        {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
            BeanUtils.setProperty(mainEntity, detailName3, detailList3);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }

    /***
     * 将JSON文本反序列化为主从关系的实体
     * 泛型T 代表主实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailClass 存放了多个从实体在主实体中属性名称和类型
     * @return
     */
    public static <T> T toBean(String jsonString, Class<T> mainClass,
            HashMap<String, Class> detailClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T mainEntity = JsonUtil.toBean(jsonObject, mainClass);
        for (Object key : detailClass.keySet())
        {
            try
            {
                Class value = (Class) detailClass.get(key);
                BeanUtils.setProperty(mainEntity, key.toString(), value);
            }
            catch (Exception ex)
            {
                throw new RuntimeException("主从关系JSON反序列化实体失败！");
            }
        }
        return mainEntity;
    }
    
    public static void main(String[] args) {
		String se = "{\"status\":0,\"msg\":\"\",\"data\":[{\"eid\":\"723694498417192094\",\"elabel\":\"\\u8bfa\\u57fa\\u4e9a 1050\",\"index\":\"12844\"},{\"eid\":\"575517057678401414\",\"elabel\":\"\\u534e\\u4e3a Y511\",\"index\":\"2198\"}]}";
		//toArrayList(se);
		JSONObject job = toJSONObject(se);
		JSONArray rso = toJSONArray(job.get("data"));
		System.out.println(rso);
		
//		String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Response><statusCode>000000</statusCode><TemplateSMS><smsMessageSid>a786eed4770549f7bf8a346b6bf596b4</smsMessageSid><dateCreated>20170809161756</dateCreated></TemplateSMS></Response>";
		String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Request><action>SMSArrived</action><smsType>1</smsType><recvTime>20130923010101</recvTime><apiVersion>2013-12-26</apiVersion><fromNum>13912345678</fromNum><appendCode>12345</appendCode><content>smsMessageSid</content><status>0</status><dateSent>20130923010000</dateSent></Request>";
		try {
			JSONObject jsonObject = JsonUtil.toJSONObject(JsonUtil.xml2Map(xml));
			System.out.println(xml);
			System.out.println(jsonObject);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
