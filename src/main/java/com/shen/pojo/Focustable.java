package com.shen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Focustable {
    private int id;
    private int goodstableId;
    private int busertableId;
    private Date focustime;
}
