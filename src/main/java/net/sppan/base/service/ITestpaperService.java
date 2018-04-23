package net.sppan.base.service;

import net.sppan.base.entity.Testpaper;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by mutoulazy on 2018/4/23.
 */
public interface ITestpaperService extends IBaseService<Testpaper, Integer>{
    /**
     * 修改或者新增资源
     * @param testpaper
     */
    void saveOrUpdate(Testpaper testpaper);

    /**
     * 关键字分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Testpaper> findAllByLike(String searchText, PageRequest pageRequest);
}
