package com.kgc.controller;

import com.kgc.pojo.AppInfo;
import com.kgc.pojo.AppVersion;
import com.kgc.pojo.DevUser;
import com.kgc.service.AppInfoService;
import com.kgc.service.AppVersionService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
public class AppVersionController {
    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private AppInfoService appInfoService;
    /**
     * 新增app版本
     */
    //跳转到新增app版本页面
    @RequestMapping("/dev/flatform/app/appversionadd")
    public String appVersion(Model model, Integer id,
                             HttpSession session){
        AppInfo appInfo=appInfoService.findAppInfoById(id);
        System.out.println(id);//69
        model.addAttribute("appinfo",appInfo);
        session.setAttribute("id",id);
        //查看app历史版本
        List<AppVersion> appVersionList=appVersionService.findappversion(id);
        model.addAttribute("appVersionList",appVersionList);
        return "developer/appversionadd";
    }
    //保存
    @RequestMapping("/dev/flatform/app/addversionsave")
    public String add(AppVersion app, Model model,Date date,
                      @RequestParam(value = "id",required = true) Integer id1,
                      HttpServletRequest request,
                      @RequestParam(value = "a_downloadLink") MultipartFile attach, HttpSession session) {
        //System.out.println(appInfo.getId());
        //System.out.println("从上一个方法中得到的参数"+id1);

        app.setAppId(id1);
        String idPicPath = null;
        String idPicPath1 = null;
        Integer id = app.getAppId();
        System.err.println("add新增版本信息()================");
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics"
                    + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            int filesize = 50000000;
            if (attach.getSize() > filesize) {
                model.addAttribute("fileUploadError", " * 上传大小不得超过500KB");
                return "redirect:appversionadd?id=" + app.getAppId();
            } else if (prefix.equalsIgnoreCase("apk")) {
                String fileName = System.currentTimeMillis()
                        + RandomUtils.nextInt(1000000) + "_kangkang.apk";
                app.setApkFileName(fileName);
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("fileUploadError", " * 上传失败！");
                    return "redirect:appversionadd1?id=" + app.getAppId();
                }
                idPicPath = path + File.separator + fileName;
                idPicPath1 = "/upload/" + fileName;
            } else {
                model.addAttribute("fileUploadError", " * 上传图片格式不正确！");
                System.out.println(app.getAppId() + "3333333333333");
                return "redirect:appversionadd?id=" + app.getAppId();
            }
        }

        System.out.println(app.getModifyDate());
        app.setCreatedBy(((DevUser) request.getSession().getAttribute(
                com.kgc.tools.Constants.USER_SESSION)).getId());
        app.setCreationDate(new Date());
        app.setApkLocPath(idPicPath);
        app.setDownloadLink(idPicPath1);
        //添加版本信息
        if (appVersionService.addAppVersion(app)) {
            app.setModifyDate(new Date());
            app.setCreationDate(new Date());
            //查看最新版本
            AppVersion appversion = appVersionService.findappver(id);
            appversion.setModifyDate(new Date());
            //更新版本
            appVersionService.updateappinfo(appversion.getAppId(),id);
            System.out.println("更新版本时间"+appversion.getModifyDate());
            return "redirect:list";
        } else {
            return "redirect:appversionadd?id=" + app.getAppId();
        }
    }

    /**
     * 修改
     */
    //跳转
    @RequestMapping("dev/flatform/app/appversionmodify")
    public String updatemodify(Model model,String fileUploadError,Integer aid,Integer vid)
    {
        System.out.println("进入了修改=============");
        //appinfoID
        model.addAttribute("appid",aid);
        //版本id
        model.addAttribute("vid",vid);
        model.addAttribute("fileUploadError",fileUploadError);
        //查看版本历史记录
        List<AppVersion> appVersionList=appVersionService.findappversion(aid);
        model.addAttribute("appVersionList",appVersionList);

        //查看当前的版本号的信息
        AppVersion appversion=appVersionService.findappver(aid);
        model.addAttribute("appVersion",appversion);
        return "developer/appversionmodify";
    }
    @RequestMapping("/dev/flatform/app/appversionmodifysave")
    public String appversionmodifysave(AppVersion app,Model model,HttpServletRequest request,@RequestParam(value = "attach") MultipartFile attach) {
        System.out.println(app.getAppId());
        String idPicPath = null;
        String idPicPath1 = null;
        Integer id = app.getAppId();
        System.err.println("add新增版本信息()================");
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics"
                    + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            int filesize = 50000000;
            if (attach.getSize() > filesize) {
                model.addAttribute("fileUploadError", " * 上传大小不得超过500KB");
                return "redirect:appversionmodify?id=" + app.getAppId();
            } else if (prefix.equalsIgnoreCase("apk")) {
                String fileName = System.currentTimeMillis()
                        + RandomUtils.nextInt(1000000) + "_kangkang.apk";
                app.setApkFileName(fileName);
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("fileUploadError", " * 上传失败！");
                    return "redirect:appversionmodify?id=" + app.getAppId();
                }
                idPicPath = path + File.separator + fileName;
                idPicPath1 = "/upload/" + fileName;
            } else {
                model.addAttribute("fileUploadError", " * 上传图片格式不正确！");
                System.out.println(app.getAppId() + "3333333333333");
                return "redirect:appversionmodify?id=" + app.getAppId();
            }
        }
        app.setCreationDate(new Date());
        app.setCreatedBy(((DevUser) request.getSession().getAttribute(
                com.kgc.tools.Constants.USER_SESSION)).getId());
        app.setApkLocPath(idPicPath);
        app.setDownloadLink(idPicPath1);
        //修改版本信息
        if (appVersionService.updateapkinfo(app)>0) {
            //查看最新版本
            AppVersion appversion=appVersionService.findappver(app.getAppId());
            //修改app的最新版本
            appVersionService.updateappinfo(appversion.getId(), app.getAppId());
            return "redirect:/dev/flatform/app/list";
        } else {
            return "redirect:appversionadd?id=" + app.getAppId();
        }
    }
    /**
     * 跳转查看app信息及版本信息页面
     */
    @RequestMapping("/dev/flatform/app/appview/{id}")
    public String veiwApp(@PathVariable Integer id, Model model){
        System.out.println("跳转查看app信息=================");
        model.addAttribute("id",id);
        //通过id查询所有信息
        AppInfo app = appInfoService.findAppInfoById(id);
        System.out.println(app.getStatus());
        model.addAttribute("appInfo",app);
        //通过id查询历史版本信息
        List<AppVersion> appVersion= appVersionService.findappversion(id);
        model.addAttribute("appVersionList",appVersion);
        return "developer/appinfoview";
    }
}
