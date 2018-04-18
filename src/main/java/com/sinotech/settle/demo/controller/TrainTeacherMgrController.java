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
 * 培训管理--培训讲师controller
 * 培训讲师管理相关--增删改查及明细
 * @author wujh
 *
 */

@Controller
public class TrainTeacherMgrController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrainTeacherMgrController.class);
	
	@Autowired
	private TrainTeacherService trainTeacherService;

	
	/**
	 * 查询培训讲师列表
	 * @param paramMap
	 *  {teacherName 暂不支持模糊查询，匹配ID、code、name
	 *   pageSize
	 *   pageNumber
	 *  }
	 * @return
	 */
	@RequestMapping(value = "/hr/getTrainTeacherList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
	@ControllerHelper(description = "分页查询培训讲师列表")
	public ReturnResult getTrainTeacherList(@RequestParam Map<String, String> paramMap) {
		LOGGER.info(LogUtil.format("培训管理##查询培训讲师列表 请求参数：",paramMap.toString()));
		List<TrainTeacher> resultList = new ArrayList<TrainTeacher>();
		int pageSize = "".equals(paramMap.get("pageSize"))?20:Integer.parseInt(paramMap.get("pageSize"));
        int pageNumber = "".equals(paramMap.get("pageNum"))?1:Integer.parseInt(paramMap.get("pageNum"));
		try {
			PageHelper.startPage(pageNumber,pageSize);
			resultList = trainTeacherService.getTrainTeacherList(paramMap.get("teacherName"));
		} catch (PersistenceException e) {
			LOGGER.error("培训管理##查询培训讲师列表 SQL解析发生异常： " + e.getMessage());
			return ReturnResult.fail("查询培训讲师列表失败", ErrorCodes.DATABASE_ERROR);
		} catch (DataAccessException e) {
			LOGGER.error("培训管理##查询培训讲师列表 SQL执行发生异常： " + e.getMessage());
			return ReturnResult.fail("查询培训讲师列表失败", ErrorCodes.DATABASE_ERROR);
		} catch (ServiceException e) {
			LOGGER.error("培训管理##查询培训讲师列表发生系统异常： " + e.getMessage());
			return ReturnResult.fail(e.getMessage(), ErrorCodes.TYPE_PARAM_ERROR);
		} catch (Exception e) {
			LOGGER.error("培训管理##查询培训讲师列表发生异常： " + e.getMessage());
			return ReturnResult.fail("查询培训讲师列表失败", ErrorCodes.COMMON_EXCEPTION_ERROR);
		}
		return ReturnResult.pageSuccess(new PageInfo<TrainTeacher>(resultList));
	}

	/**
	 * 查看培训讲师档案明细
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/hr/getTrainTeacherDetail", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
	@ControllerHelper(description = "查看培训讲师档案明细", required = {"id|讲师ID"})
	public ReturnResult getTrainTeacherDetail( String id) {
		LOGGER.info(LogUtil.format("培训管理##查询培训讲师明细  请求参数：",id));
		TrainTeacher trainTeacher = new TrainTeacher();
		try {
			trainTeacher = trainTeacherService.getTrainTeacher(id);
		} catch (PersistenceException e) {
			LOGGER.error("培训管理##查询培训讲师明细 SQL解析发生异常： " + e.getMessage());
			return ReturnResult.fail("查询培训讲师明细失败", ErrorCodes.DATABASE_ERROR);
		} catch (DataAccessException e) {
			LOGGER.error("培训管理##查询培训讲师明细 SQL执行发生异常： " + e.getMessage());
			return ReturnResult.fail("查询培训讲师明细失败", ErrorCodes.DATABASE_ERROR);
		} catch (ServiceException e) {
			LOGGER.error("培训管理##查询培训讲师明细发生系统异常： " + e.getMessage());
			return ReturnResult.fail(e.getMessage(), ErrorCodes.TYPE_PARAM_ERROR);
		} catch (Exception e) {
			LOGGER.error("培训管理##查询培训讲师明细发生异常： " + e.getMessage());
			return ReturnResult.fail("查询培训讲师明细失败", ErrorCodes.COMMON_EXCEPTION_ERROR);
		}
		return ReturnResult.success("查询培训讲师明细成功", trainTeacher);
	}
}
