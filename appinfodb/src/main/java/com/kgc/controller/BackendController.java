package com.kgc.controller;

import com.kgc.pojo.*;
import com.kgc.service.AppCategoryService;
import com.kgc.service.AppInfoService;
import com.kgc.service.AppVersionService;
import com.kgc.service.BackUserService;
import com.kgc.tools.Constants;
import com.kgc.tools.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BackendController {

    @Autowired
    private BackUserService backUserService;
    @Autowired
    private AppCategoryService appCategoryService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private AppVersionService appVersionService;

    // 跳转后台登录界面
    @RequestMapping("/manager/login")
    public String backenduser() {
        System.out.println("进入了后台登录界面=============");
        return "/backendlogin";
    }

    /*
     * 登录
     */
    @RequestMapping("dologin1")
    public String dologin(String userCode, String userPassword,
                          HttpServletRequest request, Model model,HttpSession session) {
        System.out.println("进入了登录处理=============");
        BackUser backuser = backUserService.finduser(userCode, userPassword);
        session.setAttribute("userSession",backuser);
        System.out.println(userCode + "," + userPassword);
        if (backuser != null) {
            request.getSession().setAttribute(Constants.USER_SESSION, backuser);
            return "/backend/main";
        } else {
            model.addAttribute("error", "账号或密码错误");
            return "/backendlogin";
        }
    }
    /**
     * 注销
     */

    @RequestMapping("/manager/logout")
    public String dologin(HttpSession session) {
        System.out.println("进入注销方法===============");
        session.invalidate();
        return "redirect:/index.jsp";
    }

    /**
     * app审核
     */
    @RequestMapping("/manager/backend/app/list")
    public String applist(Model model, Integer pid, Integer pageIndex,
                          String querySoftwareName, Integer queryStatus,
                          Integer queryFlatformId, Integer queryCategoryLevel1,
                          Integer queryCategoryLevel2, Integer queryCategoryLevel3) {
        // 所属平台
        int currentPageNo = 1;
        if (null != pageIndex) {
            currentPageNo = pageIndex;
        }
        Integer pageSize = Constants.pageSize;
        int totalCount = backUserService.Count(querySoftwareName, queryStatus,
                queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
                queryCategoryLevel3);
        PageSupport pages = new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        // 控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }
        List<DataDictionary> listdata = backUserService.findptname();
        List<AppInfo> infoList = backUserService.findAppInfocondition((currentPageNo - 1)
                        * pageSize, pageSize, querySoftwareName, queryStatus,
                queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
                queryCategoryLevel3);
        model.addAttribute("appInfoList", infoList);
        model.addAttribute("flatFormList", listdata);
        model.addAttribute("pages", pages);

        List<AppCategory> categoryLevel2List = new ArrayList<AppCategory>();

        if (queryCategoryLevel1 != null) {
            categoryLevel2List = appCategoryService.findtwotype(queryCategoryLevel1);
        }

        List<AppCategory> categoryLevel3List = new ArrayList<AppCategory>();
        if (queryCategoryLevel2 != null) {
            categoryLevel3List = appCategoryService.findtwotype(queryCategoryLevel2);
        }
        model.addAttribute("categoryLevel2List", categoryLevel2List);
        model.addAttribute("categoryLevel3List", categoryLevel3List);


        // 加载分类
        List<AppCategory> listapp = backUserService.findtwotype(pid);
        model.addAttribute("categoryLevel1List", listapp);
        model.addAttribute("querySoftwareName",querySoftwareName);
        model.addAttribute("flatFormList",listdata);
        model.addAttribute("queryFlatformId",queryFlatformId);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        return "/backend/applist";
    }

    /**
     * 二级
     *
     * @return
     */
    @RequestMapping("/manager/backend/app/categorylevellist")
    @ResponseBody
    public Object applist1(Integer pid) {

        System.out.println("进入二级=============");
        System.out.println(pid);
        List<AppCategory> listapp1 = backUserService.findtwotype(pid);
        return listapp1;

    }

    /**
     * 三级
     *
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping("/manager/backend/app/listlist1")
    @ResponseBody
    public Object applist3(Model model, Integer pid) {
        System.out.println("进入三级=============");
        System.out.println(pid);
        List<AppCategory> listapp2 = backUserService.findtwotype(pid);
        return listapp2;

    }

    // 跳转到审核基本信息界面
    @RequestMapping("/manager/backend/app/check")
    public String appinfoupdate(Integer aid, Integer vid, Model model) {
        System.out.println("进入了跳转修改app页面===============");
        System.out.println(aid);
        // 查看app信息
        AppInfo app = appInfoService.findAppInfoById(aid);
        model.addAttribute("appInfo", app);
        model.addAttribute("id", aid);
        model.addAttribute("vid", vid);
        // 查看最新app版本信息
        AppVersion appversion = appVersionService.findappver(aid);
        model.addAttribute("appVersion", appversion);
        return "/backend/appcheck";
    }
    //根据单击的按钮修改是否审核
    @RequestMapping("/manager/backend/app/checksave")
    public String appinfoupdate1(Integer id, Integer vid, Integer status,
                                 Model model) {
        System.out.println("进入了跳转修改app页面===============");
        if (backUserService.checksave(id, status) > 0) {
            if (status == 2) {
                backUserService.checksavestatus(2, vid);
            } else if (status == 1){
                backUserService.checksavestatus(1, vid);
            }else {
                backUserService.checksavestatus(0, vid);
            }
            return "redirect:/manager/backend/app/list";
        } else {
            return "redirect:/manager/backend/app/check/?aid=" + id;
        }

    }
}
