package com.crmsys.service.base;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
public interface BaseService<T> {
    void save(T t);

    void removeById(Integer id);

    void removeAll(Integer[] ids);

    void update(T t);

    List<T> listAll();

    T getById(Integer id);
}
