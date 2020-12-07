package com.kgc.service;

import com.kgc.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

public interface AppVersionService {

    //查询历史版本
    List<AppVersion> findappversion(@Param("appid")Integer appid);
    //添加
    public boolean addAppVersion(AppVersion appVersion);
    //获取最新版本
    AppVersion findappver(@Param("appid")Integer appid);
    //更新最新版本信息
    int updateappinfo(@Param("versionId")Integer versionId,@Param("appid")Integer appid);
    //修改app
    int updateapkinfo(AppVersion appver);
}
