package com.shen.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//关注表
public class Focustable {
    private int id;
    private int goodstableId;
    private int busertableId;
    private Date focustime;
}
