package com.shen.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shen.mapper.GoodstypeMapper;
import com.shen.pojo.Goodstype;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


//requestParam强势绑定参数
@Controller
@RequestMapping("type")
public class TypeController {

    @Autowired
    GoodstypeMapper goodstypeMapper;

    public TypeController(@Qualifier("goodstypeMapper") GoodstypeMapper goodstypeMapper) {
        this.goodstypeMapper = goodstypeMapper;
    }

//    @RequestMapping("/selectAllTypeByPage")
//    public String selectAllTypeByPage(@RequestParam(defaultValue = "1") int currentPage, Model model) {
//        // 处理请求，获取分页数据
//        model.addAttribute("currentPage", currentPage);
//        // 返回页面
//        return "/admin/selectGoodsType";
//    }
//@RequestMapping("/selectAllTypeByPage")
//public String selectAllTypeByPage(@RequestParam(defaultValue = "1") int currentPage, Model model) {
//    // 处理请求，获取分页数据
//    Page<Goodstype> gts = new Page<>(1, 5);
//    goodstypeMapper.selectPage(gts, null);
//    // 将分页数据添加到模型中
//    model.addAttribute("allTypes", gts.getPages());  // 当前页的内容
//    model.addAttribute("currentPage", currentPage);
//    model.addAttribute("totalPage", gts.getTotal());
//    // 返回页面
//    return "/admin/selectGoodsType";
//}
@RequestMapping("/selectAllTypeByPage")
public String selectAllTypeByPage(@RequestParam(defaultValue = "1") int currentPage, Model model) {
    int pageSize = 5;  // 每页显示5条记录

    // 创建分页对象
    Page<Goodstype> page = new Page<>(currentPage, pageSize);

    // 查询数据，注意goodstypeMapper应该是你的MyBatis Mapper
    goodstypeMapper.selectPage(page, null);  // 查询所有商品类型（没有条件的查询）

    // 将分页数据添加到模型中
    model.addAttribute("allTypes", page.getRecords());  // 当前页的数据
    model.addAttribute("currentPage", currentPage);     // 当前页
    model.addAttribute("totalPage", page.getPages());   // 总页数

    return "/admin/selectGoodsType";
}


    @RequestMapping("/toAddType")
    public String toAddType(Model model) {
        model.addAttribute("goodsType", new Goodstype());
        return "/admin/addType";
    }
    @PostMapping("/addType")
    public String toAddType(@ModelAttribute("goodsType") Goodstype goodsType) {
        System.out.println(goodsType);
        goodstypeMapper.insert(goodsType);
        return "redirect:/type/toAddType";
    }
    @GetMapping("/deleteType")
    public String deleteType(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            // 执行删除操作
            int delete = goodstypeMapper.deleteById(id);
            if (delete == 1) {
                // 删除成功
                redirectAttributes.addFlashAttribute("message", "类型删除成功！");
            } else {
                // 未知原因导致删除失败
                redirectAttributes.addFlashAttribute("message", "类型删除失败，请稍后再试！");
            }
        } catch (Exception e) {
            // 捕获外键约束异常或其他异常
            redirectAttributes.addFlashAttribute("message", "无法删除该类型：存在关联商品！");
        }
        return "redirect:/type/selectAllTypeByPage"; // 重定向回列表页面
    }


}
