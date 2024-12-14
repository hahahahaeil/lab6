//package com.shen.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.shen.mapper.CarttableMapper;
//import com.shen.pojo.Busertable;
//import com.shen.pojo.Carttable;
//import com.shen.pojo.Goodstable;
//import com.shen.util.MyUtil;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class CartServiceImpl implements CartService {
//    @Autowired
//    CarttableMapper carttableMapper;
//
//    @Override
//    public String selectCart(Model model, HttpSession session, String act) {
//        Busertable bUser = (Busertable) session.getAttribute("bUser");
//        Integer id = bUser.getId();
////        我需要一个cartlist，里面有每个商品的单价、图片、单价，数量、小计（购物车表）
////        首先查出bid中gid的商品属性、再小计，成为一条记录
//        QueryWrapper<Carttable> queryWrapper = new QueryWrapper<>();
////        查询did中gid属性
//        queryWrapper.eq("busertable_id", id)
//                .select("goodstable_id");
//        // 执行查询
//        List<Integer> goodstableIds = carttableMapper.selectObjs(queryWrapper);
//
//
//        List<Integer> l0 = carttableMapper.selectBy
//        List<Goodstable> l1 =
//        List<Map<String, Object>> list = carttableMapper.;
//        double sum = 0;
//        for (Map<String, Object> map : list) {
//            sum = sum + (Double)map.get("smallsum");
//        }
//        model.addAttribute("total", sum);
//        model.addAttribute("cartlist", list);
//        //广告区商品
////        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
//        //导航栏商品类型
////        model.addAttribute("goodsType", indexRepository.selectGoodsType());
//        if("toCount".equals(act)) {//去结算页面
//            return "user/count";
//        }
//        return "user/cart";
//    }
//}
