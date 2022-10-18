package cn.wolfcode.mapper;

import cn.wolfcode.domain.BulletMsgSensitive;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface BulletMsgSensitiveMapper {

    Long selectForCount(QueryObject qo);

    List<BulletMsgSensitive> selectForList(QueryObject qo);

    int deleteByPrimaryKey(Long id);

    int insert(BulletMsgSensitive record);

    BulletMsgSensitive selectByPrimaryKey(Long id);

    List<BulletMsgSensitive> selectAll();

    int updateByPrimaryKey(BulletMsgSensitive record);
}