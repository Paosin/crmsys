package com.crmsys.controller;

import com.crmsys.comm.ResourceConstant;
import com.crmsys.security.annotation.Authorize;
import com.crmsys.service.UserService;
import com.crmsys.po.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpSession session) {
        if (!Objects.isNull(session.getAttribute("user"))) {
            return new ModelAndView("redirect:/sysindex");
        }

        User temp = this.userService.getByUserNmaeAndPassword(user.getUsername(), user.getPassword());
        if (!Objects.isNull(temp) && temp.getEnabled() == 1) {
            session.setAttribute("user", temp);
            return new ModelAndView("redirect:/sysindex");
        }
        Map<String, String> map = new HashMap<>();
        map.put("message", "用户名或密码错误");
        return new ModelAndView("forward:/login.jsp", map);
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "forward:/login.jsp";
    }

    @RequestMapping("/relogin")
    public ModelAndView relogin(ModelAndView mav) {
        mav.addObject("message", "请登录");
        mav.setViewName("forward:/login.jsp");
        return mav;
    }
}
