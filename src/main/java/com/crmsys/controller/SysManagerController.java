package com.crmsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Controller
public class SysManagerController {

    @RequestMapping("/sysindex")
    public String index(){
        return "sysmgr/index.jsp";
    }
}
