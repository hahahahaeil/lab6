package com.shen.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shen.mapper.*;
import com.shen.pojo.*;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    BusertableMapper busertableMapper;
    @Autowired
    GoodstableMapper goodstableMapper;
    @Autowired
    CarttableMapper carttableMapper;
    @Autowired
    OrderbasetableMapper orderbasetableMapper;
    @Autowired
    OrderdetailMapper orderdetailMapper;
    @Autowired
    FocustableMapper focustableMapper;
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
        List<Integer> goodstableIds = carttableMapper.selectObjs(queryWrapper);
        List<Goodstable> goodsList = new ArrayList<>();
//        查询了某个用户的购物车
        List<Carttable> cartList = new ArrayList<>();
//        List<Double> SMList = new ArrayList<>();
        Map<Integer, Double> smMap = new HashMap<>();
        Double Total_money = 0.0;  // 用于存储总金额
        for(Integer goodstableId : goodstableIds) {
            Goodstable goodstable = goodstableMapper.selectById(goodstableId);
            QueryWrapper<Carttable> queryCart = new QueryWrapper<>();
            queryCart.eq("goodstable_id", goodstableId);
            Carttable carttable = carttableMapper.selectOne(queryCart);
            if(goodstable != null) {
                cartList.add(carttable);
                goodsList.add(goodstable);
                int spn = carttable.getShoppingnum();
                double gp = goodstable.getGrprice();
                // 计算小计
                double sm = spn * gp;
                // 将商品 ID 和对应的小计存入 Map
                smMap.put(goodstableId, sm);
                Total_money += sm;

            }
        }
        session.setAttribute("Total_money", Total_money);
        System.out.println(goodsList);
        System.out.println(cartList);
        model.addAttribute("cartList", cartList);
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("smMap", smMap);
        model.addAttribute("Total_money", Total_money);
        return "user/cart";
    }
//  结算
    @RequestMapping("/toCount")
    public String toCount(Model model, HttpSession session) {
        //        填两个表 填订单基础表某个用户花了多少钱、并记录时间
//        将购物车的信息填入订单详情表，并且清空这个人的购物车
        Busertable bUser = (Busertable) session.getAttribute("user");
        Integer id = bUser.getId();
        // 从 session 中获取 Total_money
        Object totalMoneyObj = session.getAttribute("Total_money");
        double Total_money = 0.0;
        // 确保 totalMoneyObj 不为空并且是 Double 类型
        if (totalMoneyObj instanceof Double) {
            Total_money = (Double) totalMoneyObj;
        }
        Orderbasetable orderbasetable = new Orderbasetable();
        orderbasetable.setBusertableId(id);
        orderbasetable.setAmount(Total_money);
        orderbasetableMapper.insert(orderbasetable);

//        插入订单详情表
        QueryWrapper<Carttable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", id);
//        获取该用户的所有购物车表
        List<Carttable> cartList = carttableMapper.selectList(queryWrapper);
//        Orderdetail orderdetail = new Orderdetail();
////        与订单表关联
//        orderdetail.setOrderbasetableId(orderbasetable.getId());
        for (Carttable cart : cartList) {
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setOrderbasetableId(orderbasetable.getBusertableId());  // 将订单ID关联到订单详情
            orderdetail.setGoodstableId(cart.getGoodstableId());  // 设置商品ID
            orderdetail.setShoppingnum(cart.getShoppingnum());  // 设置商品数量
            // 插入订单详情表
            orderdetailMapper.insert(orderdetail);
        }
//        删除原有的购物车表,根据用户id
        HashMap<String,Object> map = new HashMap<>();
        map.put("busertable_id", id);
        carttableMapper.deleteByMap(map);
//        结算成功返回首页
        return "user/header";
    }
    @RequestMapping("delete")
    public String deleteCart(@RequestParam("gid")int gid) {
        carttableMapper.deleteById(gid);
        return "redirect:/cart/selectCart";
    }
//    清空购物车
    @RequestMapping("/clearCart")
    public String clearCart(HttpSession session) {
        Busertable bUser = (Busertable) session.getAttribute("user");
        System.out.println(bUser);
        Integer Bid = bUser.getId();
        QueryWrapper<Carttable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", Bid);
//        delete 会根据查询条件进行删除
        carttableMapper.delete(queryWrapper);
        return "user/header";
    }

//
    @RequestMapping("/focus/{id}")
    public String focus(@PathVariable("id") Integer id,Model model,HttpSession session) {
//        完成收藏操作
        Busertable bUser = (Busertable) session.getAttribute("user");
        Focustable focustable = new Focustable();
        focustable.setBusertableId(bUser.getId());
        focustable.setGoodstableId(id);
        focustableMapper.insert(focustable);

        return "user/searchResult";
    }

}
//@RequestMapping("/selectCart")
//    public String selectCart(Model model, HttpSession session) {
//        Busertable bUser = (Busertable) session.getAttribute("user");
//        System.out.println("bUser存在，ID为：" + bUser.getId());
////        欲求其购物车，先求其id
//        Integer id = bUser.getId();
////        查所有买了的商品
//        QueryWrapper<Carttable> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("busertable_id", id)
//                .select("goodstable_id");
//
//        List<Integer> goodstableIds = carttableMapper.selectObjs(queryWrapper);
//        List<Goodstable> goodsList = new ArrayList<>();
////        查询了某个用户的购物车
//        List<Carttable> cartList = new ArrayList<>();
//        for(Integer goodstableId : goodstableIds) {
//            Goodstable goodstable = goodstableMapper.selectById(goodstableId);
//            QueryWrapper<Carttable> queryCart = new QueryWrapper<>();
//            queryCart.eq("goodstable_id", goodstableId);
//            Carttable carttable = carttableMapper.selectOne(queryCart);
//            if(goodstable != null) {
//                cartList.add(carttable);
//                goodsList.add(goodstable);
//
//            }
//        }
//
//        System.out.println(goodsList);
//        System.out.println(cartList);
//        model.addAttribute("cartList", cartList);
//        model.addAttribute("goodsList", goodsList);
//        return "user/cart";
//    }