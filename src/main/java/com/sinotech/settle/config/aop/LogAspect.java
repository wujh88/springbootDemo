package com.sinotech.settle.config.aop;

import com.sinotech.settle.common.ReturnResult;
import com.sinotech.settle.config.ErrorCodes;
import com.sinotech.settle.exception.IllegalParametersException;
import com.sinotech.settle.utils.AccountValidatorUtil;
import com.sinotech.settle.utils.DateUtil;
import com.sinotech.settle.utils.JsonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component
@Aspect
public class LogAspect {
    private static Logger logger = Logger.getLogger(LogAspect.class);


    //切入点是使用了ParamVerify注解的方法
    @Pointcut("@annotation(com.sinotech.settle.config.aop.ControllerHelper)")
    private void aroundController() {}


    /**
     * controller层的环绕通知
     * @param point
     * @return
     */
    @Around("aroundController()")
    public Object checkParamMap(ProceedingJoinPoint point) throws Throwable {

        ControllerHelper annotation = null;

        String methodName = point.getSignature().getName();
        String simpleClassName = point.getSignature().getDeclaringType().getSimpleName();
        String className = point.getSignature().getDeclaringTypeName();
        StringBuffer sb = new StringBuffer("【OA】");

        String dateTimeFormat = DateUtil.getDateTimeFormat(new Date());
        sb.append(dateTimeFormat);


        Method method;

        //方法的所有参数名字
        List<String> paramNames = new ArrayList<>();
        try {
            Optional<Method> first = Arrays.stream(Class.forName(className).getMethods()).filter(m -> m.getName().equalsIgnoreCase(methodName)).findFirst();
            method = first.get();

            //获取方法的所有参数名字
            Arrays.stream(method.getParameters()).forEach(a -> paramNames.add(a.getName()));

            annotation = method.getAnnotation(ControllerHelper.class);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        sb.append(" 调用：" + simpleClassName + "的方法:");
        String[] required = {};
        String[] numeric = {};
        String numbericStr = null;

        if ( annotation != null){
            sb.append(" "+annotation.description());

            required = annotation.required();
            numeric = annotation.numeric();
            if (numeric.length > 0){
                numbericStr = Arrays.toString(numeric);
            }
        }
        sb.append("-"+ point.getSignature().getName() + " 请求参数为：");

        Arrays.stream(point.getArgs()).forEach(a -> {

            if (a != null){
                if (a.getClass().isArray()){
                    sb.append(Arrays.toString((Object[]) a)+ " | ");
                } else {
                    sb.append(a.toString()+" | ");
                }
            }else {
                sb.append(" null| ");
            }
        });

        //校验参数
        if (required.length >0){

            List<String> requiredResultList = new ArrayList();
            List<String> numbericResultList = new ArrayList();

            //遍历校验每一个需要校验的参数
            for (int i = 0; i < required.length; i++) {

                if (!required[i].contains("|")){
                    throw new IllegalParametersException("非空参数"+required[i]+" 缺少注释-后台接口错误");
                }
                //对象中的变量
                if (required[i].contains(".")){

                    String objectName = required[i].substring(0,required[i].indexOf("."));
                    String param = required[i].substring(required[i].indexOf(".")+1,required[i].indexOf("|"));
                    String remark = required[i].substring(required[i].indexOf("|")+1);

                    for (int j = 0; j < point.getArgs().length; j++) {
                        //参数对应的名字
                        String argName = paramNames.get(j);

                        if (argName.equalsIgnoreCase(objectName)){

                            try {
                                //将实体类转换成map
                                Map describe = BeanUtils.describe(point.getArgs()[j]);

                                //判断参数是否为空
                                if (StringUtils.isBlank((String)describe.get(param))){
                                    requiredResultList.add(remark);
                                    continue;
                                }

                                //判断该参数是否要求是数字
                                if (numbericStr != null && numbericStr.contains(param)){
                                    //判断是否是数字
                                    if (!AccountValidatorUtil.isRealNumber((String)describe.get(param))){
                                        numbericResultList.add(remark);
                                        continue;
                                    }
                                }

                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }


                        }


                    }

                } else {

                    String param = required[i].substring(0, required[i].indexOf("|"));
                    String remark = required[i].substring(required[i].indexOf("|")+1);

                    //方法中的变量
                    for (int j = 0; j < point.getArgs().length; j++) {
                        //参数对应的名字
                        String argName = paramNames.get(j);
                        if (argName.equalsIgnoreCase(param)){
                            Object arg = point.getArgs()[j];

                            if (arg == null){
                                requiredResultList.add(remark);
                                continue;
                            }


                            if (arg instanceof String){

                                if (StringUtils.isBlank(String.valueOf(arg))){
                                    requiredResultList.add(remark);
                                    continue;
                                }

                            } else if (arg instanceof String[]){

                                if (((String[])arg).length == 0){
                                    requiredResultList.add(remark);
                                    continue;
                                }

                            } else if (arg instanceof Date){
                                if (arg == null){
                                    requiredResultList.add(remark);
                                    continue;
                                }
                            }

                            //判断该参数是否要求是数字
                            if (numbericStr != null && numbericStr.contains(param)){
                                //判断是否是数字
                                if (!AccountValidatorUtil.isRealNumber(String.valueOf(arg))){
                                    numbericResultList.add(remark);
                                    continue;
                                }
                            }


                        }
                    }
                }
            }

            if (requiredResultList.size() > 0 || numbericResultList.size() > 0){
                logger.info(sb.toString());

                StringBuffer errorResult = new StringBuffer("");
                if (requiredResultList.size() > 0){
                    errorResult.append(requiredResultList.toString() + "不能为空");
                }
                if (numbericResultList.size() > 0){
                    errorResult.append(numbericResultList.toString() + "必须是数字");
                }

                ReturnResult returnResult = ReturnResult.fail(errorResult.toString(), ErrorCodes.ILLEGAL_PARAMETERS_ERROR);
                logger.error(annotation.description() +"-"+ methodName + " 的返回结果为：" + JsonUtil.toJsonString(returnResult));
                return returnResult;
            }
        }

        logger.info(sb.toString());
        Object result;
        //继续执行
        result = point.proceed(point.getArgs());
        logger.info(annotation.description() +"-"+ methodName + " 的返回结果为："+JsonUtil.toJsonString(result));
        return result;

    }

}
