package com.crmsys.service.impl;

import com.crmsys.dao.CityDao;
import com.crmsys.po.City;
import com.crmsys.service.CityService;
import com.crmsys.service.base.impl.BaseServiceImpl;
import com.crmsys.util.BeanCopyUtils;
import com.crmsys.vo.CityVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
@Service
@Transactional(readOnly = true)
public class CityServiceImpl extends BaseServiceImpl<City> implements CityService {
    //@Transactional(propagation = Propagation.REQUIRED)
    private CityDao cityDao;

    @Autowired
    public void setCityDao(CityDao cityDao) {
        super.setBaseDao(cityDao);
        this.cityDao = cityDao;
    }

    @Override
    public List<CityVo> listCityByParentId(Integer cityId) {
        City city = this.cityDao.getById(cityId);
        List<City> temp = new ArrayList<>(city.getCityies());
        return BeanCopyUtils.copyProperties(CityVo.class, temp);
    }

    @Override
    public List<CityVo> listByLevel(Integer lv) {
        return this.cityDao.listByLevel(lv);
    }

}
