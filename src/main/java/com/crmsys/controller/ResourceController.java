package com.crmsys.controller;

import com.crmsys.comm.ResourceConstant;
import com.crmsys.po.Role;
import com.crmsys.po.User;
import com.crmsys.security.annotation.Authorize;
import com.crmsys.service.ResourceService;
import com.crmsys.service.RoleService;
import com.crmsys.vo.MenuVo;
import com.crmsys.vo.ResourceVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
@Controller
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleService roleService;
    /**
     * 通过当前登录的用户查找相应的菜单资源
     * @param session
     * @return 菜单资源的json
     */
    @Deprecated
    @RequestMapping(value = "/user/menu",method = RequestMethod.GET)
    public @ResponseBody List<MenuVo> getMenu (HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (Objects.isNull(user)) {
            return null;
        }
        List<MenuVo> menus = resourceService.listMenuByUser(user.getId());
        return menus;
    }

    /**
     * 通过选择的角色 获取角色相应的 资源
     * @param roid
     * @param mav
     * @return
     */
    @Authorize(ResourceConstant.SYS_ROLE_ALLOC_RESOURCE)
    @RequestMapping(value = "/resource/role/{roid}", method = RequestMethod.GET)
    public ModelAndView getResByRole(@PathVariable("roid") Integer roid, ModelAndView mav) {
        List<ResourceVo> resources = this.resourceService.listResVoByRole(roid);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Role role = this.roleService.getById(roid);
        mav.addObject("resources", json);
        mav.addObject("_role", role);
        mav.setViewName("sysmgr/allocres.jsp");
        return mav;
    }

    /**
     * 通过 ajax 和 角色id获取 相应的角色资源
     * @param roid
     * @param ids
     * @return
     */
    @RequestMapping(value = "/resource/role/{roid}", method = RequestMethod.PUT)
    public @ResponseBody String updateResByRole(@PathVariable("roid") Integer roid, Integer[] ids) {
        this.resourceService.updateByIds(roid, ids);
        return "success";
    }

}
