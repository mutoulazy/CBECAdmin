package net.sppan.base.mapper;

import net.sppan.base.entity.UserScore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserScore record);

    int insertSelective(UserScore record);

    UserScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserScore record);

    int updateByPrimaryKey(UserScore record);

    UserScore selectByTestpaperIdAndUserId(UserScore record);
}