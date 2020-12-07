package com.kgc.controller;

import com.kgc.common.ResultObject;
import com.kgc.pojo.AppInfo;
import com.kgc.pojo.DataDictionary;
import com.kgc.pojo.DevUser;
import com.kgc.service.AppInfoService;
import com.kgc.service.AppVersionService;
import com.kgc.service.DataDictionaryService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class AppinfoAddController {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private DataDictionaryService dataDictionaryService;


    //加载平台列表
    @RequestMapping("/dev/flatform/app/datadictionarylist.json")
    @ResponseBody
    public Object addinfoadd() {
        System.out.println("==========加载平台");
        List<DataDictionary> listptname = dataDictionaryService.findptname();
        return listptname;
    }

    //跳转到新增页面
    @RequestMapping("/dev/flatform/app/appinfoadd")
    public String addView() {
        return "developer/appinfoadd";
    }

    /**
     * 验证APK名称
     */
    @RequestMapping("/dev/flatform/app/apkexist.json")
    @ResponseBody
    public Object apkexist(String APKName) {
        HashMap<String, String> hm = new HashMap<String, String>();
        if (APKName.isEmpty()) {
            hm.put("APKName", "empty");
            return hm;
        }
        if (appInfoService.findname(APKName) > 0) {
            hm.put("APKName", "exist");
        } else {
            hm.put("APKName", "noexist");
        }
        return hm;
    }

    // 添加app信息
    @RequestMapping("/dev/flatform/app/appinfoaddsave")
    public String add(AppInfo app, Model model, HttpServletRequest request,
                      @RequestParam(value = "a_logoPicPath") MultipartFile attach) {
        String idPicPath = null;
        String idPicPath1 = null;
        System.err.println("add()================");
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics"
                    + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            int filesize = 500000;
            if (attach.getSize() > filesize) {
                model.addAttribute("fileUploadError", " * 上传大小不得超过50KB");
                return "useradd";
            } else if (prefix.equalsIgnoreCase("jpg")
                    || prefix.equalsIgnoreCase("png")) {
                String fileName = System.currentTimeMillis()
                        + RandomUtils.nextInt(1000000) + "_kangkang.jpg";
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("fileUploadError", " * 上传失败！");
                    return "developer/appinfoadd";
                }
                idPicPath = path + File.separator + fileName;
                idPicPath1 = "/upload/" + fileName;
            } else {
                model.addAttribute("fileUploadError", " * 上传图片格式不正确！");
                return "developer/appinfoadd";
            }
        }
        app.setCreationDate(new Date());
        app.setDevId(((DevUser) request.getSession().getAttribute(
                com.kgc.tools.Constants.USER_SESSION)).getId());
        app.setCreatedBy(((DevUser) request.getSession().getAttribute(
                com.kgc.tools.Constants.USER_SESSION)).getId());
        app.setLogoLocPath(idPicPath);
        app.setLogoPicPath(idPicPath1);
        if (appInfoService.addAppInfo(app)) {
            return "redirect:/dev/flatform/app/list";
        } else {
            return "developer/appinfoadd";
        }
    }

    /**
     * 修改
     */

    //跳转到修改页面
    @RequestMapping("/dev/flatform/app/appinfomodify")
    public String appinfoupdate(Integer id, String fileUploadError, Model model) {
        System.out.println("进入了跳转修改app页面===============");
        AppInfo app = appInfoService.findAppInfoById(id);
        model.addAttribute("appInfo", app);
        model.addAttribute("fileUploadError", fileUploadError);
        model.addAttribute("id", id);
        return "/developer/appinfomodify";
    }

    //保存修改
    @RequestMapping("/dev/flatform/app/appinfomodifysave")

    public String appinfomodifysave(AppInfo app, Model model,
                                    HttpServletRequest request,
                                    @RequestParam(value = "attach") MultipartFile attach) {
        String idPicPath = null;
        String idPicPath1 = null;
        System.err.println("add()================");
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics"
                    + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            int filesize = 500000;
            if (attach.getSize() > filesize) {
                model.addAttribute("fileUploadError", " * 上传大小不得超过500KB");
                return "redirect:appinfomodify?id=" + app.getId();
            } else if (prefix.equalsIgnoreCase("jpg")
                    || prefix.equalsIgnoreCase("png")) {
                String fileName = System.currentTimeMillis()
                        + RandomUtils.nextInt(1000000) + "img.jpg";
                app.setAPKName(fileName);
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("fileUploadError", " * 上传失败！");
                    return "redirect:appinfomodify?id=" + app.getId();
                }
                idPicPath = path + File.separator + fileName;
                idPicPath1 = "/upload/" + fileName;
            } else {
                model.addAttribute("fileUploadError", " * 上传图片格式不正确！");
                return "redirect:appinfomodify?id=" + app.getId();
            }
        }
        System.out.println("进入修改app版本信息============");
        app.setLogoLocPath(idPicPath);
        app.setLogoPicPath(idPicPath1);
        app.setModifyBy(((DevUser) request.getSession().getAttribute(
                com.kgc.tools.Constants.USER_SESSION)).getId());
        app.setModifyDate(new Date());
        app.setCreationDate(new Date());
        int i = appInfoService.updateAppInfo(app);
        if (i > 0) {
            return "redirect:/dev/flatform/app/list";
        } else {
            return "redirect:appinfomodify?id=" + app.getId();
        }
    }

    /**
     * 根据id进行删除
     */
    @RequestMapping("/dev/flatform/app/delapp.json")
    @ResponseBody
    public Object delapp(Integer id) {
        System.out.println("删除==========");
        HashMap<String, String> hashMap = new HashMap<>();
        System.out.println(id);
        int i = appInfoService.deleteappinfo(id);
        if (i > 0) {
            System.out.println(id);
            System.out.println("删除历史版本信息");
            int a = appInfoService.deleteappversion(id);
            if (a > 0) {
                hashMap.put("delResult", "true");
            }
            return hashMap;
        } else {
            hashMap.put("delResult", "false");
            return hashMap;
        }
    }

    /**
     * 根据id修改上下架
     */
    @RequestMapping("sale/{appid}")
    public ResultObject sale(@PathVariable("appid") Integer appid){
        AppInfo appInfo = appInfoService.findAppInfoById(appid);
        if (appInfo.getStatus() == 5){
            if (appInfoService.updatestatus(appid)>0){
                return  ResultObject.resultBySuccess(1,"success");
            }else {
                return ResultObject.resultByErrorMsg(-1,"failed");
            }
        }else if (appInfo.getStatus() == 4){
            if (appInfoService.updatestatuss(appid)>0){
                return ResultObject.resultBySuccess(1,"success");
            }else {
                return  ResultObject.resultByErrorMsg(-1,"failed");
            }
        }
        return ResultObject.resultByErrorMsg(-1,"failed");
    }

}
