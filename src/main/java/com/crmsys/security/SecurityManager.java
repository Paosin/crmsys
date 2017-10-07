package com.crmsys.security;

import com.crmsys.po.Resource;
import com.crmsys.po.Role;
import com.crmsys.po.User;
import com.crmsys.security.annotation.Authorize;
import com.crmsys.service.ResourceService;
import com.crmsys.service.UserService;
import com.sun.org.apache.regexp.internal.RE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Component
public class SecurityManager extends HandlerInterceptorAdapter {

    private static final String SQL_CONSTNANT_QUERY = "SELECT " +
            " res.constant  " +
            " FROM " +
            " crm_resource res " +
            " LEFT OUTER JOIN crm_role_resource ro_res ON res.id = ro_res.resource_id " +
            " LEFT OUTER JOIN crm_role ro ON ro_res.role_id = ro.id " +
            " LEFT OUTER JOIN crm_user_role cur ON ro.id = cur.role_id " +
            " LEFT OUTER JOIN crm_user u ON cur.user_id = u.id  " +
            " WHERE " +
            " u.id = ?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 登录过滤
         */
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/relogin");
            return false;
        }
        Set<Resource> resources = getResourcesSet(user.getId());

        List<Resource> menu = this.setLeftPannel(resources);
        List<String> button = this.setButton(resources);
        request.setAttribute("sysmenu", menu);
        request.setAttribute("sysfeature", button);


        HandlerMethod method = (HandlerMethod) handler;
        Authorize authorize = method.getMethodAnnotation(Authorize.class);
        if (authorize == null) {
            return true;
        }

        if (!authorize.ignore()) {
            String value = authorize.value().toString();
            if (!this.isAuthorized(value, user.getId())) {
                response.sendRedirect("/relogin");
                return false;
            }
        }
        return true;
    }

    /**
     * 判断当前登录的用户是否含有权限注解上的资源唯一标识符
     *
     * @param permission 注解上的资源唯一标识符
     * @param uid        用户 id 用户sql查询
     * @return 是否含有
     */
    private boolean isAuthorized(String permission, Integer uid) {
        if (permission == null || permission.isEmpty()) {
            return false;
        }

        List<String> permissions = jdbcTemplate.queryForList(SQL_CONSTNANT_QUERY, new Object[]{uid}, String.class);
        return permissions.contains(permission);
    }

    /**
     * 获取所有的功能按钮资源，并传递到页面上
     * @param resources
     * @return
     */
    private List<String> setButton(Set<Resource> resources) {
        List<String> rs = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getType() == 2) {
                rs.add(resource.getConstant());
            }
        }
        return rs;
    }

    /**
     * 通过递归得到树形结构，用于显示左侧菜单栏
     * @param resources
     * @return
     */
    private List<Resource> setLeftPannel(Set<Resource> resources) {
        List<Resource> rs = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getType() == 1) {
                rs.add(resource);
            }
        }
        // 将得到的树形数据 通过递归 转换为 树形结构
        return this.transform(rs, null);
    }

    private Set<Resource> getResourcesSet(Integer uid) {
        Set<Resource> resources = new HashSet<>();
        User user = this.userService.getById(uid);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getEnabled() == 0) {
                //如果当前角色不可用
                continue;
            }
            List<Resource> rs = this.resourceService.listByRole(role.getId());
            for (Resource r : rs) {
                if (r.getEnabled() == 1) {
                    resources.add(r);
                }
            }
        }
        return resources;
    }

    private List<Resource> transform(List<Resource> nodes, Integer id) {
        List<Resource> roots = new ArrayList<Resource>();
        for (Resource node : nodes) {
            Resource parent = node.getParent();
            Integer pid = parent == null ? null : parent.getId();
            if (Objects.equals(id, pid)) {
                List<Resource> children = transform(nodes, node.getId());
                node.setResources(new HashSet<Resource>(children));
                roots.add(node);
            }
        }
        return roots;
    }
}
