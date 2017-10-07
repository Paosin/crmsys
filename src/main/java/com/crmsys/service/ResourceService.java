package com.crmsys.service;

import com.crmsys.po.Resource;
import com.crmsys.service.base.BaseService;
import com.crmsys.vo.FeatureVo;
import com.crmsys.vo.MenuVo;
import com.crmsys.vo.ResourceVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
public interface ResourceService extends BaseService<Resource> {
    /**
     * 通过当前用户 id获取对应的菜单，即数据库crm_resource中type=1的数据
     *
     * @param uid
     * @return
     */
    List<MenuVo> listMenuByUser(Integer uid);

    /**
     * 通过角色获取对应的资源Vo
     *
     * @param roid 角色 id
     * @return
     */
    List<ResourceVo> listResVoByRole(Integer roid);

    /**
     * 通过角色获取对应的资源
     *
     * @param roid 角色 id
     * @return
     */
    List<Resource> listByRole(Integer roid);

    /**
     * 通过角色 id和 被选定的资源数组来更改当前角色所拥有的资源
     *
     * @param roid
     * @param ids
     */
    void updateByIds(Integer roid, Integer[] ids);

    /**
     * 根据用户 id获取对应的功能
     *
     * @param uid
     * @return
     */
    List<FeatureVo> listByUser(Integer uid);
}
