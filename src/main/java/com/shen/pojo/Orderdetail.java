package com.shen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
@AllArgsConstructor
@Data
//订单详情表
//把购物车表的东西完全粘过来
public class Orderdetail {
    private int id;
//    订单编号
    private int orderbasetableId;
//    商品编号
    private int goodstableId;
//    购买数量
    private int shoppingnum;
}
