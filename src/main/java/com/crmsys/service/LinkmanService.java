package com.crmsys.service;

import com.crmsys.po.Client;
import com.crmsys.po.Linkman;
import com.crmsys.service.base.BaseService;
import com.crmsys.vo.LinkmanVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public interface LinkmanService extends BaseService<Linkman> {

    /**
     * 通过客户 id获取联系人集合
     * @param clientId
     * @return
     */
    List<LinkmanVo> listByClientToVo(Integer clientId);

    /**
     * 获取所有转Vo
     * @return
     */
    List<LinkmanVo> listAllToVo();

    /**
     * 通过id获取vo
     * @param linkId
     * @return
     */
    LinkmanVo getByIdToVo(Integer linkId);
}
