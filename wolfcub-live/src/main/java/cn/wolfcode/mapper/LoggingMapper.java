package cn.wolfcode.mapper;

import cn.wolfcode.domain.Logging;
import cn.wolfcode.domain.User;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface LoggingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logging record);

    Logging selectByPrimaryKey(Long id);

    List<Logging> selectAll();

    int updateByPrimaryKey(Logging record);

    Long selectForCount(QueryObject qo);

    List<Logging> selectForList(QueryObject qo);
}