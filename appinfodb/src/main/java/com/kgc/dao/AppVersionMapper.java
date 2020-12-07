package com.kgc.dao;

import com.kgc.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppVersionMapper {
    /**
     * 查询历史版本
     */
    List<AppVersion> findappversion(@Param("appid")Integer appid);

    /**
     * 添加
     */
    public int addversion(AppVersion appVersion);
    /**
     * 获取最新版本
     */
    AppVersion findappver(@Param("appid")Integer appid);
    /**
     * 更新最新版本
     */
    int updateappinfo(@Param("versionId")Integer versionId,@Param("appid")Integer appid);
    /**
     * 修改版本
     */
    //修改app版本
    int updateapkinfo(AppVersion appver);
}
