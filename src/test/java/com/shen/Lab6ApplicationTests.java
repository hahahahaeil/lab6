package com.shen;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shen.mapper.AusertableMapper;
import com.shen.mapper.GoodstypeMapper;
import com.shen.pojo.Ausertable;
import com.shen.pojo.Goodstype;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Lab6ApplicationTests {

    @Autowired
    private AusertableMapper ausertableMapper;
    @Autowired
    private GoodstypeMapper goodstypeMapper;
    @Test
    void contextLoads() {
        List<Ausertable> applicant =  ausertableMapper.selectList(null);
        applicant.forEach(System.out::println);
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
