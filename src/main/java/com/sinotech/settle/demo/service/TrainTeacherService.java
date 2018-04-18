package com.sinotech.settle.demo.service;

import java.util.List;

import com.sinotech.settle.demo.model.TrainTeacher;

/**
 * 培训讲师信息管理相关业务逻辑处理
 * 
 * @author wujh
 *
 */
public interface TrainTeacherService {

	/**
	 * 新增培训讲师档案记录
	 * @param trainTeacher
	 * @return
	 */
	int addTrainTeacher(TrainTeacher trainTeacher);
	
	/**
	 * 更新培训讲师档案
	 * @param trainTeacher
	 * @return
	 */
	int updateTrainTeacher(TrainTeacher trainTeacher);
	
	/**
	 * 批量删除培训讲师档案---逻辑删除，即把是否可用字段改为不可用
	 * @param ids
	 * @return
	 */
	int deleteTrainTeacher(String[] ids);
	
	/**
	 * 根据查询条件查出讲师列表
	 * @param code
	 * @return
	 */
	List<TrainTeacher> getTrainTeacherList(String code);
	
	List<TrainTeacher> getTrainTeacherIdNameList();
	
	/**
	 * 根据讲师ID查看讲师档案详情
	 * @param id
	 * @return
	 */
	TrainTeacher getTrainTeacher(String id);
}
