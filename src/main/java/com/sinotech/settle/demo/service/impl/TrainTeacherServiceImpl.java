package com.sinotech.settle.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotech.settle.demo.mapper.TrainTeacherMapper;
import com.sinotech.settle.demo.model.TrainTeacher;
import com.sinotech.settle.demo.service.TrainTeacherService;
import com.sinotech.settle.utils.KeyUtils;
import com.sinotech.settle.utils.RedisCacheUtils;

@Service
public class TrainTeacherServiceImpl implements TrainTeacherService{

	@Autowired
	private TrainTeacherMapper trainTeacherMapper;
	
	@Override
	public int addTrainTeacher(TrainTeacher trainTeacher) {
		// TODO Auto-generated method stub

        //生成记录流水
        String trainTeacherId = KeyUtils.getKey("train_teacher",null,new Date(),6);
		//设置记录中部分字段
        trainTeacher.setId(trainTeacherId);
        trainTeacher.setCode(getTrainTeacherCode());
        trainTeacher.setCreateOn(new Date());
        trainTeacher.setUpdateOn(new Date());
        int index = 0;
        index = trainTeacherMapper.insertSelective(trainTeacher);
		return index;
	}

	@Override
	public int updateTrainTeacher(TrainTeacher trainTeacher) {
		// TODO Auto-generated method stub

        int index = 0;
        index = trainTeacherMapper.updateByPrimaryKeySelective(trainTeacher);
		return index;
	}

	@Override
	public int deleteTrainTeacher(String[] ids) {

        // 批量删除档案信息
        int index = 0;
        // TODO
        return index;
	}

	@Override
	public List<TrainTeacher> getTrainTeacherList(String code) {
		return trainTeacherMapper.getTrainTeacherList(code);
	}
	@Override
	public List<TrainTeacher> getTrainTeacherIdNameList() {
		return trainTeacherMapper.getTrainTeacherIdNameList();
	}

	@Override
	public TrainTeacher getTrainTeacher(String id) {
		return trainTeacherMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获得培训讲师编码
	 * @return
	 */
	private String getTrainTeacherCode() {
        int no = (int) RedisCacheUtils.incr("train_teacherCode", 1);
        return "TEACH" + StringUtils.leftPad(String.valueOf(no), 4,"0");
    }
}
