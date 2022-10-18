package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.LoggerModule;
import cn.wolfcode.domain.User;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface LoggerMapper {
	
	int insert(LoggerModule record);
	
	LoggerModule selectByPrimaryKey(Long id);
	
	List<LoggerModule> selectAll();
	
	List<LoggerModule> selectForList(QueryObject qo);
	long selectForCount(QueryObject qo);
}
