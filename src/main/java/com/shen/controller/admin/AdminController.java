package com.shen.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shen.mapper.AusertableMapper;
import com.shen.pojo.Ausertable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AusertableMapper ausertableMapper;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("ausertable", new Ausertable());  // 提供一个空的 Ausertable 对象用于绑定表单数据
        return "/admin/login";  // 返回登录页面视图
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("ausertable") Ausertable ausertable, Model model, HttpSession session) {
        // 使用用户名查询数据库，验证密码
        QueryWrapper<Ausertable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aname", ausertable.getAname());
        Ausertable existingUser = ausertableMapper.selectOne(queryWrapper);
        if (existingUser != null && existingUser.getApwd().equals(ausertable.getApwd())) {
            session.setAttribute("user", existingUser);
            // 如果用户名存在且密码匹配，则表示登录成功
            return "/admin/header";  // 返回登录页面
        } else {
            // 如果用户名或密码错误，返回错误消息
            model.addAttribute("errorMessage", "用户名或密码错误");
//            在这里还需要传过去广告图片
            return "/admin/header";  // 返回登录页面
        }
    }




}
//      这里有误，是插入操作
//    @RequestMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("ausertable", new Ausertable());
//        return "/admin/login";
//    }
//    // 处理表单提交并将数据保存到数据库
//    @PostMapping("/login")
//    public String login(@ModelAttribute("ausertable") Ausertable ausertable) {
//        ausertableMapper.insert(ausertable);  // 保存数据
//        return "redirect:/admin/login";  // 登录成功后跳转到仪表盘
//    }

//    主页控制