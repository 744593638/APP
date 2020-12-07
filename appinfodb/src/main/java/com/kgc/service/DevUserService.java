package com.kgc.service;

import com.kgc.common.ResultObject;
import com.kgc.pojo.DevUser;

public interface DevUserService {
    public ResultObject login(String devCode, String devPassword);
}
