package com.kgc.service.impl;

import com.kgc.dao.AppVersionMapper;
import com.kgc.pojo.AppVersion;
import com.kgc.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper appVersionMapper;
    //查询历史版本
    @Override
    public List<AppVersion> findappversion(Integer appid) {
        return appVersionMapper.findappversion(appid);
    }
    //添加
    @Override
    public boolean addAppVersion(AppVersion appVersion) {
        boolean flag=false;
        if (appVersionMapper.addversion(appVersion)>0){
            flag=true;
        }
        return flag;
    }
    //获取最新版本号
    @Override
    public AppVersion findappver(Integer appid) {
        return appVersionMapper.findappver(appid);
    }

    //更新最新版本号
    @Override
    public int updateappinfo(Integer versionId, Integer appid) {
        return appVersionMapper.updateappinfo(versionId,appid);
    }
    @Override
    public int updateapkinfo(AppVersion appver) {
        return appVersionMapper.updateapkinfo(appver);
    }
}
