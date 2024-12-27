package com.shen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

//验证码控制器
@RestController
@RequestMapping("/validateCode")
public class CaptchaController{

    @GetMapping
    public String generateCaptcha() {
        String captcha = generateRandomCaptcha();
        // 将验证码保存在会话（session）中，以便后端验证
        // 你可以选择使用会话或数据库等方式存储验证码
        // 这里是一个简单示例，将验证码存储在会话中
        // session.setAttribute("captcha", captcha);
        return captcha; // 返回验证码文本
    }

    private String generateRandomCaptcha() {
        Random rand = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            captcha.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return captcha.toString();
    }
}
