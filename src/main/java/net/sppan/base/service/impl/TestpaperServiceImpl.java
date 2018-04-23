package net.sppan.base.service.impl;

import net.sppan.base.dao.ITestpaperDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Testpaper;
import net.sppan.base.service.ITestpaperService;
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
public class TestpaperServiceImpl extends BaseServiceImpl<Testpaper, Integer> implements ITestpaperService{
    @Autowired
    ITestpaperDao testpaperDao;

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
    public void delete(Integer id) {
        testpaperDao.delete(id);
    }
}
