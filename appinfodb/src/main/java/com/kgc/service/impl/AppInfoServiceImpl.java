package com.kgc.service.impl;

import com.kgc.dao.AppInfoAddMapper;
import com.kgc.pojo.AppInfo;
import com.kgc.service.AppInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoAddMapper appInfoAddMapper;

    /**
     * 根据条件查询
     */
    @Override
    public List<AppInfo> getAppInfoList(String querySoftwareName,
                                        Integer queryStatus,
                                        Integer queryFlatformId,
                                        Integer queryCategoryLevel1,
                                        Integer queryCategoryLevel2,
                                        Integer queryCategoryLevel3,
                                        Integer currentPageNo,
                                        Integer pageSize) {
        List<AppInfo> appInfoList = null;
        appInfoList = appInfoAddMapper.getAppInfoList(querySoftwareName,queryStatus,queryFlatformId,
                                        queryCategoryLevel1,queryCategoryLevel2,
                                        queryCategoryLevel3,currentPageNo,pageSize);

        return appInfoList;
    }








    @Override
    public int getAppInfoCount(String querySoftwareName, Integer queryStatus, Integer queryFlatformId, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3) {
        int count=0;
        try {
            count= appInfoAddMapper.getAppInfoCount(querySoftwareName, queryStatus, queryFlatformId,
                    queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    //新增版本app
    @Override
    public boolean addAppInfo(AppInfo appInfo) {
        boolean flag=false;
        if (appInfoAddMapper.AddAppInfo(appInfo)>0){
            flag=true;
        }
        return flag;
    }

    //进行apk验证
    @Override
    public int findname(String APKName) {
        return appInfoAddMapper.findname(APKName);
    }

    //通过id进行查询
    @Override
    public AppInfo findAppInfoById(Integer pid) {
        return appInfoAddMapper.findAppInfoById(pid);
    }

    //根据id进行修改
    @Override
    public int updateAppInfo(AppInfo appInfo) {
        return appInfoAddMapper.updateAppInfo(appInfo);
    }

    @Override
    public int deleteappinfo(Integer id) {
        return appInfoAddMapper.deleteappinfo(id);
    }

    @Override
    public int deleteappversion(Integer id) {
        return appInfoAddMapper.deleteappversion(id);
    }

    @Override
    public int updatestatus(Integer id) {
        return appInfoAddMapper.updatestatus(id);
    }

    @Override
    public int updatestatuss(Integer id) {
        return appInfoAddMapper.updatestatuss(id);
    }
}
