package net.sppan.base.service.impl;

import net.sppan.base.dao.ISubjectDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Subject;
import net.sppan.base.service.IRoleService;
import net.sppan.base.service.ISubjectService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by mutoulazy on 2018/4/8.
 */
@Service
public class SubjectServiceImpl extends BaseServiceImpl<Subject, Integer> implements ISubjectService {
    @Autowired
    private ISubjectDao subjectDao;

    @Override
    public IBaseDao<Subject, Integer> getBaseDao() {
        return this.subjectDao;
    }


    @Override
    public void saveOrUpdate(Subject subject) {
        if(subject.getId() != null){
            Subject dbSubject = find(subject.getId());
            dbSubject.setUpdateTime(new Date());
            dbSubject.setName(subject.getName());
            dbSubject.setLevel(subject.getLevel());
            dbSubject.setSort(subject.getSort());
            dbSubject.setIsHide(subject.getIsHide());
            dbSubject.setDescription(subject.getDescription());
            dbSubject.setUpdateTime(new Date());
            dbSubject.setParent(subject.getParent());
            update(dbSubject);
        }else{
            subject.setCreateTime(new Date());
            subject.setUpdateTime(new Date());
            save(subject);
        }
    }

    @Override
    public Page<Subject> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return subjectDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        subjectDao.delete(id);
    }
}
