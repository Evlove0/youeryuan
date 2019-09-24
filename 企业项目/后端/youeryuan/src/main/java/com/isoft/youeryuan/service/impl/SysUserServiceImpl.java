package com.isoft.youeryuan.service.impl;

import com.isoft.youeryuan.dao.SysUserDao;
import com.isoft.youeryuan.entity.SysUser;
import com.isoft.youeryuan.service.SysUserService;
import com.isoft.youeryuan.util.MD5Tools;
import com.isoft.youeryuan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao ;
    @Override
    public SysUser loginCheck(String name, String pass) {
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(pass)) {
            return null;
        }
        pass = MD5Tools.MD5(pass) ;
        return sysUserDao.loginCheck(name , pass) ;
    }


    @Override
    public int repass(Integer id, String oldPass, String newPass) {
        if(id == null || id < 1) {
            return REPASS_INFONULL;
        }
        if(StringUtil.isEmpty(oldPass) || StringUtil.isEmpty(newPass)) {
            return  REPASS_INFONULL ;
        }
        oldPass = MD5Tools.MD5(oldPass) ;
        if(sysUserDao.checkPass(id , oldPass) < 1) {
            return  REPASS_OLDPASSERROR ;
        }
        newPass = MD5Tools.MD5(newPass) ;
        return sysUserDao.repass(id , newPass) > 0 ? REPASS_OK : REPASS_OTHERERROR ;
    }

    @Override
    public boolean rephoto(Integer id, String photoUrl) {
        if(id == null || id < 1) {
            return false;
        }
        if(StringUtil.isEmpty(photoUrl)) {
            return false ;
        }
        return sysUserDao.rephoto(id , photoUrl) > 0 ? true : false;
    }

    @Override
    public boolean add(String name, String pass, String role) {
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(pass) || StringUtil.isEmpty(role)) {
            return false;
        }
        pass = MD5Tools.MD5(pass) ;
        return sysUserDao.add(name , pass , role) > 0 ? true : false ;
    }

    @Override
    public boolean del(Integer id) {
        if(id == null || id < 1) {
            return false;
        }
        return sysUserDao.delById(id) > 0 ? true : false ;
    }

    @Override
    public List<SysUser> getAll() {
        return sysUserDao.getAll();
    }
}
