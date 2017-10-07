package com.crmsys.service;

import com.crmsys.po.City;
import com.crmsys.service.base.BaseService;
import com.crmsys.vo.CityVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public interface CityService extends BaseService<City> {
    /**
     * 通过父级 id寻找孩子 ajax
     * @param cityId
     * @return
     */
    List<CityVo> listCityByParentId(Integer cityId);

    /**
     * 通过级别获取城市（省市县）
     * @param lv
     * @return
     */
    List<CityVo> listByLevel(Integer lv);
}
