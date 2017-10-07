package com.crmsys.dao.base.impl;

import com.crmsys.dao.base.BaseDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */

@SuppressWarnings("all")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    protected Class<T> clazz;

    public BaseDaoImpl() {
        // 处理T的原始类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 得到类上面的尖括号中的数据类型 是一个数组
        Type[] types = type.getActualTypeArguments();
        if (types.length != 1) {
            throw new IllegalArgumentException("参数不能为空");
        }

        this.clazz = (Class<T>) types[0];
    }


    protected Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T t) {
        this.getSession().save(t);
    }

    @Override
    public void removeById(Integer id) {
        this.getSession().delete(this.getById(id));
    }

    @Override
    public void update(T t) {
        this.getSession().update(t);
    }

    @Override
    public List<T> listAll() {
        Criteria criteria = this.getSession().createCriteria(clazz);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public T getById(Integer id) {
        return (T) this.getSession().get(clazz, id);
    }

    @Override
    public void removeAll(Integer[] ids) {
        if (ids == null || ids.length < 1) {
            return;
        }
        this.getSession().createQuery("delete from " + clazz.getSimpleName() + " where id in (:ids)").setParameterList("ids", ids).executeUpdate();
        this.getSession().flush();
    }
}
