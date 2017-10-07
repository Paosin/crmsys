package com.crmsys.service;

import com.crmsys.po.Client;
import com.crmsys.service.base.BaseService;
import com.crmsys.vo.ClientVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public interface ClientService extends BaseService<Client> {
    /**
     * 获取所有客户vo
     * @return
     */
    List<ClientVo> listAllByVo();

    /**
     * 根据id获取客户vo
     * @param clientId
     * @return
     */
    ClientVo getByClientId(Integer clientId);

    /**
     * 更新客户信息
     * @param cid 客户id
     * @param client 携带客户信息
     * @param city 城市编号
     */
    void updateByClent(Integer cid, Client client, Integer city);

    /**
     * 根据一个条件模糊搜索数据
     * @param param 值
     * @param condition 条件
     * @return
     */
    List<ClientVo> fuzzySearch(String param, Integer condition);
}
