package com.shen.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shen.mapper.GoodstableMapper;
import com.shen.mapper.GoodstypeMapper;
import com.shen.pojo.Goodstable;
import com.shen.pojo.Goodstype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodController {
    @Autowired
    GoodstableMapper goodstableMapper;
    @Autowired
    GoodstypeMapper goodstypeMapper;
    // 显示商品列表，支持分页


    @RequestMapping("/selectAllGoodsByPage")
    public String selectAllGoodsByPage(@RequestParam(defaultValue = "0") int currentPage, Model model) {
        int pageSize = 5; // 每页显示的商品数量
        Page<Goodstable> page = new Page<>(currentPage, pageSize);
        goodstableMapper.selectPage(page,null);

        model.addAttribute("goodsTable", page.getRecords());  // 获取商品列表
        model.addAttribute("currentPage", currentPage);  // 当前页
        model.addAttribute("totalPage", page.getPages());  // 总页数
        return "admin/adminGoods";
    }
    @GetMapping("/toAddGoods")
    public String toAddGoods(Model model) {
        // 获取所有商品类型
        List<Goodstype> goodsType = goodstypeMapper.selectList(null);
        model.addAttribute("goodsTable", new Goodstable()); // 传递商品表单对象
        model.addAttribute("goodsType", goodsType); // 传递商品类型列表
        return "admin/addGoods"; // 返回到商品添加页面
    }

    @PostMapping("/addGoods")
    public String addGoods(@ModelAttribute("goodsTable") Goodstable goodstable,Model model) {

        System.out.println(goodstable);
        goodstableMapper.insert(goodstable);
        return "redirect:/goods/toAddGoods";
    }
//    如果商品在购物车的话，无法删除
    @GetMapping("/delete")
    public String deleteType(@RequestParam("id") Integer id,RedirectAttributes redirectAttributes) {
        try {
            goodstableMapper.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "商品删除成功");
            return "redirect:/goods/selectAllGoodsByPage?currentPage=0"; // 重定向回列表页面
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "商品在购物车中，无法删除");
            return "redirect:/goods/selectAllGoodsByPage?currentPage=0";
        }
    }
    @GetMapping("detail")
    public String detail(@RequestParam("id") Integer id, Model model) {
        Goodstable goodstable = goodstableMapper.selectById(id);
        model.addAttribute("goods", goodstable);
        return "admin/detail";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        Goodstable goodstable = goodstableMapper.selectById(id);
        List<Goodstype> goodsType = goodstypeMapper.selectList(null);  // 获取所有商品类型

        model.addAttribute("goods", goodstable);
        model.addAttribute("goodsType", goodsType);  // 把商品类型数据添加到模型中

        return "admin/updateAGoods";
    }
    @PostMapping("/update")
    public String updateGoods(@ModelAttribute Goodstable goodstable) {
        goodstableMapper.updateById(goodstable);
        return "redirect:/goods/selectAllGoodsByPage?currentPage=0";
    }

}
