package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Created by mutoulazy on 2018/4/11.
 */
@Repository
public interface IRecruitDao extends IBaseDao<Recruit, Integer>{

    Page<Recruit> findAllByNameContaining(String searchText, Pageable pageable);
}