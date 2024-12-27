package com.shen.controller.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shen.mapper.GoodstableMapper;
import com.shen.mapper.GoodstypeMapper;
import com.shen.pojo.Goodstable;
import com.shen.pojo.Goodstype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    GoodstableMapper goodstableMapper;
    @Autowired
    GoodstypeMapper goodstypeMapper;

    @RequestMapping("/index")
    public String Index(Model model){
        //            传各类型商品过去
        List<Goodstype> goodsType = goodstypeMapper.selectList(null);
//            传轮播广告过去
        List<String> advertisementGoods = Arrays.asList(
                "202111311142959226.jpg",
                "202111311142912965.jpg",
                "202111311142932272.jpg");
        model.addAttribute("goodsType", goodsType);
        model.addAttribute("advertisementGoods", advertisementGoods);
        return "user/header";
    }
//    模糊搜索某个商品
    @RequestMapping("/search")
    public String search(Model model, String mykey) {

//    return indexService.search(model, mykey);
//        模糊搜索
        QueryWrapper<Goodstable> wrapper = new QueryWrapper<>();
        wrapper.like("gname", mykey);
        List<Goodstable> searchgoods = goodstableMapper.selectList(wrapper);
        model.addAttribute("searchgoods", searchgoods);
//            传各类型商品过去
        List<Goodstype> goodsType = goodstypeMapper.selectList(null);
//            传轮播广告过去
        List<String> advertisementGoods = Arrays.asList(
                "202111311142959226.jpg",
                "202111311142912965.jpg",
                "202111311142932272.jpg");
        model.addAttribute("goodsType", goodsType);
        model.addAttribute("advertisementGoods", advertisementGoods);
        return "user/searchResult"; // 跳转到用户主页或其他页面
    }

}
