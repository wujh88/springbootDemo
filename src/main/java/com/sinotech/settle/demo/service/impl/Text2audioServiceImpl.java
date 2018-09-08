package com.sinotech.settle.demo.service.impl;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.sinotech.settle.config.Const4baidu;
import com.sinotech.settle.demo.service.Text2audioService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("Text2audioService1")
public class Text2audioServiceImpl implements Text2audioService {

    @Override
    public void text2audio(String text) {
        AipSpeech client = new AipSpeech(Const4baidu.BAIDU_AI_APPID, Const4baidu.BAIDU_AI_APPKEY, Const4baidu.BAIDU_AI_SECRETKEY);
        TtsResponse res = client.synthesis(text, "zh", 1, null);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "F:\\baiduDemo\\output_"+System.currentTimeMillis() + ".mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
    }
}
