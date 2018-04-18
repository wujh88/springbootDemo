package com.sinotech.settle.config;


import com.sinotech.settle.utils.FileOperation;

import java.util.Properties;

public class ConfigConsts {

	public static Properties properties= FileOperation.readConfigProperties("conf.properties");
//	public static Properties rabbit=FileOperation.readConfigProperties("rabbitmq.properties");
	
	//Redis服务地址
	//public static final String REDIS_ADDR = properties.getProperty("redis.host");
	//Redis的端口号
	//public static final int REDIS_PORT = Integer.valueOf(properties.getProperty("redis.port"));//6379;
    //Redis访问密码
	public static final String REDIS_AUTH = properties.getProperty("redis.auth");//"";
	public static final String REDIS_SENTINEL = properties.getProperty("redis.sentinel");//"";
    //Redis可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	public static final int REDIS_MAX_ACTIVE = Integer.valueOf(properties.getProperty("redis.max_active"));//1024;
    //Redis控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	public static final int REDIS_MAX_IDLE = Integer.valueOf(properties.getProperty("redis.max_idle"));//200;
    //Redis等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	public static final int REDIS_MAX_WAIT = Integer.valueOf(properties.getProperty("redis.max_wait"));//10000;
	public static final int REDIS_TIMEOUT = Integer.valueOf(properties.getProperty("redis.timeout"));//10000;
	public static final int REDIS_PC_TIMEOUT = Integer.valueOf(properties.getProperty("redis.pc.timeout"));//3600;
	public static final int REDIS_MOBILE_TIMEOUT = Integer.valueOf(properties.getProperty("redis.mobile.timeout"));//1296000;
	public static final int PRIMARY_KEY_LENGTH = Integer.valueOf(properties.getProperty("primary.key.length"));//9;
    //Redis在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	public static final boolean REDIS_TEST_ON_BORROW = Boolean.valueOf(properties.getProperty("redis.test_on_borrow"));//true;
	//短信平台URL
	public static final String MESSAGE_URL=properties.getProperty("message.url");
	//短信平台商户号
	public static final String MESSAGE_MERCHANT_ID=properties.getProperty("message.merchant_id");
	//短信平台模板ID
	public static final String MESSAGE_TEMPLATE_ID=properties.getProperty("message.template_id");
	//理赔图片路径
	public static final String PICTURE_ORDERCLAIM_URL=properties.getProperty("picture.orderclaim.url");
	//rabbitmq审核结果通知客户的消息队列
//	public static final String RABBITMQ_CUSTOMER_AUTH = rabbit.getProperty("spring.rabbitmq.customer_auth");
	//同时启动多个Listener实例来消费消息
//	public static final int RABBITMQ_CUSTOMER_AMOUNT = Integer.valueOf(rabbit.getProperty("spring.rabbitmq.customer_amount"));
	//均匀分配给多个接收着
//	public static final int RABBITMQ_PREFETCH_COUNT = Integer.valueOf(rabbit.getProperty("spring.rabbitmq.prefetch_count"));
	//PC端消息推送是否开启
	public static final String WEBSOCKET_CONNECT = properties.getProperty("websocket.connect");//"false";
	
	/** 系统超时时间【毫秒】 ，当前设为10分钟*/
	public final static long SYSTEM_TIMEOUT = 180000L;
	//http请求
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;
	public static final int HTTP_POST_GOTYE = 2;
	public static final int HTTP_PUT = 4;
	public static final int HTTP_DELETE = 5;


	//session中当前用户信息key
	public static final String USER_INFO = "curr_user";

	//菜单缓存的名字
	public static final String OMS_MENU_CACHE = "OA-menu-cache";


	public static final String SHIRO_AUTHORIZATION_CACHE_NAME = "OA";
	//fastDFS 文件服务器地址
	public static final String FASTDFS_NGINX_SERVER = properties.getProperty("fastdfs.nginx.server");
	public static final String FASTDFS_DEFAULT_GROUP = properties.getProperty("fastdfs.default.group");
	// 消息推送
	public static final String JPUSH_SERVER_URL = properties.getProperty("jpush.server.url");
	public static final String APP_KEY = properties.getProperty("app.key");
	public static final String MASTER_SECRET = properties.getProperty("master.secret");

	public static final String ATTEND_DEVICE_SERVER = properties.getProperty("attend.device.server");
	public static final String ATTEND_DEVICE_REGISTER = properties.getProperty("attend.device.register");
	public static final String ATTEND_DEVICE_REFRESH = properties.getProperty("attend.device.refresh");
	public static final String ATTEND_DEVICE_RESTART = properties.getProperty("attend.device.restart");
	public static final String ATTEND_DEVICE_REMOVE = properties.getProperty("attend.device.remove");
	public static final String ATTEND_DEVICE_GETRECORD = properties.getProperty("attend.device.getRecord");

	public static final Long COMPANY_DISK_SIZE = Long.valueOf(properties.getProperty("company.disk.size"));
	public static final Long DEPARTMENT_DISK_SIZE = Long.valueOf(properties.getProperty("department.disk.size"));
	public static final Long PERSONAL_DISK_SIZE = Long.valueOf(properties.getProperty("personal.disk.size"));

	// 逾期审批处罚罚金
	public static final int OVERDUE_PENALTY_MONEY = Integer.valueOf(properties.getProperty("overdue.penalty.money"));
}
