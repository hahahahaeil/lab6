package com.shen.mapper;

import com.shen.pojo.Goodstable;
import com.shen.pojo.Goodstype;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexRepository {
	public List<Goodstable> selectAdvertisementGoods();
	public List<Goodstype> selectGoodsType();
	public List<Goodstable> selectRecommendGoods(Integer tid);
	public List<Goodstable> selectLastedGoods(Integer tid);
	public Goodstable selectAGoods(Integer id);
	public List<Goodstable> search(String mykey);
}
