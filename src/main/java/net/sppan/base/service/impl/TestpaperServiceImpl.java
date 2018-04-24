package net.sppan.base.service.impl;

import net.sppan.base.common.Constats;
import net.sppan.base.dao.IQuestionBankDao;
import net.sppan.base.dao.ITestpaperDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.QuestionBank;
import net.sppan.base.entity.Testpaper;
import net.sppan.base.service.IQuestionBankService;
import net.sppan.base.service.ITestpaperService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Service
public class TestpaperServiceImpl extends BaseServiceImpl<Testpaper, Integer> implements ITestpaperService{
    @Autowired
    private ITestpaperDao testpaperDao;
    @Autowired
    private IQuestionBankService questionBankService;

    @Override
    public IBaseDao<Testpaper, Integer> getBaseDao() {
        return this.testpaperDao;
    }

    @Override
    public void saveOrUpdate(Testpaper testpaper) {
        if(testpaper.getId() != null){
            Testpaper dbTestpaper = find(testpaper.getId());
            dbTestpaper.setUpdateTime(new Date());
            dbTestpaper.setIsHide(testpaper.getIsHide());
            dbTestpaper.setName(testpaper.getName());
            dbTestpaper.setQuestionBanks(testpaper.getQuestionBanks());
            update(dbTestpaper);
        }else{
            testpaper.setCreateTime(new Date());
            testpaper.setUpdateTime(new Date());
            save(testpaper);
        }
    }

    @Override
    public Page<Testpaper> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return testpaperDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    @CacheEvict(value = Constats.RESOURCECACHENAME, key = "'tree_' + #id")
    public void grant(Integer id, String[] questionIds) {
        Testpaper testpaper = find(id);
        Assert.notNull(testpaper, "试卷不存在");

        //Assert.state(!"administrator".equals(role.getRoleKey()),"超级管理员角色不能进行资源分配");
        QuestionBank questionBank;
        Set<QuestionBank> questionBanks = new HashSet<QuestionBank>();
        if(questionIds != null){
            for (int i = 0; i < questionIds.length; i++) {
                if(StringUtils.isBlank(questionIds[i]) || "0".equals(questionIds[i])){
                    continue;
                }
                Integer rid = Integer.parseInt(questionIds[i]);
                questionBank = questionBankService.find(rid);
                questionBanks.add(questionBank);
            }
        }
        testpaper.setQuestionBanks(questionBanks);
        update(testpaper);
    }

    @Override
    public void delete(Integer id) {
        testpaperDao.delete(id);
    }
}
