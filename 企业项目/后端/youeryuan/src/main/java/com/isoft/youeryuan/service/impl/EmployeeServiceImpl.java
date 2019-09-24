package com.isoft.youeryuan.service.impl;

import com.isoft.youeryuan.bean.Page;
import com.isoft.youeryuan.dao.EmployeeDao;
import com.isoft.youeryuan.entity.Employee;
import com.isoft.youeryuan.service.EmployeeService;
import com.isoft.youeryuan.util.MD5Tools;
import com.isoft.youeryuan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao ;
    @Override
    public boolean add(Employee employee) {
        if(null == employee || StringUtil.isEmpty(employee.getTname())) {
            return false;
        }
        employee.setPass(MD5Tools.MD5("123"));   // 新增员工初始密码默认为123
        return employeeDao.add(employee) > 0 ? true : false ;
    }

    @Override
    public boolean departure(Integer id) {
        if(null == id || id < 1) {
            return false;
        }
        return employeeDao.leave(id) > 0 ?true : false ;
    }

    @Override
    public boolean changeState(Integer id) {
        if(null == id || id < 1) {
            return false;
        }
        return employeeDao.changeState(id , 0) > 0 ?true : false ;
    }

    @Override
    public boolean update(Integer id, String email, String mobile, String address, String photo) {
        if(null == id || id < 1) {
            return false;
        }
        return employeeDao.update(id , email , mobile , address , photo) > 0 ? true : false ;
    }

    @Override
    public Page<Employee> search(Integer page, Integer pageSize, Integer role, String gender, String nameKey, String mobileKey, Integer state) {
        if(null == page || page < 0) {
            page = 1 ;
        }
        if(null == pageSize || pageSize < 1) {
            pageSize = 10 ;
        }
        if(StringUtil.isEmpty(gender)) {
            gender = null ;
        }
        if(StringUtil.isEmpty(nameKey)) {
            nameKey = null ;
        }
        if(StringUtil.isEmpty(mobileKey)) {
            mobileKey = null ;
        }

        List<Employee> data = employeeDao.select((page-1) * pageSize , pageSize , role , gender , nameKey , mobileKey , state) ;
        int count = employeeDao.selectCount(role , gender , nameKey , mobileKey , state) ;
        int pageCount = (int)(Math.ceil(count*1.0 / pageSize));
        return new Page<Employee>(data , page , pageSize , pageCount ,count) ;
    }

    @Override
    public Employee getById(String tid) {
        if(StringUtil.isEmpty(tid)) {
            return null;
        }
        return employeeDao.getByTid(tid);
    }

    @Override
    public List<Employee> getAllTeacher() {
        return employeeDao.getAllTeacher();
    }
}
