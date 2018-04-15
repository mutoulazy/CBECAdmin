package net.sppan.base.service.impl;

import net.sppan.base.dao.IAchievementDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Achievement;
import net.sppan.base.service.IAchievementService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by mutoulazy on 2018/4/11.
 */
@Service
public class AchievementServiceImpl extends BaseServiceImpl<Achievement, Integer> implements IAchievementService{
    @Autowired
    private IAchievementDao achievementDao;

    @Override
    public IBaseDao<Achievement, Integer> getBaseDao() {
        return this.achievementDao;
    }

    @Override
    public void saveOrUpdate(Achievement achievement) {
        if(achievement.getId() != null){
            Achievement dbAchievement = find(achievement.getId());
            dbAchievement.setUpdateTime(new Date());
            dbAchievement.setIsHide(achievement.getIsHide());
            dbAchievement.setDescription(achievement.getDescription());
            dbAchievement.setUpdateTime(new Date());
            dbAchievement.setName(achievement.getName());
            dbAchievement.setImage(achievement.getImage());
            dbAchievement.setResource(achievement.getResource());
            dbAchievement.setIntroduction(achievement.getIntroduction());
            dbAchievement.setEnterprise(achievement.getEnterprise());
            update(dbAchievement);
        }else{
            achievement.setCreateTime(new Date());
            achievement.setUpdateTime(new Date());
            save(achievement);
        }
    }

    @Override
    public Page<Achievement> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return achievementDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        achievementDao.delete(id);
    }
}