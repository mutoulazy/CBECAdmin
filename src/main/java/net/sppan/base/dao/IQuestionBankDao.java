package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.QuestionBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Created by mutoulazy on 2018/4/23.
 */
@Repository
public interface IQuestionBankDao extends IBaseDao<QuestionBank, Integer> {
    Page<QuestionBank> findAllByNameContaining(String searchText, Pageable pageable);
}
