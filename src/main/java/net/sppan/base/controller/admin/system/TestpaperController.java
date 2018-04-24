package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Testpaper;
import net.sppan.base.service.ITestpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Controller
@RequestMapping("/admin/testpaper")
public class TestpaperController extends BaseController{
    @Autowired
    private ITestpaperService testpaperService;

    @RequestMapping("/index")
    public String index() {
        return "admin/testpaper/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Testpaper> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<Testpaper> page = testpaperService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Testpaper> list = testpaperService.findAll();
        map.put("list", list);
        return "admin/testpaper/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Testpaper testpaper = testpaperService.find(id);
        map.put("testpaper", testpaper);

        List<Testpaper> list = testpaperService.findAll();
        map.put("list", list);
        return "admin/testpaper/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Testpaper testpaper, ModelMap map){
        try {
            testpaperService.saveOrUpdate(testpaper);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(@PathVariable Integer id, ModelMap map) {
        Testpaper testpaper = testpaperService.find(id);
        map.put("testpaper", testpaper);
        return "admin/testpaper/grant";
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult grant(@PathVariable Integer id,
                            @RequestParam(required = false) String[] questionIds, ModelMap map) {
        try {
            testpaperService.grant(id,questionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            testpaperService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
