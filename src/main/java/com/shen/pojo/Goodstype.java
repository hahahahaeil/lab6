package com.shen.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品类型
public class Goodstype {

    @TableId(value = "id", type = IdType.AUTO)  // 表示 id 字段是自增的
    private Integer id;
    private String typename;
}
