package com.sinotech.settle.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinotech.settle.demo.model.TrainTeacher;

public interface TrainTeacherMapper {
    int deleteByPrimaryKey(String id);
    
    int deleteByIds(@Param("list") List<String> list, @Param("userId") String userId, @Param("realName") String realName);

    int insert(TrainTeacher record);

    int insertSelective(TrainTeacher record);

    TrainTeacher selectByPrimaryKey(String id);
    
    List<TrainTeacher> getTrainTeacherList(@Param("code") String code);
    
    List<TrainTeacher> getTrainTeacherIdNameList();

    int updateByPrimaryKeySelective(TrainTeacher record);

    int updateByPrimaryKey(TrainTeacher record);
    
}