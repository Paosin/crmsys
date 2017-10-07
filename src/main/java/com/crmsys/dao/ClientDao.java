package com.crmsys.dao;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.po.Client;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public interface ClientDao extends BaseDao<Client> {
    /**
     * 根据一个条件模糊搜索数据
     * @param param 值
     * @param condition 条件
     * @return
     */
    List<Client> fuzzySearch(String param, String condition);
}
