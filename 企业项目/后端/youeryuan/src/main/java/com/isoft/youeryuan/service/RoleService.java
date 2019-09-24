package com.isoft.youeryuan.service;

import com.isoft.youeryuan.entity.Role;

import java.util.List;

public interface RoleService {
    boolean add(String role) ;
    List<Role> getAll();
}
