package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Subject;
import net.sppan.base.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/8.
 */
@Controller
@RequestMapping("/admin/subject")
public class SubjectController extends BaseController{
    @Autowired
    private ISubjectService subjectService;

    @RequestMapping("/index")
    public String index() {
        return "admin/subject/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Subject> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<Subject> page = subjectService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Subject> list = subjectService.findAll();
        map.put("list", list);
        return "admin/subject/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id,ModelMap map) {
        Subject subject = subjectService.find(id);
        map.put("subject", subject);

        List<Subject> list = subjectService.findAll();
        map.put("list", list);
        return "admin/subject/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Subject subject, ModelMap map){
        try {
            subjectService.saveOrUpdate(subject);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            subjectService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
