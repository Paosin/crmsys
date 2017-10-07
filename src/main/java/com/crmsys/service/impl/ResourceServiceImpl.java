package com.crmsys.service.impl;

import com.crmsys.dao.ResourceDao;
import com.crmsys.dao.RoleDao;
import com.crmsys.dao.RoleResourceDao;
import com.crmsys.dao.UserDao;
import com.crmsys.po.Resource;
import com.crmsys.po.Role;
import com.crmsys.po.RoleResource;
import com.crmsys.service.ResourceService;
import com.crmsys.service.base.impl.BaseServiceImpl;
import com.crmsys.util.BeanCopyUtils;
import com.crmsys.vo.FeatureVo;
import com.crmsys.vo.MenuVo;
import com.crmsys.vo.ResourceVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    private ResourceDao resourceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public void setResourceDao(ResourceDao resourceDao) {
        super.setBaseDao(resourceDao);
        this.resourceDao = resourceDao;
    }

    @Override
    public List<MenuVo> listMenuByUser(Integer uid) {
        List<Integer> temp = resourceDao.listByUser(uid);
        Integer[] ids = new Integer[temp.size()];
        temp.toArray(ids);
        List<Resource> resources = resourceDao.listByIds(ids);

        List<MenuVo> rs = instanceMenuVo(resources);

        return rs;
    }

    @Override
    public List<ResourceVo> listResVoByRole(Integer roid) {
        List<Resource> all = this.resourceDao.listAll();
        List<Resource> res = this.resourceDao.listByRole(roid);

        List<ResourceVo> rs = new ArrayList<>();
        for (Resource resource : all) {
            ResourceVo vo = new ResourceVo(resource);
            rs.add(vo);
            if (res.contains(resource)) {
                vo.setChecked(true);
            }
        }

        return rs;
    }

    @Override
    public List<Resource> listByRole(Integer roid) {
        return this.resourceDao.listByRole(roid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateByIds(Integer roid, Integer[] ids) {
        this.resourceDao.updateByRoIdAndIds(roid, ids);
    }

    @Override
    public List<FeatureVo> listByUser(Integer uid) {
        List<Resource> resources = this.resourceDao.listFeatureByUser(uid);
        return BeanCopyUtils.copyProperties(FeatureVo.class, resources);
    }

    /**
     * 将Resource初始化为MenuVo
     * @param resources
     * @return 菜单树
     */
    private List<MenuVo> instanceMenuVo(List<Resource> resources) {
        List<MenuVo> tree = new ArrayList<>();
        for (Resource resource : resources) {
            if (Objects.isNull(resource.getParent())) {
                tree.add(new MenuVo(resource));
            }
        }

        for (MenuVo menu : tree) {
            menu.setChildren(getChild(menu.getId(),resources));
        }

        return tree;
    }

    /**
     * 将获得的数据递归生成树结构
     * @param id 父节点id
     * @param rootMenu 该用户下所有的Resource
     * @return 子菜单
     */
    private List<MenuVo> getChild(Integer id, List<Resource> rootMenu) {
        // 子菜单
        List<MenuVo> childList = new ArrayList<>();

        for (Resource menu : rootMenu) {
            if (!Objects.isNull(menu.getParent())) {
                if (menu.getParent().getId().equals(id)) {
                    childList.add(new MenuVo(menu));
                }
            }
        }

        for (MenuVo menu : childList) {
            menu.setChildren(getChild(menu.getId(), rootMenu));
        }

        if (childList.size() == 0) {
            return null;
        }

        return childList;
    }
}
