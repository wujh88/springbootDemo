package com.sinotech.settle.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotech.settle.demo.mapper.TrainTeacherMapper;
import com.sinotech.settle.demo.model.TrainTeacher;
import com.sinotech.settle.demo.service.TrainTeacherService;

@Service
public class TrainTeacherServiceImpl implements TrainTeacherService{

	@Autowired
	private TrainTeacherMapper trainTeacherMapper;

	@Override
	public List<TrainTeacher> getTrainTeacherList(String code) {
		return trainTeacherMapper.getTrainTeacherList(code);
	}

}
