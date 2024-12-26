package com.shen.controller.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shen.mapper.GoodstableMapper;
import com.shen.pojo.Goodstable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    GoodstableMapper goodstableMapper;

//    模糊搜索某个商品
    @RequestMapping("/search")
    public String search(Model model, String mykey) {

//    return indexService.search(model, mykey);
//        模糊搜索
        QueryWrapper<Goodstable> wrapper = new QueryWrapper<>();
        wrapper.like("gname", mykey);
        List<Goodstable> searchgoods = goodstableMapper.selectList(wrapper);
        model.addAttribute("searchgoods", searchgoods);
        return "user/searchResult";
    }
}
