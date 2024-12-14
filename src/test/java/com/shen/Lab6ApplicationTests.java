package com.shen;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shen.mapper.AusertableMapper;
import com.shen.mapper.CarttableMapper;
import com.shen.mapper.GoodstableMapper;
import com.shen.mapper.GoodstypeMapper;
import com.shen.pojo.Ausertable;
import com.shen.pojo.Carttable;
import com.shen.pojo.Goodstable;
import com.shen.pojo.Goodstype;
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
