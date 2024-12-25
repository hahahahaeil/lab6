package com.shen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//订单基础表
public class Orderbasetable {
    private int id;
    private int busertableId;  // Foreign key reference to busertable
//   金额
    private double amount;
//    订单状态
    private byte status;       // Using byte for the tinyint column
//    下单时间
    private Date orderdate;    // Using Date to map the datetime column
}
