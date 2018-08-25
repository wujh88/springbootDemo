package com.sinotech.settle.config;

/**
 *
 */
public class ErrorCodes {

    /**登录信息有误*/
    public static final String LOGIN_FAILED_ERROR = "5000";
    /**权限不足*/
    public static final String UNAUTHORIZED_ERROR = "5001";
    /**参数为空*/
    public static final String NULL_PARAM_ERROR = "5003";
    /**没有登录*/
    public static final String UNAUTHENTICATED_ERROR = "5002";
    /**数据库异常*/
    public static final String DATABASE_ERROR = "5004";
    /**查询结果为空*/
    public static final String NULL_QUERY_ERROR = "5005";
    /**参数异常*/
    public static final String TYPE_PARAM_ERROR = "5006";
    /**添加数据失败*/
    public static final String ADD_DATA_ERROR = "5009";
    /**修改数据失败*/
    public static final String UPDATE_DATA_ERROR = "5010";
    /**删除数据失败*/
    public static final String DELETE_DATA_ERROR = "5011";


    /**订单转换运单失败*/
    public static final String CAN_NOT_CONVERT = "5014";
    /**添加运单失败*/
    public static final String ADD_ORDER_ERROR = "5015";
    /**运单分配承运商确认失败*/
    public static final String ALLOT_ORDER_ERROR = "5016";
    /**运单分配查询运单失败*/
    public static final String QUERY_DISTRIBUTION_ORDER_ERROR = "5017";
    /**运单分配确认查询运单失败*/
    public static final String QUERY_ALLOT_ORDER_ERROR = "5018";
    /**数字转换异常 */
    public static final String NUMBER_FORMAT_ERROR = "5019";
    /**sql语法错误 */
    public static final String SQL_SYNTAX_ERROR = "5020";
    /**值超过列最大值 */
    public static final String SQL_DATA_ERROR = "5021";
    /**未知异常 */
    public static final String COMMON_EXCEPTION_ERROR = "5022";
    /**数据库服务器网络异常 */
    public static final String DATABASE_NETWORK_ERROR = "5023";
    /**更新订单异常**/
    public static final String UPDATE_ORDER_ERROR = "5024";
    /**存储过程异常**/
    public static final String PROC_ERROR = "5025";
    /**自定义异常**/
    public static final String CUSTOM_ERROR = "5026";

    /**业务上不允许**/
    public static final String BUSSINESS_FORBIDDEN = "5027";
    /**流程引擎错误**/
    public static final String WORKFLOW_ERROR = "5028";
    /**文件格式错误*/
    public static final String FILE_FORMAT_ERROR = "5029";
    /**非法参数*/
    public static final String ILLEGAL_PARAMETERS_ERROR = "5030";
    /**文件服务器异常*/
    public static final String FASTDFS_ERROR = "5031";
    /**系统IO异常*/
    public static final String SYSTEM_IO_ERROR = "5032";
    /**json数据格式化异常*/
    public static final String JSON_FORMAT_ERROR = "5033";


}
