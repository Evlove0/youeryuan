package com.isoft.youeryuan.service;

import com.isoft.youeryuan.bean.Page;
import com.isoft.youeryuan.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * 添加员工
     * 不需要提供 id , tid ,departure , state=2
     */
    boolean add(Employee employee) ;

    /**
     * 员工离职
     */
    boolean departure(Integer id) ;

    /**
     * 员工从试用期修改为正式员工
     */
    boolean changeState(Integer id) ;
    /**
     * 更新
     */
    boolean update(Integer id , String email , String mobile , String address , String photo) ;

    /**
     * 按照条件，分页查询数据
     * @param page      页码
     * @param pageSize  每页记录个数
     * @param role       0-教工，1-职工，null-忽略
     * @param gender     男，女，null-忽略
     * @param nameKey    名字关键字，null-忽略
     * @param mobileKey  电话关键字，null-忽略
     * @param state      0-在职，1-离职，2-试用，null-忽略
     */
    Page<Employee> search(Integer page , Integer pageSize , Integer role , String gender , String nameKey , String mobileKey , Integer state);

    /**
     * 查看个人信息详情
     */
    Employee getById(String tid) ;

    /**
     * 获取所有教师id及名
     */
    List<Employee> getAllTeacher();
}
