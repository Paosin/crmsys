package com.crmsys.controller;

import com.crmsys.po.City;
import com.crmsys.po.Client;
import com.crmsys.po.User;
import com.crmsys.service.CityService;
import com.crmsys.service.ClientService;
import com.crmsys.service.UserService;
import com.crmsys.vo.CityVo;
import com.crmsys.vo.ClientVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;


    /**
     * 跳转到添加页面
     * @param mav
     * @return
     */
    @RequestMapping(value = "/client/add")
    public ModelAndView toAdd(ModelAndView mav) {
        List<CityVo> province = cityService.listByLevel(0);
        mav.addObject("province", province);
        mav.setViewName("client/addclient.jsp");
        return mav;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ModelAndView listAllClient(ModelAndView mav) {
        List<ClientVo> clients = this.clientService.listAllByVo();
        List<CityVo> province = cityService.listByLevel(0);
        mav.addObject("province", province);
        mav.addObject("clients", clients);
        mav.setViewName("client/index.jsp");
        return mav;
    }

    /**
     * 模糊搜索客户，并返回 json
     * @return
     */
    @RequestMapping(value = "/clients/json", method = RequestMethod.GET)
    public @ResponseBody List<ClientVo> listAllClientToJson(Integer condition,String param) {
        return this.clientService.fuzzySearch(param,condition);
    }

    /**
     * 根据客户 id获取对应客户
     *
     * @param cid 客户id
     * @return
     */
    @RequestMapping(value = "/client/{cid}", method = RequestMethod.GET)
    public @ResponseBody
    ClientVo getByClientId(@PathVariable Integer cid) {
        return this.clientService.getByClientId(cid);
    }

    @RequestMapping(value = "/client",method = RequestMethod.POST)
    public String saveClient(HttpSession session,Client client, Integer[] cities) {
        Integer uid = ((User) session.getAttribute("user")).getId();

        User user = userService.getById(uid);

        Integer cityId = 0;
        for (Integer c : cities) {
            if (c != 0)
                cityId = c;
        }
        City city = this.cityService.getById(cityId);
        client.setRegisterDate(new Date(System.currentTimeMillis()));
        client.setUser(user);
        client.setCity(city);
        this.clientService.save(client);
        return "redirect:/clients";
    }

    /**
     * 根据客户 id删除对应客户
     *
     * @param cid
     * @return
     */
    @RequestMapping(value = "/client/{cid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer cid) {
        this.clientService.removeById(cid);
        return "redirect:/clients";
    }

    /**
     * 删除一组数据
     * @param ids
     * @return
     */
    @RequestMapping(value = "/clients",method = RequestMethod.DELETE)
    public String deleteAll(Integer[] ids) {
        this.clientService.removeAll(ids);
        return "redirect:/clients";
    }

    /**
     * 更新一个客户
     *
     * @param cid
     * @return
     */
    @RequestMapping(value = "/client/{cid}", method = RequestMethod.PUT)
    public String update(@PathVariable Integer cid, Client client, Integer[] cities) {
        Integer city = 0;
        for (Integer c : cities) {
            if (c != 0)
                city = c;
        }
        this.clientService.updateByClent(cid, client, city);
        return "redirect:/clients";
    }
}
