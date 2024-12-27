package com.shen.controller.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shen.mapper.BusertableMapper;
import com.shen.mapper.GoodstypeMapper;
import com.shen.pojo.Busertable;
import com.shen.pojo.Goodstype;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    BusertableMapper busertableMapper;
    @Qualifier("goodstypeMapper")
    @Autowired
    private GoodstypeMapper goodstypeMapper;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("bUser", new Busertable());
        return "/user/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("bUser") Busertable bUser,
                        RedirectAttributes redirectAttributes,
                        HttpSession session,Model model) {
        // 从表单中获取输入的邮箱和密码

        String email = bUser.getBemail();
        String password = bUser.getBpwd();
        session.setAttribute("bUser", bUser);
//        System.out.println("bUser存在，ID为：" + bUser.getId());

        // 查询数据库，查找该邮箱的用户信息
        QueryWrapper<Busertable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bemail", email); // 使用邮箱进行查询
        Busertable user = busertableMapper.selectOne(queryWrapper);

        // 验证用户是否存在以及密码是否匹配
        if (user != null && user.getBpwd().equals(password)) {
            // 登录成功，可以在 Session 中存储用户信息
            session.setAttribute("user", user);

//            传各类型商品过去
            List<Goodstype> goodsType = goodstypeMapper.selectList(null);
//            传轮播广告过去
            List<String> advertisementGoods = Arrays.asList(
                    "202111311142959226.jpg",
                    "202111311142912965.jpg",
                    "202111311142932272.jpg");
            model.addAttribute("goodsType", goodsType);
            model.addAttribute("advertisementGoods", advertisementGoods);
            return "redirect:/index "; // 跳转到用户主页或其他页面
        } else {
            // 登录失败，传递错误信息
            redirectAttributes.addFlashAttribute("errorMessage", "邮箱或密码错误！");

            return "redirect:/login"; // 返回登录页面
        }
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("bUser", new Busertable());
        return "/user/register";
    }
    // 处理注册提交
    @PostMapping("/register")
    public String register(@ModelAttribute("bUser") Busertable bUser,
                           Model model, BindingResult result,RedirectAttributes redirectAttributes) {
        // 校验表单数据
        // 校验确认密码是否一致
        if (!bUser.getBpwd().equals(bUser.getRebpwd())) {
            redirectAttributes.addFlashAttribute("message", "两次密码不同");
            return "/user/register"; // 返回注册页面
        }

        QueryWrapper<Busertable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bemail", bUser.getBemail());
        long count = busertableMapper.selectCount(queryWrapper);

        if (count > 0) {
            redirectAttributes.addFlashAttribute("message", "该邮箱已经注册");
            return "/user/register";
        }
        // 校验确认密码是否与密码一致
        busertableMapper.insert(bUser);
        // 保存用户数据
        return "redirect:/user/login";  // 注册成功后重定向到登录页面
    }


}
