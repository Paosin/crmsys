package com.crmsys.dao.impl;

import com.crmsys.dao.CityDao;
import com.crmsys.dao.base.impl.BaseDaoImpl;
import com.crmsys.po.City;
import com.crmsys.vo.CityVo;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
@Repository
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao {

    @Override
    public List<CityVo> listByLevel(Integer lv) {
        return this.getSession().createQuery("from City c where c.level= ? ").setParameter(0, lv).list();
    }
}
