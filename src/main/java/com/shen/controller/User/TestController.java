package com.shen.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(Model model) {
        String img2 ="images/202111309210632700.jpg";
        model.addAttribute("img2", img2);
        return "test";
    }


}
