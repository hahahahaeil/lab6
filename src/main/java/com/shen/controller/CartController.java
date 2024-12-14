package com.shen.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shen.mapper.BusertableMapper;
import com.shen.mapper.CarttableMapper;
import com.shen.mapper.GoodstableMapper;
import com.shen.pojo.Busertable;
import com.shen.pojo.Carttable;
import com.shen.pojo.Goodstable;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    BusertableMapper busertableMapper;
    @Autowired
    GoodstableMapper goodstableMapper;
    @Autowired
    CarttableMapper carttableMapper;
    @RequestMapping("/userInfo")
    public String userInfo() {
        return "user/userInfo";
    }
//    修改密码
    @PostMapping("/updateUpwd")
    public String userInfo(@RequestParam("bemail")String bemail,
                           @RequestParam("bpwd")String bpwd) {
        QueryWrapper<Busertable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bemail", bemail);
        Busertable user = busertableMapper.selectOne(queryWrapper);
        user.setBpwd(bpwd);
        busertableMapper.updateById(user);
        return "user/header";
// 修改成功跳回原来页面
    }
    @RequestMapping("/selectCart")
    public String selectCart(Model model, HttpSession session) {
        Busertable bUser = (Busertable) session.getAttribute("user");
        System.out.println("bUser存在，ID为：" + bUser.getId());
//        欲求其购物车，先求其id
        Integer id = bUser.getId();
//        查所有买了的商品
        QueryWrapper<Carttable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", id)
                .select("goodstable_id");

        QueryWrapper<Carttable> queryCart = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", id);
//        查询所有的购物车
        List<Carttable> cartList = carttableMapper.selectList(queryCart);
        List<Integer> goodstableIds = carttableMapper.selectObjs(queryWrapper);
        List<Goodstable> goodsList =  goodstableMapper.selectByIds(goodstableIds);
        System.out.println(goodsList);
        System.out.println(cartList);
        model.addAttribute("cartList", cartList);
        model.addAttribute("goodsList", goodsList);
        return "user/cart";
    }

    @RequestMapping("delete")
    public String deleteCart(@RequestParam("gid")int gid) {
        carttableMapper.deleteById(gid);
        return "redirect:/cart/selectCart";
    }
}
