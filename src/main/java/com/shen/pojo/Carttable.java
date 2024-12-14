package com.shen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data             // Generates getters, setters, toString(), equals(), hashCode() methods
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor  // Generates a no-argument constructor
//购物车
public class Carttable {
    private int id;
//    用户id
    private int busertableId;   // Foreign key to Busertable
//    商品id
    private int goodstableId;   // Foreign key to Goodstable
//    商品数量
    private int shoppingnum;    // Number of items in the cart
}
