package cn.wolfcode.mapper;

import cn.wolfcode.domain.Video;
import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Video record);

    Video selectByPrimaryKey(String id);

    int updateByPrimaryKey(Video record);
}
