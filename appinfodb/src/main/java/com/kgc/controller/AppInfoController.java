package com.kgc.controller;

import com.kgc.pojo.AppCategory;
import com.kgc.pojo.DataDictionary;
import com.kgc.service.AppCategoryService;
import com.kgc.service.AppInfoService;
import com.kgc.service.AppVersionService;
import com.kgc.service.DataDictionaryService;
import com.kgc.tools.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppInfoController {
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private DataDictionaryService dataDictionaryService;

    /**
     * 根据条件进行查询，分页
     */
    @RequestMapping(value = "dev/flatform/app/list")
    public String appinfolist(Model model, Integer pid, String pageIndex,
                              String querySoftwareName, Integer queryStatus,
                              Integer queryFlatformId, Integer queryCategoryLevel1,
                              Integer queryCategoryLevel2, Integer queryCategoryLevel3) {

        int pageSize = com.kgc.tools.Constants.pageSize;
        int currentPageNo = 1;
        if (null != pageIndex) {
            currentPageNo = Integer.valueOf(pageIndex);
        }
        int totalCount = appInfoService.getAppInfoCount(querySoftwareName, queryStatus,
                queryFlatformId, queryCategoryLevel1, queryCategoryLevel2,
                queryCategoryLevel3);
        PageSupport pages = new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

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

        // 控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }
        // 查询app状态
        List<DataDictionary> list = dataDictionaryService.findtypename();
        model.addAttribute("statusList", list);
        // 查询所属平台
        List<DataDictionary> listptname = dataDictionaryService.findptname();
        model.addAttribute("flatFormList", listptname);
        // 加载一级分类
        List<AppCategory> app = appCategoryService.findtype();
        model.addAttribute("categoryLevel1List", app);
        model.addAttribute("pages", pages);
        model.addAttribute("querySoftwareName", querySoftwareName);
        model.addAttribute("queryStatus", queryStatus);
        model.addAttribute("queryFlatformId", queryFlatformId);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        // 查询全部
        model.addAttribute("appInfoList", appInfoService.getAppInfoList(querySoftwareName
        ,queryStatus,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3
        ,(currentPageNo - 1) * pageSize,pageSize));
        return "/developer/appinfolist";
    }

    // 二级分类
    @RequestMapping("/dev/flatform/app/categorylevellist")
    @ResponseBody
    public Object findtwotype(Integer pid) {
        System.out.println("二级================");
        List<AppCategory> listtwo = appCategoryService.findtwotype(pid);
        return listtwo;
    }

    // 三级分类
    @RequestMapping("/threetype")
    @ResponseBody
    public Object findtwotype3(Integer pid) {
        System.out.println("三级================");
        List<AppCategory> listtwo = appCategoryService.findtwotype(pid);
        return listtwo;
    }
}
