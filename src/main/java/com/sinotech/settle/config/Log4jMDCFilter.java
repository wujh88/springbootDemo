package com.sinotech.settle.config;

//import org.apache.log4j.MDC;  //这里没有选择用 Log4j 的 MDC，而是换成了 slf4j

import com.sinotech.settle.utils.StrUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description： 使用MDC标注日志上下文，在日志中添加文字信息。
 *
 */
//@Component
public class Log4jMDCFilter  implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) rq;
        HttpServletResponse response = (HttpServletResponse) rp;

        try {
            putMDC(request, response); //保存信息
            chain.doFilter(request, response);
        }finally {
            clearMDC(request); //记得 clear 相关信息，否则会导致内存溢出
        }
        return ;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    public void putMDC(HttpServletRequest request, HttpServletResponse response){
//        User user = null;
//        if (!StrUtils.isNull(SecurityUtils.getSubject())) {
//            user = SessionUtils.getCurrentUser();
//        }
//        if(user!=null){
            //String.valueOf(user.getId())，String.valueOf(user.getRealName())
            MDC.put("uId", StrUtils.getUUID());
//        }
    }

    public void clearMDC(HttpServletRequest request){
        MDC.clear();
    }
}
