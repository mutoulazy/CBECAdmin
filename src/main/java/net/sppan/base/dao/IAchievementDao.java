package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Achievement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Created by mutoulazy on 2018/4/11.
 */
@Repository
public interface IAchievementDao extends IBaseDao<Achievement, Integer>{
    Page<Achievement> findAllByNameContaining(String searchText, Pageable pageable);
}