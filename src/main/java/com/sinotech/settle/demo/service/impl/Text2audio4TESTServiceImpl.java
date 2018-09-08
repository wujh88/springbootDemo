package com.sinotech.settle.demo.service.impl;

import com.sinotech.settle.demo.service.Text2audioService;
import org.springframework.stereotype.Service;

@Service("Text2audioService2")
public class Text2audio4TESTServiceImpl implements Text2audioService {
    @Override
    public void text2audio(String text) {
        System.out.println("Text2audioService2");
    }
}
