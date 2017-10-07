package com.crmsys.service.impl;

import com.crmsys.dao.ClientDao;
import com.crmsys.dao.LinkmanDao;
import com.crmsys.po.Client;
import com.crmsys.po.Linkman;
import com.crmsys.service.LinkmanService;
import com.crmsys.service.base.impl.BaseServiceImpl;
import com.crmsys.util.BeanCopyUtils;
import com.crmsys.vo.LinkmanVo;

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
public class LinkmanServiceImpl extends BaseServiceImpl<Linkman> implements LinkmanService {
    //    @Transactional(propagation = Propagation.REQUIRED)
    private LinkmanDao linkmanDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    public void setLinkmanDao(LinkmanDao linkmanDao) {
        super.setBaseDao(linkmanDao);
        this.linkmanDao = linkmanDao;
    }

    @Override
    public List<LinkmanVo> listByClientToVo(Integer clientId) {
        Client client = this.clientDao.getById(clientId);
        return BeanCopyUtils.copyProperties(LinkmanVo.class, new ArrayList<>(client.getLinkmen()));
    }

    @Override
    public List<LinkmanVo> listAllToVo() {
        return BeanCopyUtils.copyProperties(LinkmanVo.class, this.linkmanDao.listAll());
    }

    @Override
    public LinkmanVo getByIdToVo(Integer linkId) {
        return BeanCopyUtils.copyProperties(LinkmanVo.class, this.linkmanDao.getById(linkId));
    }
}
