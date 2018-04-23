package net.sppan.base.service;

import net.sppan.base.entity.UserScore;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by mutoulazy on 2018/4/23.
 */
public interface IUserScoreService extends IBaseService<UserScore, Integer>{
    /**
     * 修改或者新增资源
     * @param userScore
     */
    void saveOrUpdate(UserScore userScore);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<UserScore> findAllByLike(String searchText, PageRequest pageRequest);
}
