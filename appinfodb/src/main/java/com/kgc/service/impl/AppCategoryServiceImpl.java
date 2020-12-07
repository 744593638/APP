package com.kgc.service.impl;

import com.kgc.dao.AppInfoMapper;
import com.kgc.pojo.AppCategory;
import com.kgc.service.AppCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {

    @Resource
    private AppInfoMapper appInfoMapper;

    //一级分类
    @Override
    public List<AppCategory> findtype() {
        List<AppCategory> app= appInfoMapper.findtype();
        return app;
    }

    //二级分类
    @Override
    public List<AppCategory> findtwotype(Integer pid) {
        List<AppCategory> twoapp= appInfoMapper.findtwotype(pid);
        return twoapp;
    }


}
