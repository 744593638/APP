package com.kgc.service;

import com.kgc.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryService {
    //一级分类
    public List<AppCategory> findtype();
    //二级分类
    public List<AppCategory> findtwotype(@Param("pid")Integer pid);
}
