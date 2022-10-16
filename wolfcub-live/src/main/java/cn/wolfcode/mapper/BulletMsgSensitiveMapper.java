package cn.wolfcode.mapper;

import cn.wolfcode.domain.BulletMsgSensitive;
import java.util.List;

public interface BulletMsgSensitiveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BulletMsgSensitive record);

    BulletMsgSensitive selectByPrimaryKey(Long id);

    List<BulletMsgSensitive> selectAll();

    int updateByPrimaryKey(BulletMsgSensitive record);
}