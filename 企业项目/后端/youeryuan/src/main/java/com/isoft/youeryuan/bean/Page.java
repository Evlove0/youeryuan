package com.isoft.youeryuan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页类
 * @param <T>
 */
@Data
@AllArgsConstructor
public class Page<T> {
    /**
     * 本页数据
     */
    private List<T> data ;
    /**
     * 页码
     */
    private Integer page ;
    /**
     * 每页记录个数
     */
    private Integer pageSize ;
    /**
     * 总页数
     */
    private Integer pageCount ;
    /**
     * 记录总数
     */
    private Integer count ;
}
