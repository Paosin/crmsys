package com.crmsys.controller;

import com.crmsys.service.CityService;
import com.crmsys.vo.CityVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
@Controller
public class CityController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/cities/{parentId}",method = RequestMethod.GET)
    public @ResponseBody
    List<CityVo> getByParent(@PathVariable("parentId") Integer cityId) {
        return this.cityService.listCityByParentId(cityId);
    }
}
