package net.sppan.base.service.impl;


import net.sppan.base.dao.ITestpaperDao;
import net.sppan.base.entity.QuestionBank;
import net.sppan.base.entity.Testpaper;
import net.sppan.base.entity.User;
import net.sppan.base.entity.UserScore;
import net.sppan.base.mapper.TbQuestionBankMapper;
import net.sppan.base.mapper.TbUserScoreMapper;
import net.sppan.base.service.IExamService;
import net.sppan.base.service.IUserScoreService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by mutoulazy on 2018/4/26.
 */
@Service
public class ExamServiceImpl implements IExamService{
    @Autowired
    private TbUserScoreMapper tbUserScoreMapper;
    @Autowired
    private ITestpaperDao testpaperDao;
    @Autowired
    private IUserScoreService userScoreService;
    @Autowired
    private TbQuestionBankMapper tbQuestionBankMapper;

    @Override
    public void findJudgmentQuestionAndChoiceQuestion(ModelAndView modelAndView, String id, HttpSession session) {
        // 查询该试卷是否已经存在成绩
        UserScore userScore = new UserScore();
        Testpaper testpaper = new Testpaper();
        testpaper.setId(Integer.valueOf(id));
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if(principal== null){
            System.out.println("未进行登陆");
        }
        userScore.setUser((User) principal);
        userScore.setTestpaper(testpaper);
        UserScore ifExistenceScore = tbUserScoreMapper.selectByTestpaperIdAndUserId(userScore);
        if (ifExistenceScore != null) {
            System.out.println("score: " + ifExistenceScore.getScore());
            modelAndView.addObject("score", ifExistenceScore.getScore());
            modelAndView.setViewName("exam/score");
            return;
        }

        //进行题目显示
        session.setAttribute("testpaperId",id);
        List<QuestionBank> judgmentQuestionBanks = tbQuestionBankMapper.selectByTestPaperIdAndType(Integer.valueOf(id), 2); // 判断题
        for (QuestionBank judgmentQuestionBank : judgmentQuestionBanks) {
            session.setAttribute(judgmentQuestionBank.getId().toString(), judgmentQuestionBank.getAnswer());
        }
        List<QuestionBank> choiceQuestionBanks = tbQuestionBankMapper.selectByTestPaperIdAndType(Integer.valueOf(id), 1); // 选择题
        for (QuestionBank choiceQuestionBank : choiceQuestionBanks) {
            session.setAttribute(choiceQuestionBank.getId().toString(), choiceQuestionBank.getAnswer());
        }

        // 设置考试时间
        session.setAttribute("ExamTime", "60");

        modelAndView.addObject("JudgmentQuestion", judgmentQuestionBanks);
        modelAndView.addObject("ChoiceQuestion", choiceQuestionBanks);
        modelAndView.setViewName("exam/exam");
    }

    @Override
    public List<QuestionBank> judgmentSystem(List<QuestionBank> questionBankVos, HttpSession session) {
        Integer score = 0;
        for (QuestionBank questionBankVo : questionBankVos) {
            if (questionBankVo.getAnswer() != null) {
                if (session.getAttribute(questionBankVo.getId().toString())
                        .equals(questionBankVo.getAnswer())) {
                    score = score + 1;
                    questionBankVo.setCorrect(true);
                } else {
                    questionBankVo.setCorrect(false);
                    questionBankVo
                            .setAnswer(session.getAttribute(questionBankVo.getId().toString()).toString());
                }
            } else {
                questionBankVo.setCorrect(false);
                questionBankVo
                        .setAnswer(session.getAttribute(questionBankVo.getId().toString()).toString());
            }
        }
        UserScore userScore = new UserScore();
        Testpaper testpaper = testpaperDao.findOne(Integer.valueOf(session.getAttribute("testpaperId").toString()));
        userScore.setTestpaper(testpaper);
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if(principal== null){
            System.out.println("未进行登陆");
        }
        userScore.setIsHide(0);
        userScore.setName(testpaper.getName());
        userScore.setUser((User) principal);
        userScore.setScore(String.valueOf(score));
        userScoreService.saveOrUpdate(userScore);


        return questionBankVos;// 返回状态
    }
}
