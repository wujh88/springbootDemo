package com.sinotech.settle.config;

/**
 * 系统错误码常量
 * 
 * @author ZTF
 * @date 2017年5月9日 下午3:26:50
 */
public class FailCodeConstants {
	
	public static final String RESULT_CODE_SUCCESS = "success";//成功
	public static final String RESULT_CODE_FAIL = "error";   //失败
	
	public static final String RESPONSE_SUCCESS = "success";//回调接口通知成功后返回给渠道的报文信息
	public static final String RESPONSE_FAIL = "error";//回调接口通知失败后返回给渠道的报文信息
	
	/**请求渠道时发生网络异常*/
	public  static final String SYS_FAIL_CODE_EEEE = "CC_EEEEE";
	/**手机号码格式有误*/
	public  static final String SYS_FAIL_CODE_160042 = "160042";
	
	/**未知错误*/
	public  static final String SYS_FAIL_CODE_EEEE1 = "CC_EEEE1";
	
	/**参数缺失*/
	public static final String SYS_FAIL_CODE_E0030 =  "CB_E0030";
	
	/**请求参数转化异常*/
	public static final String SYS_FAIL_CODE_E0001 =  "CC_E0001";
	
	/**返回结果格式异常*/
	public static final String SYS_FAIL_CODE_E0002= "CC_E0002";
	/**该商户不可用！*/
	public static final String SYS_FAIL_CODE_MC00001 = "CC_MC00001";	
	/**该渠道不可用！！*/
	public static final String SYS_FAIL_CODE_MC00002 = "CC_MC00002";	
	/**当前商户没有开通{0}渠道功能！！*/
	public static final String SYS_FAIL_CODE_MC00003 = "CC_MC00003";	
	
	/**系统异常，请求参数保存失败，请联系管理员！！*/
	public static final String SYS_FAIL_CODE_MC00004 = "CC_MC00004";
	/**渠道功能暂未开通！*/
	public static final String SYS_FAIL_CODE_MC00005 = "CC_MC00005";
	/**当前商户没有设置默认渠道！*/
	public static final String SYS_FAIL_CODE_MC00008 = "CC_MC00008";	
	/**金额有无*/
	public static final String SYS_FAIL_CODE_E0032 = "CB_E0032";
	/**所需参数值无效*/
	public static final String SYS_FAIL_CODE_MC00006 = "CC_MC00006";
	/**短信记录不存在*/
	public static final String SYS_FAIL_CODE_E0003 = "CB_E0003";
	/**短信记录已存在，不可重复发送*/
	public static final String SYS_FAIL_CODE_MC00007 = "CC_MC00007";
	/**业务请求超时*/
	public static final String SYS_FAIL_CODE_E0027 = "CB_E0027";
	/**时间戳格式有误*/
	public static final String SYS_FAIL_CODE_E0029 = "CB_E0029";

	public static final String PAY_ERROR_NOTIFY_ADDRESS = "https://mywallettest.tf56.com/payment/cashBillSite/view/payError.html?";
}
