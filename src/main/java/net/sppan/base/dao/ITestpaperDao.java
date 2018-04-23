package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Testpaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Repository
public interface ITestpaperDao extends IBaseDao<Testpaper, Integer>{
    Page<Testpaper> findAllByNameContaining(String searchText, Pageable pageable);
}
