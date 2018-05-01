package net.sppan.base.service;

import net.sppan.base.entity.Course;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by mutoulazy on 2018/5/1.
 */
public interface ICourseService extends IBaseService<Course, Integer>{
    /**
     * 修改或者新增资源
     * @param course
     */
    void saveOrUpdate(Course course);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Course> findAllByLike(String searchText, PageRequest pageRequest);
}
