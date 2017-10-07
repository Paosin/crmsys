package com.crmsys.dao.impl;

import com.crmsys.dao.ResourceDao;
import com.crmsys.dao.base.impl.BaseDaoImpl;
import com.crmsys.po.Resource;
import com.crmsys.po.Role;
import com.crmsys.po.RoleResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Integer> listByUser(Integer uid) {
        List<Integer> resources = jdbcTemplate.queryForList("SELECT " +
                "rec.id " +
                "FROM " +
                "crm_resource rec " +
                "LEFT OUTER JOIN crm_role_resource rr ON rec.id = rr.resource_id " +
                "LEFT OUTER JOIN crm_role ro ON ro.id = rr.role_id " +
                "LEFT OUTER JOIN crm_user_role ur ON ur.role_id = ro.id " +
                "LEFT OUTER JOIN crm_user u ON u.id = ur.user_id " +
                "WHERE " +
                "u.id = ?; ", new Object[]{uid}, Integer.class);

        return resources;
    }

    @Override
    public List<Resource> listFeatureByUser(Integer uid) {
        List<Resource> resources = jdbcTemplate.queryForList("SELECT " +
                "rec.* " +
                "FROM " +
                "crm_resource rec " +
                "LEFT OUTER JOIN crm_role_resource rr ON rec.id = rr.resource_id " +
                "LEFT OUTER JOIN crm_role ro ON ro.id = rr.role_id " +
                "LEFT OUTER JOIN crm_user_role ur ON ur.role_id = ro.id " +
                "LEFT OUTER JOIN crm_user u ON u.id = ur.user_id " +
                "WHERE " +
                "u.id = ? AND rec.type=2; ", new Object[]{uid}, Resource.class);

        return resources;
    }

    @Override
    public void updateByRoIdAndIds(Integer roid, Integer[] ids) {
        List<Integer> resources = jdbcTemplate.queryForList(
                "select crr.resource_id from crm_role_resource crr WHERE crr.role_id  = ?;", new Object[]{roid}, Integer.class);

        List<Integer> resCopy = new ArrayList<>();
        resCopy.addAll(resources);

        List<Integer> idsList = new ArrayList<>(Arrays.asList(ids));
        // 将原有的不需要的资源从数据库删除
        resCopy.removeAll(idsList);

        if (resCopy.size() > 0) {
            NamedParameterJdbcTemplate npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
            Map<String, Object> map = new HashMap<>();
            map.put("roid", roid);
            map.put("ids", resCopy);
            int i = npJdbcTemplate.update("DELETE FROM crm_role_resource WHERE role_id = :roid AND resource_id IN (:ids)", map);
        }

        // 将新加入的资源插入数据库
        idsList.removeAll(resources);

        if (idsList.size() > 0) {
            jdbcTemplate.batchUpdate("INSERT INTO crm_role_resource (role_id, resource_id, enabled) VALUES (?,?,1)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setInt(1, roid);
                    preparedStatement.setInt(2, idsList.get(i));
                }

                @Override
                public int getBatchSize() {
                    return idsList.size();
                }
            });
        }

    }

    @Override
    public List<Resource> listByIds(Integer[] ids) {
        return this.getSession().createQuery("FROM Resource r WHERE r.id in (:ids)").setParameterList("ids", ids).list();
    }

    @Override
    public List<Resource> listByRole(Integer roid) {
        Role role = (Role) this.getSession().get(Role.class, roid);
        List<Resource> rs = new ArrayList<>();
        for (RoleResource roleResource : role.getRoleResources()) {
            rs.add(roleResource.getResource());
        }
        return rs;
    }
}
