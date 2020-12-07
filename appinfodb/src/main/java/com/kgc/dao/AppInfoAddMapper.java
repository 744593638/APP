package com.kgc.dao;

import com.kgc.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppInfoAddMapper {
    /**
     * 根据条件进行查询
     */
    public List<AppInfo> getAppInfoList(@Param("querySoftwareName")String querySoftwareName,
                                        @Param("queryStatus")Integer queryStatus,
                                        @Param("queryFlatformId")Integer queryFlatformId,
                                        @Param("queryCategoryLevel1")Integer queryCategoryLevel1,
                                        @Param("queryCategoryLevel2")Integer queryCategoryLevel2,
                                        @Param("queryCategoryLevel3")Integer queryCategoryLevel3,
                                        @Param("currentPageNo")Integer currentPageNo,
                                        @Param("pageSize")Integer pageSize);

    /**
     * 统计总数
     */
    public int getAppInfoCount(@Param("querySoftwareName")String querySoftwareName,
                               @Param("queryStatus")Integer queryStatus,
                               @Param("queryFlatformId")Integer queryFlatformId,
                               @Param("queryCategoryLevel1")Integer queryCategoryLevel1,
                               @Param("queryCategoryLevel2")Integer queryCategoryLevel2,
                               @Param("queryCategoryLevel3")Integer queryCategoryLevel3
                               )throws Exception;
    /**
     * 新增版本信息
     */
    public int AddAppInfo(AppInfo appInfo);
    /**
     * 根据APKName查询，进行APK验证
     */
    public int findname(@Param("APKName")String APKName);
    /**
     * 通过id进行查询
     */
    public AppInfo findAppInfoById(@Param("id")Integer id);

    /**
     * 修改
     */
    public int updateAppInfo(AppInfo appInfo);

    /**
     * 根据id进行删除
     */
    public int deleteappinfo(@Param("id")Integer id);

    /**
     * 根据id删除所有版本信息
     */
    public int deleteappversion(@Param("id")Integer id);

    /*
     * 通过id修改上下架  两个
     */
    public int updatestatus(@Param("id")Integer appid);
    public int updatestatuss(@Param("id")Integer appid);

}
