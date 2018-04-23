package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.UserScore;
import net.sppan.base.service.IUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Controller
@Repository("/admin/userScore")
public class UserScoreController extends BaseController{
    @Autowired
    private IUserScoreService userScoreService;

    @RequestMapping("/index")
    public String index() {
        return "admin/userScore/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<UserScore> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<UserScore> page = userScoreService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<UserScore> list = userScoreService.findAll();
        map.put("list", list);
        return "admin/userScore/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        UserScore userScore = userScoreService.find(id);
        map.put("userScore", userScore);

        List<UserScore> list = userScoreService.findAll();
        map.put("list", list);
        return "admin/userScore/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(UserScore userScore, ModelMap map){
        try {
            userScoreService.saveOrUpdate(userScore);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            userScoreService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
