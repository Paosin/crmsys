package com.crmsys.dao;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.po.Resource;
import com.crmsys.vo.FeatureVo;
import com.crmsys.vo.MenuVo;
import com.crmsys.vo.ResourceVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
public interface ResourceDao extends BaseDao<Resource> {
    /**
     * 通过用户 id获取所有用户资源
     * @param uid
     * @return
     */
    List<Integer> listByUser(Integer uid);

    /**
     * 通过用户 id获取所有的用户功能
     * 即 type=2 的资源
     * @param uid
     * @return
     */
    List<Resource> listFeatureByUser(Integer uid);

    /**
     * 通过一组资源 id获取资源列表
     *
     * @param ids
     * @return
     */
    List<Resource> listByIds(Integer[] ids);

    /**
     * 根据角色 id查找相应资源
     *
     * @param roid
     * @return
     */
    List<Resource> listByRole(Integer roid);

    /**
     * 修改对应角色 Id 的 所有资源
     * @param roid
     * @param ids
     */
    void updateByRoIdAndIds(Integer roid, Integer[] ids);
}