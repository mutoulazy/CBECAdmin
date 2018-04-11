package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/11.
 */
@Repository
public interface IEnterpriseDao  extends IBaseDao<Enterprise, Integer>{

    Page<Enterprise> findAllByNameContaining(String searchText, Pageable pageable);

    List<Enterprise> findAllByOrderByParentAscIdAscSortAsc();
}
