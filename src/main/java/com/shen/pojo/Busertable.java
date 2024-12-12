package com.shen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data            // Generates getters, setters, toString(), equals(), hashCode() methods
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor  // Generates a no-argument constructor
public class Busertable {
    private int id;
    private String bemail;
    private String bpwd;
}
