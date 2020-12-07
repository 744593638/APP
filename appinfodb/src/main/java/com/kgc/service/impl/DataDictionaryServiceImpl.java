package com.kgc.service.impl;

import com.kgc.dao.DataDictionaryMapper;
import com.kgc.pojo.DataDictionary;
import com.kgc.service.DataDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> findtypename() {
        List<DataDictionary> list=dataDictionaryMapper.findtypename();
        return list;
    }

    public List<DataDictionary> findptname() {
        List<DataDictionary> listdata=dataDictionaryMapper.findptname();
        return listdata;
    }


}
