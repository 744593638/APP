package com.kgc.controller;

import com.kgc.common.ResultObject;
import com.kgc.service.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/devlogin")

public class DevUserController {

    @Resource
    private DevUserService devUserService;
    /**
     * 跳转
     */
    @RequestMapping("gotologin")
    public String getload(){
        return "devlogin";
    }

    @RequestMapping("/dologout")
    public String dologout(HttpSession session){
        System.out.println("=======注销");
        session.invalidate();
        return "redirect:/index.jsp";
    }

    /**
     * 登录
     */
    @RequestMapping("/dologin.html")
    public ModelAndView dologin(@RequestParam(value = "devCode") String devCode,
                                @RequestParam(value = "devPassword") String devPassword,
                                HttpSession session,
                                HttpServletRequest request){
        ResultObject resultObject = devUserService.login(devCode, devPassword);

        if (resultObject.isSuccess()){
            request.getSession().setAttribute("devUserSession",resultObject.getData());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/developer/main");
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error","账号或密码错误");
            modelAndView.setViewName("/devlogin");
            return modelAndView;
        }
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/dev/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "devlogin";
    }
}
