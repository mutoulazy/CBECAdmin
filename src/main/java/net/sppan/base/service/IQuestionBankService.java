package net.sppan.base.service;

import net.sppan.base.entity.QuestionBank;
import net.sppan.base.service.support.IBaseService;
import net.sppan.base.vo.ZtreeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

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


    /**
     * 获取试题
     * @param paperId
     * @return
     */
    List<ZtreeView> tree(int paperId);
}
