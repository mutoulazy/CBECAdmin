package net.sppan.base.service.impl;

import net.sppan.base.common.Constats;
import net.sppan.base.dao.IQuestionBankDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.QuestionBank;
import net.sppan.base.entity.Testpaper;
import net.sppan.base.service.IQuestionBankService;
import net.sppan.base.service.ITestpaperService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import net.sppan.base.vo.ZtreeView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Service
public class QuestionBankServiceImpl extends BaseServiceImpl<QuestionBank, Integer> implements IQuestionBankService{
    @Autowired
    private IQuestionBankDao questionBankDao;
    @Autowired
    private ITestpaperService testpaperService;

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
    @Cacheable(value=Constats.RESOURCECACHENAME,key="'tree_' + #paperId")
    public List<ZtreeView> tree(int paperId) {
        List<ZtreeView> resulTreeNodes = new ArrayList<ZtreeView>();
        Testpaper testpaper = testpaperService.find(paperId);
        Set<QuestionBank> questionBanks = testpaper.getQuestionBanks();
        //resulTreeNodes.add(new ZtreeView(0L, null, "试题菜单", true));
        ZtreeView node;
        List<QuestionBank> all = questionBankDao.findAll();
        for (QuestionBank questionBank : all) {
            node = new ZtreeView();
            node.setId(Long.valueOf(questionBank.getId()));
            node.setName(questionBank.getName());
            if (questionBanks != null && questionBanks.contains(questionBank)) {
                node.setChecked(true);
            }
            resulTreeNodes.add(node);
        }
        return resulTreeNodes;
    }

    @Override
    public void delete(Integer id) {
        questionBankDao.delete(id);
    }
}
