package net.sppan.base.service;

import net.sppan.base.entity.Recruit;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by mutoulazy on 2018/4/11.
 */
public interface IRecruitService extends IBaseService<Recruit, Integer>{
    /**
     * 修改或者新增资源
     * @param recruit
     */
    void saveOrUpdate(Recruit recruit);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Recruit> findAllByLike(String searchText, PageRequest pageRequest);
}
