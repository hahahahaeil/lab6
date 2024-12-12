package com.shen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderbasetable {
    private int id;
    private int busertableId;  // Foreign key reference to busertable
    private double amount;
    private byte status;       // Using byte for the tinyint column
    private Date orderdate;    // Using Date to map the datetime column
}
