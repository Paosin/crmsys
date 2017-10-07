package com.crmsys.service.impl;

import com.crmsys.dao.CityDao;
import com.crmsys.dao.ClientDao;
import com.crmsys.po.Client;
import com.crmsys.service.ClientService;
import com.crmsys.service.base.impl.BaseServiceImpl;
import com.crmsys.util.BeanCopyUtils;
import com.crmsys.vo.ClientVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
@Service
@Transactional(readOnly = true)
public class ClientServiceImpl extends BaseServiceImpl<Client> implements ClientService {
//    @Transactional(propagation = Propagation.REQUIRED)
    private ClientDao clientDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    public void setClientDao(ClientDao clientDao) {
        super.setBaseDao(clientDao);
        this.clientDao = clientDao;
    }

    @Override
    public List<ClientVo> listAllByVo() {
        return BeanCopyUtils.copyProperties(ClientVo.class, this.clientDao.listAll());
    }

    @Override
    public ClientVo getByClientId(Integer clientId) {
        return BeanCopyUtils.copyProperties(ClientVo.class, this.clientDao.getById(clientId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateByClent(Integer cid, Client client, Integer city) {
        Client c = this.clientDao.getById(cid);
        c.setLegal(client.getLegal());
        c.setLevel(client.getLevel());
        c.setCity(client.getCity());
        c.setName(client.getName());
        c.setNextTime(client.getNextTime());
        c.setRegisterDate(client.getRegisterDate());
        c.setCity(this.cityDao.getById(city));
        this.clientDao.update(c);
    }

    @Override
    public List<ClientVo> fuzzySearch(String param, Integer condition) {
        String rs;
        switch (condition) {
            case 0:
                rs = "name";
                break;
            case 1:
                rs = "city.name";
                break;
            case 2:
                rs = "legal";
                break;
            default:
                return this.listAllByVo();
        }
        if (Objects.isNull(param)||param.isEmpty())
            return this.listAllByVo();
        return BeanCopyUtils.copyProperties(ClientVo.class,this.clientDao.fuzzySearch(param.trim(), rs.trim()));
    }
}
