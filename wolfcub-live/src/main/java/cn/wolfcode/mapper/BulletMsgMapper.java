package cn.wolfcode.mapper;

import cn.wolfcode.domain.BulletMsg;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BulletMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BulletMsg record);

    BulletMsg selectByPrimaryKey(Long id);

    List<BulletMsg> selectAll();

    int updateByPrimaryKey(BulletMsg record);

    Long selectForCount(QueryObject qo);

    List<BulletMsg> selectForList(QueryObject qo);

    void changeStatus(@Param("id") Long id, @Param("status") Integer status);

    int selectMsgCountByVideoId(String videoId);

    List<BulletMsg> selectByVideoId(String videoId);

    List<BulletMsg> selectRollingMessages(@Param("videoId") String videoId, @Param("currentVideoTime") int currentVideoTime);
}
