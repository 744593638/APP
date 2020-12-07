package com.kgc.dao;

import com.kgc.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    /**
     * 根据用户编码进行验证
     */
    public DevUser getLogin(@Param("devCode") String devCode);

    public DevUser getDevUserByNameAndPassword(@Param("devCode") String devCode, @Param("devPassword") String devPassword);
}
