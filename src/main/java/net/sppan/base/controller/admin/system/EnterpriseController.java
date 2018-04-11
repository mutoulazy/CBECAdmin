package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Enterprise;
import net.sppan.base.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业信息控制类
 * Created by mutoulazy on 2018/4/11.
 */
@Controller
@RequestMapping("/admin/enterprise")
public class EnterpriseController extends BaseController{
    @Autowired
    private IEnterpriseService enterpriseService;

    @RequestMapping("/index")
    public String index() {
        return "admin/enterprise/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Enterprise> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<Enterprise> page = enterpriseService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Enterprise> list = enterpriseService.findAll();
        map.put("list", list);
        return "admin/enterprise/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Enterprise enterprise = enterpriseService.find(id);
        map.put("enterprise", enterprise);

        List<Enterprise> list = enterpriseService.findAll();
        map.put("list", list);
        return "admin/enterprise/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Enterprise enterprise, ModelMap map){
        try {
            enterpriseService.saveOrUpdate(enterprise);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            enterpriseService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
