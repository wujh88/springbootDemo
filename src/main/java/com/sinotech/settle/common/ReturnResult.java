package com.sinotech.settle.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;

/**
 * 
 * 项目名称：sinoTechOMS   
 * 类名称：ReturnResult
 * 类描述：   通用的返回结果对象
 * 创建人：liuhe   
 * 创建时间：2017年10月13日
 * 修改人：liuhe   
 * 修改时间：2017年10月13日 
 * 修改备注：   
 * @version
 */
public class ReturnResult {
	
	public static final String FAILED_CODE = "500"; //失败code
	public static final String SUCCESS_CODE = "200";  //成功code
	
	public static final String SUCCESS_MSG = "调用成功";  //成功 msg

	private String msg;//返回信息
	private String code;//结果代码
	
	@JsonInclude(Include.NON_NULL)//值为空时不包含在返回值中
	private Integer pageNumber;//当前页
	@JsonInclude(Include.NON_NULL)
	private Long total;//总记录数
	//数据
	private Object rows = new ArrayList();
	
	/******** 构造函数 ********/
	
	private ReturnResult(){
		
	}
	private ReturnResult(String msg, String code){
		this.msg = msg;
		this.code = code;
	}
	
	private ReturnResult(String msg, String code, Object data){
		this.msg = msg;
		this.code = code;
		this.rows = data;
	}
	
	private ReturnResult(String msg, String code, int page, long total, Object data){
		this.msg = msg;
		this.code = code;
		this.pageNumber = page;
		this.total = total;
		this.rows = data;
	}
	
	/******** 静态方法 ********/

	/**
	 * 调用成功后返回结果
	 * @return
	 */
	public static ReturnResult success(){
		return new ReturnResult(SUCCESS_MSG,SUCCESS_CODE);
	}
	/**
	 * 调用成功后返回结果(自定义提示信息)
	 * @return
	 */
	public static ReturnResult success(String msg){
		return new ReturnResult(msg,SUCCESS_CODE);
	}
	/**
	 * 返回不带分页信息的结果
	 * @param data
	 * @return
	 */
	public static ReturnResult success(String msg, Object data){
		return new ReturnResult(msg,SUCCESS_CODE,data);
	}

	/**
	 * 返回带分页信息的结果
	 * @param pageinfo	分页信息
	 * @return
	 */
	public static ReturnResult pageSuccess(PageInfo<?> pageinfo){
		
		return new ReturnResult(SUCCESS_MSG,
				SUCCESS_CODE,
				pageinfo.getPageNum(),
				pageinfo.getTotal(),
				pageinfo.getList());
		
	}
	/**
	 * 返回带分页信息的结果
	 * @param pageinfo	分页信息
	 * @return
	 */
	public static ReturnResult pageSuccess(PageInfo<?> pageinfo, String msg){

		return new ReturnResult(msg,
				SUCCESS_CODE,
				pageinfo.getPageNum(),
				pageinfo.getTotal(),
				pageinfo.getList());

	}

	/**
	 * 调用成功后返回结果
	 * @param msg	错误提示信息
	 * @return
	 */
	public static ReturnResult fail(String msg, String code){
		return new ReturnResult(msg,code);
	}
	/******** geter&seter方法 ********/
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public static String getFailedCode() {
		return FAILED_CODE;
	}
	public static String getSuccessCode() {
		return SUCCESS_CODE;
	}
	public static String getSuccessMsg() {
		return SUCCESS_MSG;
	}
	
	
	
	
	
}
