package net.sppan.base.service.impl;

import net.sppan.base.dao.IQuestionBankDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.QuestionBank;
import net.sppan.base.service.IQuestionBankService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Service
public class QuestionBankServiceImpl extends BaseServiceImpl<QuestionBank, Integer> implements IQuestionBankService{
    @Autowired
    private IQuestionBankDao questionBankDao;

    @Override
    public IBaseDao<QuestionBank, Integer> getBaseDao() {
        return this.questionBankDao;
    }

    @Override
    public void saveOrUpdate(QuestionBank questionBank) {
        if(questionBank.getId() != null){
            QuestionBank dbQuestionBank = find(questionBank.getId());
            dbQuestionBank.setUpdateTime(new Date());
            dbQuestionBank.setIsHide(questionBank.getIsHide());
            dbQuestionBank.setName(questionBank.getName());
            dbQuestionBank.setType(questionBank.getType());
            dbQuestionBank.setOption1(questionBank.getOption1());
            dbQuestionBank.setOption2(questionBank.getOption2());
            dbQuestionBank.setOption3(questionBank.getOption3());
            dbQuestionBank.setOption4(questionBank.getOption4());
            dbQuestionBank.setAnswer(questionBank.getAnswer());
            dbQuestionBank.setDescription(questionBank.getDescription());
            update(dbQuestionBank);
        }else{
            questionBank.setCreateTime(new Date());
            questionBank.setUpdateTime(new Date());
            save(questionBank);
        }
    }

    @Override
    public Page<QuestionBank> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return questionBankDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        questionBankDao.delete(id);
    }
}
