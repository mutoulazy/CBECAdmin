package net.sppan.base.service;

import net.sppan.base.entity.QuestionBank;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by mutoulazy on 2018/4/23.
 */
public interface IQuestionBankService extends IBaseService<QuestionBank, Integer>{
    /**
     * 修改或者新增资源
     * @param questionBank
     */
    void saveOrUpdate(QuestionBank questionBank);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<QuestionBank> findAllByLike(String searchText, PageRequest pageRequest);
}
