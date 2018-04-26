package net.sppan.base.controller.admin.exam;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Testpaper;
import net.sppan.base.mapper.UserMapper;
import net.sppan.base.service.ITestpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    private UserMapper userMapper;

    /**
     * 跳转试卷首页
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        List<Testpaper> allTestPaper = testpaperService.findAll();
        System.out.println(userMapper.getAll());
        modelAndView.addObject("allTestPaper", allTestPaper);

        modelAndView.setViewName("exam/index");
        return modelAndView;
    }
}
