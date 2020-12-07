package com.kgc.service;

import com.kgc.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    /**
     * 查询app状态
     * @return
     */
    public List<DataDictionary> findtypename();

    /**
     * 查询所属平台
     */
    public List<DataDictionary> findptname();
    /**
     * 通过valueid查询valuename
     */

}
