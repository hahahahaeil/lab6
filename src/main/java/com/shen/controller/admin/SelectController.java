package com.shen.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shen.mapper.BusertableMapper;
import com.shen.mapper.OrderbasetableMapper;
import com.shen.pojo.Busertable;
import com.shen.pojo.Orderbasetable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SelectController {

    @Autowired
    private OrderbasetableMapper orderbasetableMapper;
    @Autowired
    private BusertableMapper busertableMapper;
//    查询订单
//    分页的同时还要返回对应用户的信息
    @RequestMapping("selectOrder")
    public String selectOrder(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                              Model model){
        int pageSize = 5;  // 每页显示的记录数
        Page<Orderbasetable> page = new Page<>(currentPage, pageSize);
//        获取全部
        orderbasetableMapper.selectPage(page, null);
//       返回当前页
        List<Orderbasetable> orders = page.getRecords();

        List<Map<String, Object>> orderByPage = new ArrayList<>();

        for (Orderbasetable order : orders) {
            Map<String, Object> orderMap = new HashMap<>();

            // 获取订单的字段
            orderMap.put("id", order.getId());
            orderMap.put("amount", order.getAmount());
            orderMap.put("status", order.getStatus());
            orderMap.put("orderdate", order.getOrderdate());

            // 获取用户数据
            Busertable user = busertableMapper.selectById(order.getBusertableId()); // 假设订单有 userId 字段

            orderMap.put("bemail", user.getBemail());

            // 将合并后的数据添加到列表中
            orderByPage.add(orderMap);
        }

        model.addAttribute("allOrders", orderByPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", page.getPages());

        return "admin/allOrder";

    }

    @RequestMapping("selectUser")
    public String selectUser(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,Model model){
        int pageSize = 5;
        Page<Busertable> page = new Page<>(currentPage, pageSize);
        busertableMapper.selectPage(page, null);
        model.addAttribute("allUsers", page.getRecords());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", page.getPages());
        return "admin/allUser";
    }


    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody  // 确保返回的是 JSON 格式
    public  Map<String, String> deleteUser(@PathVariable("id") Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            busertableMapper.deleteById(id);  // 删除用户
            response.put("status", "success");  // 返回成功状态
            System.out.println(response);
        } catch (Exception e) {
            response.put("status", "failure");  // 返回失败状态
            System.out.println(response.toString());
        }
        return response;  // 自动转换为 JSON 格式返回
    }

//    安全退出
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/admin/login";
    }
}
