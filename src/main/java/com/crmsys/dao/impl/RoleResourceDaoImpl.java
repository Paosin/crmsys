package com.crmsys.dao.impl;

import com.crmsys.dao.RoleResourceDao;
import com.crmsys.dao.base.impl.BaseDaoImpl;
import com.crmsys.po.Resource;
import com.crmsys.po.RoleResource;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
@Repository
public class RoleResourceDaoImpl extends BaseDaoImpl<RoleResource> implements RoleResourceDao {
    @Override
    public List<RoleResource> listByResIds(Integer[] ids) {
        List<Resource> resources = this.getSession().createQuery("FROM Resource r WHERE r.id in (:ids)").setParameterList("ids", ids).list();
        Set<RoleResource> roleResources = new HashSet<>();
        for (Resource resource : resources) {
            roleResources.addAll(resource.getRoleResources());
        }
        List<RoleResource> rs = new ArrayList<>();
        rs.addAll(roleResources);
        return rs;
    }
}
