package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Enterprise;
import net.sppan.base.entity.Recruit;
import net.sppan.base.service.IEnterpriseService;
import net.sppan.base.service.IRecruitService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
    @Autowired
    private IEnterpriseService enterpriseService;


    @RequestMapping("/index")
    public String index() {
        return "admin/recruit/index";
    }

    @RequestMapping("/auditList")
    public String auditList() {
        return "admin/recruit/auditList";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Recruit> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<Recruit> page = recruitService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Enterprise> eList = enterpriseService.findAll();
        List<Recruit> list = recruitService.findAll();
        map.put("eList", eList);
        map.put("list",list);
        return "admin/recruit/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Recruit recruit = recruitService.find(id);
        map.put("recruit", recruit);
        List<Recruit> list = recruitService.findAll();
        List<Enterprise> eList = enterpriseService.findAll();
        map.put("eList", eList);
        map.put("list",list);
        return "admin/recruit/form";
    }

    @RequestMapping(value = "/audit/{id}", method = RequestMethod.GET)
    public String audit(@PathVariable Integer id, ModelMap map) {
        Recruit recruit = recruitService.find(id);
        map.put("recruit", recruit);
        return "admin/recruit/audit";
    }

    @RequestMapping(value= {"/audit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult audit(HttpServletRequest request,  ModelMap map){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String review = request.getParameter("review");

        Recruit recruit = recruitService.find(Integer.valueOf(id));
        // 进行审核信息绑定
        recruit.setStatus(Integer.valueOf(status));
        recruit.setReview(review);
        try {
            recruitService.saveOrUpdate(recruit);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
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