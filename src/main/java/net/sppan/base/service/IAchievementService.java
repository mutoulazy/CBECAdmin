package net.sppan.base.service;

import net.sppan.base.entity.Achievement;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by mutoulazy on 2018/4/11.
 */
public interface IAchievementService extends IBaseService<Achievement, Integer> {
    /**
     * 修改或者新增资源
     * @param achievement
     */
    void saveOrUpdate(Achievement achievement);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Achievement> findAllByLike(String searchText, PageRequest pageRequest);
}
