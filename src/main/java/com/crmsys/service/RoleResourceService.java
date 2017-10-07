package com.crmsys.service;

import com.crmsys.po.RoleResource;
import com.crmsys.service.base.BaseService;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
public interface RoleResourceService extends BaseService<RoleResource> {
    List<RoleResource> listByResIds(Integer[] ids);
}
