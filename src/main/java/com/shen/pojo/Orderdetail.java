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
public class Orderdetail {
    private int id;
    private int orderbasetableId;
    private int goodstableId;
    private int shoppingnum;
}
