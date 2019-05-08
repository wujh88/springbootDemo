package com.sinotech.settle.config;

import com.sinotech.settle.utils.DateUtil;
import com.sinotech.settle.utils.StrUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description： spring mvc 前端string空数据到后台转date,400异常处理
 */
//@Configuration
@ControllerAdvice("com.sinotech.settle")
public class BasePackageAdvice {
    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                if (StrUtils.isNull(value)) {
                    setValue(null);
                } else {
                    try {
                        setValue(DateUtil.getDateTimeFormat(value));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
            }
        });
    }
}
