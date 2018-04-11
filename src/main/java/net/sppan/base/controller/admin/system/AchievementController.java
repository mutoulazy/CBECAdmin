package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Achievement;
import net.sppan.base.service.IAchievementService;
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
@RequestMapping("/admin/achievement")
public class AchievementController extends BaseController{
    @Autowired
    private IAchievementService achievementService;

    @RequestMapping("/index")
    public String index() {
        return "admin/achievement/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Achievement> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<Achievement> page = achievementService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Achievement> list = achievementService.findAll();
        map.put("list", list);
        return "admin/achievement/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Achievement achievement = achievementService.find(id);
        map.put("achievement", achievement);

        List<Achievement> list = achievementService.findAll();
        map.put("list", list);
        return "admin/achievement/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Achievement achievement, ModelMap map){
        try {
            achievementService.saveOrUpdate(achievement);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            achievementService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
