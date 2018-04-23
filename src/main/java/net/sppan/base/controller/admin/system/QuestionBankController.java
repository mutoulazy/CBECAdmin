package net.sppan.base.controller.admin.system;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.QuestionBank;
import net.sppan.base.service.IQuestionBankService;
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
@RequestMapping("/admin/questionBank")
public class QuestionBankController extends BaseController{
    @Autowired
    private IQuestionBankService questionBankService;

    @RequestMapping("/index")
    public String index() {
        return "admin/questionBank/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<QuestionBank> list(@RequestParam(value="searchText",required=false) String searchText){
        Page<QuestionBank> page = questionBankService.findAllByLike(searchText, getPageRequest());
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<QuestionBank> list = questionBankService.findAll();
        map.put("list", list);
        return "admin/questionBank/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        QuestionBank questionBank = questionBankService.find(id);
        map.put("questionBank", questionBank);

        List<QuestionBank> list = questionBankService.findAll();
        map.put("list", list);
        return "admin/questionBank/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(QuestionBank questionBank, ModelMap map){
        try {
            questionBankService.saveOrUpdate(questionBank);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            questionBankService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
