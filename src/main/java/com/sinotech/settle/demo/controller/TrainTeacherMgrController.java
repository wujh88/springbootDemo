package com.sinotech.settle.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinotech.settle.common.ReturnResult;
import com.sinotech.settle.config.aop.ControllerHelper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinotech.settle.config.ErrorCodes;
import com.sinotech.settle.exception.ServiceException;
import com.sinotech.settle.demo.model.TrainTeacher;
import com.sinotech.settle.demo.service.TrainTeacherService;
import com.sinotech.settle.utils.LogUtil;

/**
 * 培训管理
 */

@Controller
public class TrainTeacherMgrController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrainTeacherMgrController.class);
	
	@Autowired
	private TrainTeacherService trainTeacherService;

	
	/**
	 * rrrr
	 * @param paramMap
	 *  {teacherName 暂不支持模糊查询，匹配ID、code、name
	 *   pageSize
	 *   pageNumber
	 *  }
	 * @return
	 */
	@RequestMapping(value = "/hr/getTrainTeacherList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
	@ControllerHelper(description = "rrrr")
	public ReturnResult getTrainTeacherList(@RequestParam Map<String, String> paramMap) {
		LOGGER.info(LogUtil.format("rrrr##rrrr 请求参数：",paramMap.toString()));
		List<TrainTeacher> resultList = new ArrayList<TrainTeacher>();
		int pageSize = "".equals(paramMap.get("pageSize"))?20:Integer.parseInt(paramMap.get("pageSize"));
        int pageNumber = "".equals(paramMap.get("pageNum"))?1:Integer.parseInt(paramMap.get("pageNum"));
		try {
			PageHelper.startPage(pageNumber,pageSize);
			resultList = trainTeacherService.getTrainTeacherList(paramMap.get("teacherName"));
		} catch (Exception e) {
			LOGGER.error("rrrr##rrrr发生异常： " + e.getMessage());
			return ReturnResult.fail("rrr失败", ErrorCodes.COMMON_EXCEPTION_ERROR);
		}
		return ReturnResult.pageSuccess(new PageInfo<TrainTeacher>(resultList));
	}
}
