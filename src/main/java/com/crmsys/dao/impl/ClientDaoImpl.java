package com.crmsys.dao.impl;

import com.crmsys.dao.ClientDao;
import com.crmsys.dao.base.impl.BaseDaoImpl;
import com.crmsys.po.Client;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
@Repository
public class ClientDaoImpl extends BaseDaoImpl<Client> implements ClientDao {

    @Override
    public List<Client> fuzzySearch(String param, String condition) {
        String hql = "from Client as model where model." + condition + " like '" + param + "%'";
        Query query = this.getSession().createQuery(hql);
        return query.list();
    }
}
