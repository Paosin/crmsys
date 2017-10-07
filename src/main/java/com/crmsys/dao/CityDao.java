package com.crmsys.dao;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.po.City;
import com.crmsys.vo.CityVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public interface CityDao extends BaseDao<City> {
    /**
     * 通过级别获取城市（省市县）
     * @param lv
     * @return
     */
    List<CityVo> listByLevel(Integer lv);
}
