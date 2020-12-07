package com.kgc.dao;

import com.kgc.pojo.AppCategory;
import com.kgc.pojo.AppInfo;
import com.kgc.pojo.BackUser;
import com.kgc.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackUserMapper {
    /**
     * 登录
     */
    BackUser finduser(@Param("UserCode") String UserCode,
                          @Param("UserPassword") String UserPassword);
    /**
     * 按条件查询信息
     *
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<AppInfo> findAppInfocondition(
            @Param("currentPageNo") Integer currentPageNo,
            @Param("pageSize") Integer pageSize,
            @Param("querySoftwareName") String querySoftwareName,
            @Param("queryStatus") Integer queryStatus,
            @Param("queryFlatformId") Integer queryFlatformId,
            @Param("queryCategoryLevel1") Integer queryCategoryLevel1,
            @Param("queryCategoryLevel2") Integer queryCategoryLevel2,
            @Param("queryCategoryLevel3") Integer queryCategoryLevel3);
    /**
     * 统计总数
     *
     * @return
     */
    public int Count(@Param("querySoftwareName") String querySoftwareName,
                     @Param("queryStatus") Integer queryStatus,
                     @Param("queryFlatformId") Integer queryFlatformId,
                     @Param("queryCategoryLevel1") Integer queryCategoryLevel1,
                     @Param("queryCategoryLevel2") Integer queryCategoryLevel2,
                     @Param("queryCategoryLevel3") Integer queryCategoryLevel3);

    /**
     * 查询所属平台
     */
    public List<DataDictionary> findptname();

    /**
     * 加载分类
     */
    public List<AppCategory> findtwotype(@Param("pid") Integer pid);

    /**
     * 修改审核
     * @param id
     * @return
     */
    public int checksave(@Param("id")Integer id,@Param("status")Integer status);

    /**
     * 修改发布状态
     * @param
     * @return
     */
    public int checksavestatus(@Param("publishStatus")Integer publishStatus,@Param("vid")Integer vid);
}
