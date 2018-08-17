package com.sinotech.settle.demo.controller;

import com.alibaba.fastjson.JSON;
import com.sinotech.settle.common.ReturnResult;
import com.sinotech.settle.demo.service.Text2audioService;
import com.sinotech.settle.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpeechController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechController.class);

    @Autowired
    private Text2audioService text2audioService;

    /**
     * 测试接口
     * @param json
     * @return
     */
    @RequestMapping(value = "/text2audio", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult text2audio(@RequestBody String json) {
        LOGGER.info(LogUtil.format("语音合成##SpeechController.text2audio()##业务入参" + json));
        text2audioService.text2audio(JSON.parseObject(json).getString("text"));
        return ReturnResult.success("查询成功");

    }
}
