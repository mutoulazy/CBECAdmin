package net.sppan.base.service.impl;

import net.sppan.base.dao.IRecruitDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Recruit;
import net.sppan.base.service.IRecruitService;
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
public class RecruitServiceImpl extends BaseServiceImpl<Recruit, Integer> implements IRecruitService {
    @Autowired
    private IRecruitDao recruitDao;

    @Override
    public IBaseDao<Recruit, Integer> getBaseDao() {
        return this.recruitDao;
    }



    @Override
    public void saveOrUpdate(Recruit recruit) {
        if(recruit.getId() != null){
            Recruit dbRecruit = find(recruit.getId());
            dbRecruit.setUpdateTime(new Date());
            dbRecruit.setEndTime(recruit.getEndTime());
            dbRecruit.setName(recruit.getName());
            dbRecruit.setReview(recruit.getReview());
            dbRecruit.setRequest(recruit.getRequest());
            dbRecruit.setPeopleCount(recruit.getPeopleCount());
            dbRecruit.setPay(recruit.getPay());
            dbRecruit.setAddress(recruit.getAddress());
            dbRecruit.setWelfare(recruit.getWelfare());
            dbRecruit.setIsHide(recruit.getIsHide());
            dbRecruit.setDescription(recruit.getDescription());
            dbRecruit.setEnterprise(recruit.getEnterprise());
            update(dbRecruit);
        }else{
            recruit.setCreateTime(new Date());
            recruit.setUpdateTime(new Date());
            save(recruit);
        }
    }

    @Override
    public Page<Recruit> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return recruitDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        recruitDao.delete(id);
    }
}