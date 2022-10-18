package cn.wolfcode.mapper;

import cn.wolfcode.domain.User;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("id") Long id, @Param("deleted") boolean deleted);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);

    Long selectForCount(QueryObject qo);

    List<User> selectForList(QueryObject qo);

    void changeStatus(@Param("id") Long id, @Param("status") String status);

    User selectByUsername(String username);

    Integer selectCountByDate(@Param("minNowTime") Date minNowTime, @Param("maxNowTime") Date maxNowTime);
}
