package cn.wolfcode.mapper;

import cn.wolfcode.domain.BulletMsg;
import cn.wolfcode.domain.Report;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper {

    int save(Report record);

    Long selectForCount(QueryObject qo);

    List<Report> selectForList(QueryObject qo);

    int deleteByPrimaryKey(Long id);

    int insert(Report record);

    Report selectByPrimaryKey(Long id);

    List<Report> selectAll();

    int updateByPrimaryKey(Report record);

    void reportStatus(@Param("id") Long id, Integer status);
}