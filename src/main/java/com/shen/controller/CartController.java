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

import java.util.*;

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
    @Autowired
    GoodstypeMapper goodstypeMapper;

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

        //            传各类型商品过去
        List<Goodstype> goodsType = goodstypeMapper.selectList(null);
//            传轮播广告过去
        List<String> advertisementGoods = Arrays.asList(
                "202111311142959226.jpg",
                "202111311142912965.jpg",
                "202111311142932272.jpg");
        model.addAttribute("goodsType", goodsType);
        model.addAttribute("advertisementGoods", advertisementGoods);

        return "user/cart";
    }
//  结算
    @RequestMapping("/toCount")
    public String toCount(Model model, HttpSession session) {
        //        填两个表 填订单基础表某个用户花了多少钱、并记录时间
//        将购物车的信息填入订单详情表，并且清空这个人的购物车
//        生成订单表，状态为0，生成订单详情表，将该订单状态改为1
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
////        与订单表关联
        QueryWrapper<Orderbasetable> queryOrderbasetable = new QueryWrapper<>();
        queryOrderbasetable.eq("busertable_id", id).eq("status", (byte) 0);
        Orderbasetable orderbase = orderbasetableMapper.selectOne(queryOrderbasetable);
        System.out.println(orderbase);
        for (Carttable cart : cartList) {
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setOrderbasetableId(orderbase.getId());  // 将订单ID关联到订单详情
            orderdetail.setGoodstableId(cart.getGoodstableId());  // 设置商品ID
            orderdetail.setShoppingnum(cart.getShoppingnum());  // 设置商品数量
            // 插入订单详情表
            orderdetailMapper.insert(orderdetail);
        }
        orderbase.setStatus((byte) 1);
        orderbasetableMapper.updateById(orderbase);

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
        return "redirect:/index";
    }

//    我的收藏
    @RequestMapping("myFocus")
    public String myFocus(Model model, HttpSession session) {
//        获取用户信息
        Busertable bUser = (Busertable) session.getAttribute("user");
        System.out.println(bUser);
        Integer Bid = bUser.getId();
//        查询该用户的收藏
//        这东西要传的是该用户收藏的所有商品详细
//        所以要传的是一个商品列表
        QueryWrapper<Focustable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", Bid).select("goodstable_id");
        List<Integer> goodstableIds = focustableMapper.selectObjs(queryWrapper);
        List<Goodstable> goodsList = goodstableMapper.selectByIds(goodstableIds);
//            传各类型商品过去
        List<Goodstype> goodsType = goodstypeMapper.selectList(null);
//            传轮播广告过去
        List<String> advertisementGoods = Arrays.asList(
                "202111311142959226.jpg",
                "202111311142912965.jpg",
                "202111311142932272.jpg");
        model.addAttribute("goodsType", goodsType);
        model.addAttribute("advertisementGoods", advertisementGoods);
        model.addAttribute("myFocus", goodsList);
        return "user/myFocus";

    }
//  收藏商品
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
//  我的订单
    @RequestMapping("/myOder")
    public String myOder(Model model, HttpSession session) {
//  查这个人的所有订单表
        Busertable bUser = (Busertable) session.getAttribute("user");
        QueryWrapper<Orderbasetable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", bUser.getId());
        List<Orderbasetable> orderbasetables = orderbasetableMapper.selectList(queryWrapper);

        //            传各类型商品过去
        List<Goodstype> goodsType = goodstypeMapper.selectList(null);
//            传轮播广告过去
        List<String> advertisementGoods = Arrays.asList(
                "202111311142959226.jpg",
                "202111311142912965.jpg",
                "202111311142932272.jpg");
        model.addAttribute("goodsType", goodsType);
        model.addAttribute("advertisementGoods", advertisementGoods);

        model.addAttribute("myOrder", orderbasetables);
        return "user/myOrder";
    }
    @RequestMapping("/orderDetail/{id}")
    public String orderDetail(@PathVariable("id") Integer id,Model model,HttpSession session) {
//        获取订单表id，根据订单表id获取订单详情表
//        颠，需要根据订单表有的商品去查商品表
//        还需要传回商品总量
//        传两个对象，某条订单详情，某条商品记录
        QueryWrapper<Orderdetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderbasetable_id", id);
        List<Orderdetail> orderdetails = orderdetailMapper.selectList(queryWrapper);
//        遍历这个列表，获取对应的商品id
        // 遍历订单详情列表
        List<Goodstable> goodstableList = new ArrayList<>();
        for (Orderdetail orderdetail : orderdetails) {
            // 获取当前订单详情的商品ID
            Integer goodId = orderdetail.getGoodstableId();

            // 使用商品ID查询商品表
            QueryWrapper<Goodstable> goodQueryWrapper = new QueryWrapper<>();
            goodQueryWrapper.eq("id", goodId);
            Goodstable good = goodstableMapper.selectOne(goodQueryWrapper);

            // 处理查询到的商品信息
            if (good != null) {
                // 如果存在，存入列表
                goodstableList.add(good);
            } else {
                // 如果未找到商品，处理这种情况
                System.out.println("商品ID " + goodId + " 未找到");
            }
        }
//        传过去一个详情
        model.addAttribute("orderDetail", goodstableList);
//        传过去一个用来看总数
        model.addAttribute("Order", orderdetails);
        return "user/orderDetail";
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