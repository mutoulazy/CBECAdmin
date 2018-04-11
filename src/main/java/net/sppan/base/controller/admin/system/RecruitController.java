package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Recruit;
import net.sppan.base.service.IRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/11.
 */
@Controller
@RequestMapping("/admin/recruit")
public class RecruitController extends BaseController{
    @Autowired
    private IRecruitService recruitService;


    @RequestMapping("/index")
    public String index() {
        return "admin/recruit/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Recruit> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<Recruit> page = recruitService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Recruit> list = recruitService.findAll();
        map.put("list", list);
        return "admin/recruit/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Recruit recruit = recruitService.find(id);
        map.put("recruit", recruit);

        List<Recruit> list = recruitService.findAll();
        map.put("list", list);
        return "admin/recruit/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Recruit recruit, ModelMap map){
        try {
            recruitService.saveOrUpdate(recruit);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            recruitService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
