package com.shen.controller.User;

import com.shen.mapper.GoodstableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    GoodstableMapper goodstableMapper;
    @RequestMapping("/index")
    public String index(Model model) {

    }
}
