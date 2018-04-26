package net.sppan.base.mapper;

import net.sppan.base.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by mutoulazy on 2018/4/26.
 */
@Mapper
public interface UserMapper {
    List<User> getAll();
}
