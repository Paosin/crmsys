package com.crmsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Controller
public class IndexController {

    @RequestMapping("index.do")
    public String index(){
        return "redirect:login.jsp";
    }
}
