package net.sppan.base.mapper;


import net.sppan.base.entity.QuestionBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface TbQuestionBankMapper {
    List<QuestionBank> selectByTestPaperIdAndType(@Param("testpaperId") Integer testpaperId, @Param("type") Integer type);
}