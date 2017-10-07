package com.crmsys.dao;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.po.Resource;
import com.crmsys.po.RoleResource;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
public interface RoleResourceDao extends BaseDao<RoleResource> {
    List<RoleResource> listByResIds(Integer[] ids);
}
