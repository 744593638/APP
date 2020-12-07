package com.kgc.service.impl;

import com.kgc.common.ResponseCode;
import com.kgc.common.ResultObject;
import com.kgc.dao.DevUserMapper;
import com.kgc.pojo.DevUser;
import com.kgc.service.DevUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
    @Resource
    private DevUserMapper devUserMapper;
    @Override
    public ResultObject login(String devCode, String devPassword) {
        //验证参数的合法性
        if (StringUtils.isBlank(devCode) || StringUtils.isBlank(devPassword)){
            return ResultObject.resultByErrorMsg(ResponseCode.PARAMETER_IS_NOT_FOUND.getCode(), ResponseCode.PARAMETER_IS_NOT_FOUND.getMsg());
        }
        //TODO 如果需要密码加密,在这里可以密码加密!
        //登录
        DevUser devUser = devUserMapper.getDevUserByNameAndPassword(devCode, devPassword);
        if (devUser.getDevCode() == null){
            return ResultObject.resultByErrorMsg(-1,"密码不正确");
        }
        //登录成功返回用户信息
        return ResultObject.resultBySuccessData(1,"登录成功",devUser);
    }
}
