package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Course;
import net.sppan.base.entity.Subject;
import net.sppan.base.service.ICourseService;
import net.sppan.base.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mutoulazy on 2018/5/1.
 */
@Controller
@RequestMapping("/admin/course")
public class CourseController extends BaseController{
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ISubjectService subjectService;

    @RequestMapping("/index")
    public String index() {
        return "admin/course/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Course> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<Course> page = courseService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Course> list = courseService.findAll();
        List<Subject> eList = subjectService.findAll();
        map.put("list", list);
        map.put("eList", eList);
        return "admin/course/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Course course = courseService.find(id);
        map.put("course", course);

        List<Subject> eList = subjectService.findAll();
        List<Course> list = courseService.findAll();
        map.put("list", list);
        map.put("eList", eList);
        return "admin/course/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Course course, ModelMap map){
        try {
            courseService.saveOrUpdate(course);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            courseService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
