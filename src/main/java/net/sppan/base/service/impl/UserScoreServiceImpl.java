package net.sppan.base.service.impl;

import net.sppan.base.dao.IUserScoreDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.UserScore;
import net.sppan.base.service.IUserScoreService;
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
public class UserScoreServiceImpl extends BaseServiceImpl<UserScore, Integer> implements IUserScoreService {
    @Autowired
    private IUserScoreDao userScoreDao;

    @Override
    public IBaseDao<UserScore, Integer> getBaseDao() {
        return this.userScoreDao;
    }

    @Override
    public void saveOrUpdate(UserScore userScore) {
        if(userScore.getId() != null){
            UserScore dbUserScore = find(userScore.getId());
            dbUserScore.setIsHide(userScore.getIsHide());
            dbUserScore.setName(userScore.getName());
            dbUserScore.setUser(userScore.getUser());
            dbUserScore.setTestpaper(userScore.getTestpaper());
            update(dbUserScore);
        }else{
            userScore.setCreateTime(new Date());
            save(userScore);
        }
    }

    @Override
    public Page<UserScore> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return userScoreDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        userScoreDao.delete(id);
    }
}
