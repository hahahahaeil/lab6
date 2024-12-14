package com.shen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shen.pojo.Carttable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CarttableMapper extends BaseMapper<Carttable> {
    public List<Map<String,Object>> selectGoodsShop(Integer uid);
}
