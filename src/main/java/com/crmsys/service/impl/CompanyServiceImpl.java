package com.crmsys.service.impl;

import com.crmsys.dao.CompanyDao;
import com.crmsys.po.Company;
import com.crmsys.service.CompanyService;
import com.crmsys.service.base.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paosin Von Scarlet on 2017/10/2.
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        super.setBaseDao(companyDao);
        this.companyDao = companyDao;
    }
}
