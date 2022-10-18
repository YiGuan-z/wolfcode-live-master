package cn.wolfcode.mapper;

import cn.wolfcode.domain.Video;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Video record);

    Video selectByPrimaryKey(String id);

    int updateByPrimaryKey(Video record);

    void deleteByPrimaryKey(@Param("id") Long id, @Param("deleted") boolean deleted);

    void changeStatus(@Param("id") Long id, @Param("status") String status);

    Long selectForCount(QueryObject qo);

    List<Video> selectForList(QueryObject qo);
}
