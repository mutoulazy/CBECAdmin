package net.sppan.base.service;

import net.sppan.base.entity.Enterprise;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 企业信息服务类
 * Created by mutoulazy on 2018/4/11.
 */
public interface IEnterpriseService extends IBaseService<Enterprise, Integer>{
    /**
     * 修改或者新增资源
     * @param enterprise
     */
    void saveOrUpdate(Enterprise enterprise);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Enterprise> findAllByLike(String searchText, PageRequest pageRequest);
}
