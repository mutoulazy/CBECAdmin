package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.QuestionBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Repository
public interface IQuestionBankDao extends IBaseDao<QuestionBank, Integer> {
    Page<QuestionBank> findAllByNameContaining(String searchText, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM tb_testpaper_bank WHERE question_id = :id")
    void deleteGrant(@Param("id") Integer id);
}
