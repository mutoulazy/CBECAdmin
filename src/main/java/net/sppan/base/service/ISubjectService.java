package net.sppan.base.service;

import net.sppan.base.entity.Subject;
import net.sppan.base.service.support.IBaseService;
import net.sppan.base.vo.ZtreeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 专业服务类
 * Created by mutoulazy on 2018/4/8.
 */
public interface ISubjectService extends IBaseService<Subject, Integer>{

    /**
     * 修改或者新增资源
     * @param subject
     */
    void saveOrUpdate(Subject subject);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Subject> findAllByLike(String searchText, PageRequest pageRequest);
}
