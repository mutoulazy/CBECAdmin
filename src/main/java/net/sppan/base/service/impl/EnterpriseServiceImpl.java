package net.sppan.base.service.impl;

import net.sppan.base.dao.IEnterpriseDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Enterprise;
import net.sppan.base.service.IEnterpriseService;
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
public class EnterpriseServiceImpl extends BaseServiceImpl<Enterprise, Integer> implements IEnterpriseService{
    @Autowired
    private IEnterpriseDao enterpriseDao;

    @Override
    public IBaseDao<Enterprise, Integer> getBaseDao() {
        return this.enterpriseDao;
    }



    @Override
    public void saveOrUpdate(Enterprise enterprise) {
        if(enterprise.getId() != null){
            Enterprise dbEnterprise = find(enterprise.getId());
            dbEnterprise.setUpdateTime(new Date());
            dbEnterprise.setFullName(enterprise.getFullName());
            dbEnterprise.setShrotName(enterprise.getShrotName());
            dbEnterprise.setType(enterprise.getType());
            dbEnterprise.setNature(enterprise.getNature());
            dbEnterprise.setPeopleCount(enterprise.getPeopleCount());
            dbEnterprise.setChangeMan(enterprise.getChangeMan());
            dbEnterprise.setPhone(enterprise.getPhone());
            dbEnterprise.setMail(enterprise.getMail());
            dbEnterprise.setWebSite(enterprise.getWebSite());
            dbEnterprise.setAddress(enterprise.getAddress());
            dbEnterprise.setRemark(enterprise.getRemark());
            dbEnterprise.setIsHide(enterprise.getIsHide());
            dbEnterprise.setDescription(enterprise.getDescription());
            update(dbEnterprise);
        }else{
            enterprise.setCreateTime(new Date());
            enterprise.setUpdateTime(new Date());
            save(enterprise);
        }
    }

    @Override
    public Page<Enterprise> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return enterpriseDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        enterpriseDao.delete(id);
    }
}
