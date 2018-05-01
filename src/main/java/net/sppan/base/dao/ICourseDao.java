package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Created by mutoulazy on 2018/5/1.
 */
@Repository
public interface ICourseDao extends IBaseDao<Course, Integer>{
    Page<Course> findAllByNameContaining(String searchText, Pageable pageable);
}
