package com.isoft.youeryuan.service.impl;

import com.isoft.youeryuan.dao.RoleDao;
import com.isoft.youeryuan.entity.Role;
import com.isoft.youeryuan.service.RoleService;
import com.isoft.youeryuan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao ;
    @Override
    public boolean add(String role) {
        if(StringUtil.isEmpty(role)) {
            return false ;
        }
        return roleDao.add(role) > 0 ? true : false;
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }
}
