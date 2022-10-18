package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.LoggerModule;
import cn.wolfcode.mapper.LoggerMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.LoggerService;
import cn.wolfcode.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoggerServiceImpl implements LoggerService {
	private final LoggerMapper mapper;
	
	public LoggerServiceImpl(LoggerMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public void save(LoggerModule obj) {
		mapper.insert(obj);
	}
	
	@Override
	public void updateById(LoggerModule obj) {
		throw new RuntimeException("日志不支持修改");
	}
	
	@Override
	public LoggerModule findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Page<LoggerModule> query(QueryObject qo) {
		long total = mapper.selectForCount(qo);
		if (total == 0) {
			return Page.empty(qo.getCurrent(), qo.getLimit());
		}
		List<LoggerModule> list = mapper.selectForList(qo);
		return new Page<>(qo.getCurrent(), qo.getLimit(), total, list);
	}
}
