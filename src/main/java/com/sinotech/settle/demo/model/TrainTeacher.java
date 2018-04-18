package com.sinotech.settle.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:TRAIN_TEACHER表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-12-28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainTeacher implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * null
     */
    private String id;

    /**
     * null
     */
    private String companyId;

    /**
     * 讲师编号
     */
    private String code;

    /**
     * 讲师名称
     */
    private String name;

    /**
     * 头像路径
     */
    private String headUrl;

    /**
     * 讲师简介
     */
    private String intruduce;

    /**
     * 是否启用，0未启用，1启用
     */
    private Short enabled;

    /**
     * null
     */
    private String createById;

    /**
     * null
     */
    private String createByName;

    /**
     * null
     */
    private Date createOn;

    /**
     * null
     */
    private String updateById;

    /**
     * null
     */
    private String updateByName;

    /**
     * null
     */
    private Date updateOn;

    /**
     * null
     * @return ID null
     */
    public String getId() {
        return id;
    }

    /**
     * null
     * @param id null
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * null
     * @return COMPANY_ID null
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * null
     * @param companyId null
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 讲师编号
     * @return CODE 讲师编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 讲师编号
     * @param code 讲师编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 讲师名称
     * @return NAME 讲师名称
     */
    public String getName() {
        return name;
    }

    /**
     * 讲师名称
     * @param name 讲师名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 头像路径
     * @return HEAD_URL 头像路径
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * 头像路径
     * @param headUrl 头像路径
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    /**
     * 讲师简介
     * @return INTRUDUCE 讲师简介
     */
    public String getIntruduce() {
        return intruduce;
    }

    /**
     * 讲师简介
     * @param intruduce 讲师简介
     */
    public void setIntruduce(String intruduce) {
        this.intruduce = intruduce == null ? null : intruduce.trim();
    }

    /**
     * 是否启用，0未启用，1启用
     * @return ENABLED 是否启用，0未启用，1启用
     */
    public Short getEnabled() {
        return enabled;
    }

    /**
     * 是否启用，0未启用，1启用
     * @param enabled 是否启用，0未启用，1启用
     */
    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    /**
     * null
     * @return CREATE_BY_ID null
     */
    public String getCreateById() {
        return createById;
    }

    /**
     * null
     * @param createById null
     */
    public void setCreateById(String createById) {
        this.createById = createById == null ? null : createById.trim();
    }

    /**
     * null
     * @return CREATE_BY_NAME null
     */
    public String getCreateByName() {
        return createByName;
    }

    /**
     * null
     * @param createByName null
     */
    public void setCreateByName(String createByName) {
        this.createByName = createByName == null ? null : createByName.trim();
    }

    /**
     * null
     * @return CREATE_ON null
     */
    public Date getCreateOn() {
        return createOn;
    }

    /**
     * null
     * @param createOn null
     */
    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    /**
     * null
     * @return UPDATE_BY_ID null
     */
    public String getUpdateById() {
        return updateById;
    }

    /**
     * null
     * @param updateById null
     */
    public void setUpdateById(String updateById) {
        this.updateById = updateById == null ? null : updateById.trim();
    }

    /**
     * null
     * @return UPDATE_BY_NAME null
     */
    public String getUpdateByName() {
        return updateByName;
    }

    /**
     * null
     * @param updateByName null
     */
    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName == null ? null : updateByName.trim();
    }

    /**
     * null
     * @return UPDATE_ON null
     */
    public Date getUpdateOn() {
        return updateOn;
    }

    /**
     * null
     * @param updateOn null
     */
    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

	@Override
	public String toString() {
		return "TrainTeacher [id=" + id + ", companyId=" + companyId + ", code=" + code + ", name=" + name
				+ ", headUrl=" + headUrl + ", intruduce=" + intruduce + ", enabled=" + enabled + ", createById="
				+ createById + ", createByName=" + createByName + ", createOn=" + createOn + ", updateById="
				+ updateById + ", updateByName=" + updateByName + ", updateOn=" + updateOn + "]";
	}
    
}