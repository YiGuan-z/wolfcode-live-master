package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    long selectForCount(QueryObject qo);

    List<Employee> selectForList(QueryObject qo);

    Employee selectByUsername(String username);
    
    int resetPassword(@Param("id") Long id, @Param("password") String password);
}