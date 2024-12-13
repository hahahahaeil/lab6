package com.shen.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//后台管理员
public class Ausertable {
    @TableId(value = "id", type = IdType.AUTO)  // 表示 id 字段是自增的
    private Integer id;  // 将 int 改为 Integer（包装类）
    private String aname;
    private String apwd;
}
