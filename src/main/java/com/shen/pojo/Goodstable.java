package com.shen.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//商品信息表
public class Goodstable {
    @TableId(value = "id", type = IdType.AUTO)  // 表示 id 字段是自增的
    private Integer id;
    private String gname;
    private double goprice;
    private double grprice;
    private int gstore;
    private String gpicture;
    @TableField("isRecommend")  // 显式指定数据库字段名
    private byte isRecommend;
    @TableField("isAdvertisement")  // 显式指定数据库字段名
    private byte isAdvertisement;
    private int goodstypeId;
}
