package com.kgc.service.impl;

import com.kgc.dao.BackUserMapper;
import com.kgc.pojo.AppCategory;
import com.kgc.pojo.AppInfo;
import com.kgc.pojo.BackUser;
import com.kgc.pojo.DataDictionary;
import com.kgc.service.BackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackUserServiceImpl implements BackUserService {
    @Autowired
    private BackUserMapper backUserMapper;
    /**
     * 登录
     */
    @Override
    public BackUser finduser(String UserCode, String UserPassword) {
        return backUserMapper.finduser(UserCode,UserPassword);
    }

    public List<AppInfo> findAppInfocondition(Integer currentPageNo,
                                              Integer pageSize, String querySoftwareName, Integer queryStatus,
                                              Integer queryFlatformId, Integer queryCategoryLevel1,
                                              Integer queryCategoryLevel2, Integer queryCategoryLevel3) {
        return backUserMapper.findAppInfocondition(currentPageNo, pageSize,
                querySoftwareName, queryStatus, queryFlatformId,
                queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
    }

    public int Count(String querySoftwareName, Integer queryStatus,
                     Integer queryFlatformId, Integer queryCategoryLevel1,
                     Integer queryCategoryLevel2, Integer queryCategoryLevel3) {
        return backUserMapper.Count(querySoftwareName, queryStatus, queryFlatformId,
                queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
    }

    /**
     * 查询所属平台
     */
    public List<DataDictionary> findptname() {

        return backUserMapper.findptname();
    }

    /**
     * 加载分类
     */

    public List<AppCategory> findtwotype(Integer pid) {
        return backUserMapper.findtwotype(pid);
    }

    /**
     * 修改审核
     * @param id
     * @return
     */

    public int checksave(Integer id,Integer status) {
        return backUserMapper.checksave(id,status);
    }

    /**
     * 修改发布状态
     * @param id
     * @return
     */
    public int checksavestatus(Integer publishStatus, Integer vid) {
        return backUserMapper.checksavestatus(publishStatus, vid);
    }

}
