package com.shen;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shen.mapper.*;
import com.shen.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Lab6ApplicationTests {

    @Autowired
    private AusertableMapper ausertableMapper;
    @Autowired
    private GoodstypeMapper goodstypeMapper;
    @Autowired
    private GoodstableMapper goodstableMapper;
    @Autowired
    CarttableMapper carttableMapper;
    @Autowired
    OrderbasetableMapper orderbasetableMapper;
    @Autowired
    FocustableMapper focustableMapper;
    @Test
    void seek_page(){
        int pageSize = 5;  // 每页显示的记录数
        Page<Orderbasetable> page = new Page<>(1, pageSize);
//        获取全部
        orderbasetableMapper.selectPage(page, null);
//       返回当前页
        List<Orderbasetable> orders = page.getRecords();
        System.out.println(orders);
    }
    @Test
    void seek_fc(){
        QueryWrapper<Focustable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", 9).select("goodstable_id");
        List<Integer> goodstableIds = focustableMapper.selectObjs(queryWrapper);
        System.out.println(goodstableIds);
    }
    @Test
    void insert_Ob(){
        Orderbasetable orderbasetable = new Orderbasetable();
        orderbasetable.setBusertableId(9);
        orderbasetable.setAmount(1000);
        orderbasetableMapper.insert(orderbasetable);
        //        插入订单详情表
        QueryWrapper<Carttable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", 9);
//        获取该用户的所有购物车表
        List<Carttable> cartList = carttableMapper.selectObjs(queryWrapper);
        Orderdetail orderdetail = new Orderdetail();
        orderdetail.setOrderbasetableId(666);

        System.out.println(cartList);
    }

    @Test
    void contextLoads() {
        List<Ausertable> applicant =  ausertableMapper.selectList(null);
        applicant.forEach(System.out::println);
    }
    @Test
    void seek_gids(){
        Integer id = 9;
        QueryWrapper<Carttable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busertable_id", id)
                .select("goodstable_id");       // 只查询goodstable_id字段;
        // 执行查询
        List<Integer> goodstableIds = carttableMapper.selectObjs(queryWrapper);
//        现在是呈现的逻辑，数量是已知的
        List<Goodstable> goodsList =  goodstableMapper.selectByIds(goodstableIds);
        System.out.println(goodsList);
//      现在要做的就是连起来，把查到的商品和购物车表中的东西联合成一个列表再展示

    }
    //    测试插入
    @Test
    public void testInsert(){
        Ausertable ausertable = new Ausertable();
        ausertable.setAname("三龙");
        ausertable.setApwd("male");
        int res = ausertableMapper.insert(ausertable);
        System.out.println(res);
        System.out.println(ausertableMapper.selectById(ausertable.getId()));
    }
    @Test
    public void testPage(){
//        参数一、当前页
//        参数二、页面大小
        Page<Goodstype> page = new Page<>(1, 5);
        goodstypeMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getRecords());
//        System.out.println(page.getTotal());
    }

}
