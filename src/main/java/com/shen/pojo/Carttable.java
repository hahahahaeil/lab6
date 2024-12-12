package com.shen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data             // Generates getters, setters, toString(), equals(), hashCode() methods
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor  // Generates a no-argument constructor
public class Carttable {
    private int id;
    private int busertableId;   // Foreign key to Busertable
    private int goodstableId;   // Foreign key to Goodstable
    private int shoppingnum;    // Number of items in the cart
}
