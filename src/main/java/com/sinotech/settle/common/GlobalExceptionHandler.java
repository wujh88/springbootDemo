package com.sinotech.settle.common;

import com.alibaba.fastjson.JSONException;
import com.luhuiguo.fastdfs.conn.FdfsConnectionPool;
import com.sinotech.settle.config.ErrorCodes;
import com.sinotech.settle.exception.BusinessProhibitionException;
import com.sinotech.settle.exception.CommonIOException;
import com.sinotech.settle.exception.IllegalParametersException;
import com.sinotech.settle.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.ConnectException;
import java.sql.SQLDataException;
import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;


/**
 * 全局的异常处理器
 * 项目名称：sinoTechOMS   
 * 类名称：GlobalExceptionHandler   
 * 类描述：   
 * 创建人：liuhe   
 * 创建时间：2017年10月14日
 * 修改人：liuhe   
 * 修改时间：2017年10月14日 
 * 修改备注：   
 * @version
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger= Logger.getLogger(GlobalExceptionHandler.class);

    @Autowired
    FdfsConnectionPool fdfsConnectionPool;

    /**
     * 处理数据库服务器网络异常
     * @param e
     * @return
     * @throws ServiceException
     */
    @ExceptionHandler(value = { ServiceException.class})
    @ResponseBody
    public ReturnResult ServiceException(ServiceException e) throws Exception {
        logger.error(e.getMessage());
        logger.error(e.getCause());
        return ReturnResult.fail(e.getMessage(),ErrorCodes.ILLEGAL_PARAMETERS_ERROR);
    }

    /**
     * 处理请求参数异常
     * @param e
     * @return
     * @throws IllegalParametersException
     */
    @ExceptionHandler(value = IllegalParametersException.class)
    @ResponseBody
    public ReturnResult unknownAccountException(IllegalParametersException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail(e.getMessage(), ErrorCodes.ILLEGAL_PARAMETERS_ERROR);
    }
    /**
     * 处理请求参数异常
     * @param e
     * @return
     * @throws JSONException
     */
    @ExceptionHandler(value = JSONException.class)
    @ResponseBody
    public ReturnResult JSONException(JSONException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail("数据格式化异常", ErrorCodes.JSON_FORMAT_ERROR);
    }
    /**
     * 处理请求参数异常
     * @param e
     * @return
     * @throws BindException
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ReturnResult bindException(BindException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail("参数格式异常", ErrorCodes.ILLEGAL_PARAMETERS_ERROR);
    }
    /**
     * 业务上禁止异常
     * @param e
     * @return
     * @throws BusinessProhibitionException
     */
    @ExceptionHandler(value = BusinessProhibitionException.class)
    @ResponseBody
    public ReturnResult businessProhibitionException(BusinessProhibitionException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail(e.getMessage(), ErrorCodes.BUSSINESS_FORBIDDEN);
    }
    /**
     * 业务上禁止异常
     * @param e
     * @return
     * @throws CommonIOException
     */
    @ExceptionHandler(value = CommonIOException.class)
    @ResponseBody
    public ReturnResult commonIOException(CommonIOException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail(e.getMessage(), ErrorCodes.SYSTEM_IO_ERROR);
    }
    /**
     * 处理数字转换异常
     * @param e
     * @return
     * @throws NumberFormatException
     */
    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public ReturnResult numberFormatException(NumberFormatException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail("录入的数字格式有误",ErrorCodes.NUMBER_FORMAT_ERROR);
    }

    /**
     * 处理sql语法错误
     * @param e
     * @return
     * @throws SQLSyntaxErrorException
     */
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    @ResponseBody
    public ReturnResult sqlSyntaxErrorException(SQLSyntaxErrorException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail("服务器异常请联系管理员",ErrorCodes.SQL_SYNTAX_ERROR);
    }

    /**
     * 处理值大于为此列指定的允许精度
     * @param e
     * @return
     * @throws SQLDataException
     */
    @ExceptionHandler(value = SQLDataException.class)
    @ResponseBody
    public ReturnResult sqlDataException(SQLDataException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail("服务器异常请联系管理员",ErrorCodes.SQL_DATA_ERROR);
    }

    /**
     * 处理数据库服务器网络异常
     * @param e
     * @return
     * @throws SQLRecoverableException
     */
    @ExceptionHandler(value = { SQLRecoverableException.class,ConnectException.class})
    @ResponseBody
    public ReturnResult sqlRecoverableException(SQLRecoverableException e) throws Exception {
        logger.error(e.getMessage());
        return ReturnResult.fail("服务器异常请联系管理员",ErrorCodes.DATABASE_NETWORK_ERROR);
    }


//    /**
//     * 处理未知异常
//     * @param e
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ReturnResult commonException(Exception e) throws Exception {
//        logger.error(e.getMessage());
//        return ReturnResult.fail("服务器异常请联系管理员",ErrorCodes.COMMON_EXCEPTION_ERROR);
//    }


}
