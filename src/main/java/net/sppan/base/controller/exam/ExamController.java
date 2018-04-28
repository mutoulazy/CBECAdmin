package net.sppan.base.controller.exam;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.QuestionBank;
import net.sppan.base.entity.Testpaper;
import net.sppan.base.mapper.UserMapper;
import net.sppan.base.service.IExamService;
import net.sppan.base.service.ITestpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by mutoulazy on 2018/4/26.
 */
@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController{
    @Autowired
    private ITestpaperService testpaperService;
    @Autowired
    private IExamService examService;


    /**
     * 跳转试卷首页
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        List<Testpaper> allTestPaper = testpaperService.findAll();
        modelAndView.addObject("allTestPaper", allTestPaper);
        modelAndView.setViewName("exam/index");
        return modelAndView;
    }

    /**
     * 试卷模板映射
     * @return
     */
    @RequestMapping(value = "/exam-{id}", method = RequestMethod.GET)
    public ModelAndView exam(@PathVariable String id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("试卷模板映射");
        examService.findJudgmentQuestionAndChoiceQuestion(modelAndView, id, session);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/submitpapers", method = RequestMethod.POST)
    public List<QuestionBank> submitPapers(@RequestBody List<QuestionBank> questionBankVos, HttpSession session) {

        List<QuestionBank> judgmentSystem = examService.judgmentSystem(questionBankVos, session);

        return judgmentSystem;
    }
}
