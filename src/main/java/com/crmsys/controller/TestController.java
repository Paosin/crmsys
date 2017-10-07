package com.crmsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Paosin Von Scarlet on 2017/10/5.
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test(ModelAndView mav) {
        mav.addObject("active", "DEPT_MANAGE");
        mav.setViewName("forward:test.jsp");
        return mav;
    }
}
