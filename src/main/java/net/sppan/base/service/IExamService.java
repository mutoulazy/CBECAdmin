package net.sppan.base.service;

import net.sppan.base.entity.QuestionBank;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 考试服务类
 * Created by mutoulazy on 2018/4/26.
 */
public interface IExamService {

    // 查询题目
    void findJudgmentQuestionAndChoiceQuestion(ModelAndView modelAndView, String id, HttpSession session);


    // 判断题目
    List<QuestionBank> judgmentSystem(List<QuestionBank> questionBankVos,HttpSession session);
}
