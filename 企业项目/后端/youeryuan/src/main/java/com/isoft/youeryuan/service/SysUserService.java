package com.isoft.youeryuan.service;

import com.isoft.youeryuan.dao.SysUserDao;
import com.isoft.youeryuan.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
    针对系统用户管理操作
 */
public interface SysUserService {

    /**
     * 登录校验
     * @param name
     * @param pass
     * @return
     */
    SysUser loginCheck(String name , String pass) ;

    /**
     * 修改密码
     */
    public static final int REPASS_INFONULL = 1 ;
    public static final int REPASS_OLDPASSERROR = 2 ;
    public static final int REPASS_OTHERERROR = 3 ;
    public static final int REPASS_OK = 0 ;
    int repass(Integer id , String oldPass , String newPass) ;

    /**
     * 修改头像
     */
    boolean rephoto(Integer id , String photoUrl) ;

    /**
     * 添加管理员
     */
    boolean add(String name , String pass , String role) ;

    /**
     * 删除管理员
     */
    boolean del(Integer id) ;

    /**
     * 获取所有信息
     */
    List<SysUser> getAll();
}
