package com.sinotech.settle.demo.service.impl;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.sinotech.settle.demo.service.Text2audioService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Text2audioServiceImpl implements Text2audioService {

    private final String appId = "11680458";
    private final String appKey = "n38pBi95TsZVVDKe8izTbqDc";
    private final String secretKey = "5kugCPczckCi7luy7K58d9FkkjpcCYZA";

    @Override
    public void text2audio(String text) {
        AipSpeech client = new AipSpeech(appId, appKey, secretKey);
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
