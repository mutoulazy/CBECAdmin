package net.sppan.base.service.impl;

import net.sppan.base.dao.ICourseDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Course;
import net.sppan.base.service.ICourseService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by mutoulazy on 2018/5/1.
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Integer> implements ICourseService{
    @Autowired
    private ICourseDao courseDao;

    @Override
    public IBaseDao<Course, Integer> getBaseDao() {
        return this.courseDao;
    }

    @Override
    public void saveOrUpdate(Course course) {
        if(course.getId() != null){
            Course dbCourse = find(course.getId());
            dbCourse.setUpdateTime(new Date());
            dbCourse.setIsHide(course.getIsHide());
            dbCourse.setDescription(course.getDescription());
            dbCourse.setName(course.getName());
            dbCourse.setImage(course.getImage());
            dbCourse.setVideoUrl(course.getVideoUrl());
            dbCourse.setIsRecommend(course.getIsRecommend());
            dbCourse.setAim(course.getAim());
            dbCourse.setPeople(course.getPeople());
            dbCourse.setTeacher(course.getTeacher());
            dbCourse.setSubject(course.getSubject());
            update(dbCourse);
        }else{
            course.setCreateTime(new Date());
            course.setUpdateTime(new Date());
            save(course);
        }
    }

    @Override
    public Page<Course> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        return courseDao.findAllByNameContaining(searchText, pageRequest);
    }

    @Override
    public void delete(Integer id) {
        courseDao.delete(id);
    }
}
