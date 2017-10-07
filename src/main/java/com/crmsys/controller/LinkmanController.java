package com.crmsys.controller;

import com.crmsys.dao.ClientDao;
import com.crmsys.po.Client;
import com.crmsys.po.Linkman;
import com.crmsys.service.ClientService;
import com.crmsys.service.LinkmanService;
import com.crmsys.vo.ClientVo;
import com.crmsys.vo.LinkmanVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
@Controller
public class LinkmanController {
    @Autowired
    private LinkmanService linkmanService;

    @Autowired
    private ClientService clientService;

    /**
     * 跳转到添加联系人页面
     *
     * @param clientId
     * @param mav
     * @return
     */
    @RequestMapping(value = "/linkman/add", method = RequestMethod.GET)
    public ModelAndView toAdd(Integer clientId, ModelAndView mav) {
        if (clientId != null && clientId != 0) {
            Client client = clientService.getById(clientId);
            mav.addObject("client", client);
        }else{
            List<ClientVo> clients = clientService.listAllByVo();
            mav.addObject("clients", clients);
        }
        mav.setViewName("client/addlinkman.jsp");
        return mav;
    }

    /**
     * 获取所有的联系人
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/linkmen", method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        List<LinkmanVo> rs = this.linkmanService.listAllToVo();
        mav.addObject("linkmen", rs);
        mav.setViewName("client/linkman.jsp");
        return mav;
    }

    /**
     * 通过客户获取相应的联系人列表
     *
     * @param clientId
     * @param mav
     * @return
     */
    @RequestMapping(value = "/linkmen/client/{clientId}", method = RequestMethod.GET)
    public ModelAndView listByClient(@PathVariable("clientId") Integer clientId, ModelAndView mav) {
        ClientVo client = this.clientService.getByClientId(clientId);
        List<LinkmanVo> rs = this.linkmanService.listByClientToVo(clientId);

        mav.addObject("client", client);
        mav.addObject("linkmen", rs);
        mav.setViewName("client/linkman.jsp");
        return mav;
    }


    /**
     * 通过ajax获取一个联系人
     *
     * @param linkId
     * @return
     */
    @RequestMapping(value = "/linkman/json/{linkId}", method = RequestMethod.GET)
    public @ResponseBody
    LinkmanVo getByAjax(@PathVariable("linkId") Integer linkId) {
        return this.linkmanService.getByIdToVo(linkId);
    }


    /**
     * 删除一个
     *
     * @param linkId
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/linkman/{linkId}", method = RequestMethod.DELETE)
    public String del(@PathVariable("linkId") Integer linkId,
                      Integer clientId) {
        this.linkmanService.removeById(linkId);
        if (clientId == null || clientId == 0) {
            return "redirect:/linkmen";
        }
        return "redirect:/linkmen/client/" + clientId;
    }

    /**
     * 更新一个
     *
     * @param linkId
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/linkman/{linkId}", method = RequestMethod.PUT)
    public String update(@PathVariable("linkId") Integer linkId,
                         Integer clientId,
                         Linkman linkman) {
        Linkman lm = this.linkmanService.getById(linkId);

        if (clientId == null || clientId == 0) {
            return "redirect:/linkmen";
        }
        return "redirect:/linkmen/client/" + clientId;
    }

    /**
     * 插入一个
     *
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/linkman", method = RequestMethod.POST)
    public String save(Integer clientId,
                       Linkman linkman) {
        Client client = this.clientService.getById(clientId);
        linkman.setClient(client);
        this.linkmanService.save(linkman);
        return "redirect:/linkmen/client/" + clientId;
    }

    /**
     * 删除很多
     *
     * @param clientId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/linkmen", method = RequestMethod.DELETE)
    public String removeAll(Integer clientId,
                            Integer[] ids) {
        this.linkmanService.removeAll(ids);
        if (clientId == null || clientId == 0) {
            return "redirect:/linkmen";
        }
        return "redirect:/linkmen/client/" + clientId;
    }


}
