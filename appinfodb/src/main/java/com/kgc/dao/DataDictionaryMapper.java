package com.kgc.dao;

import com.kgc.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDictionaryMapper {
    //查询app状态
    public List<DataDictionary> findtypename();
    //查询所属平台
    public List<DataDictionary> findptname();
    //通过valueid查询valueName
    //public List<DataDictionary> findd(@Param("valueid") Integer valueid);
}
