package com.kgc.service;

import com.kgc.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppInfoService {
    /**
     * 通过条件进行查询
     */
    public List<AppInfo> getAppInfoList(String querySoftwareName,
                                        Integer queryStatus,
                                        Integer queryFlatformId,
                                        Integer queryCategoryLevel1,
                                        Integer queryCategoryLevel2,
                                        Integer queryCategoryLevel3,
                                        Integer currentPageNo,
                                        Integer pageSize);

    /**
     * 统计总数
     */
    public int getAppInfoCount(String querySoftwareName, Integer queryStatus,
                               Integer queryFlatformId,Integer queryCategoryLevel1,
                               Integer queryCategoryLevel2,Integer queryCategoryLevel3);
    /**
     * 新增App信息
     */
    public boolean addAppInfo(AppInfo appInfo);
    /**
     * 通过APKName进行查询验证
     */
    public int findname(@Param("APKName")String APKName);
    /**
     * 通过id进行查询
     */
    public AppInfo findAppInfoById(Integer pid);

    /**
     * 根据id修改
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

    /**
     * 通过id修改上下架
     */
    public int updatestatus(Integer id);
    public int updatestatuss(Integer id);
}
