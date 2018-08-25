package com.sinotech.settle.config;


import com.sinotech.settle.utils.FileOperation;

import java.util.Properties;

public class ConfigConsts {

	public static Properties properties= FileOperation.readConfigProperties("conf.properties");

    //Redis访问密码
	public static final String REDIS_AUTH = properties.getProperty("redis.auth");//"";
	public static final String REDIS_SENTINEL = properties.getProperty("redis.sentinel");//"";

	public static final int PRIMARY_KEY_LENGTH = Integer.valueOf(properties.getProperty("primary.key.length"));//9;

	//fastDFS 文件服务器地址
	public static final String FASTDFS_NGINX_SERVER = properties.getProperty("fastdfs.nginx.server");
	public static final String FASTDFS_DEFAULT_GROUP = properties.getProperty("fastdfs.default.group");

}
