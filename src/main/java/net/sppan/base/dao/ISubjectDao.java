package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/8.
 */
@Repository
public interface ISubjectDao extends IBaseDao<Subject, Integer>{
//    @Modifying
//    @Query(nativeQuery = true,value = "DELETE FROM tb_role_resource WHERE resource_id = :id")
//    void deleteGrant(@Param("id") Integer id);

    Page<Subject> findAllByNameContaining(String searchText, Pageable pageable);

    List<Subject> findAllByOrderByParentAscIdAscSortAsc();
}
