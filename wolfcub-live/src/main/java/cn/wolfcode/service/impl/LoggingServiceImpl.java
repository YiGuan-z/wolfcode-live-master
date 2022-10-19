package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Logging;
import cn.wolfcode.mapper.LoggingMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.ILoggingService;
import cn.wolfcode.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wby
 * @version 1.0
 * @date 2022/10/18 19:00
 */
@Service
public class LoggingServiceImpl implements ILoggingService {

    private final LoggingMapper mapper;

    public LoggingServiceImpl(LoggingMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Logging obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(Logging obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public Logging findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Logging> query(QueryObject qo) {
        Long total = mapper.selectForCount(qo);
        if (total == 0) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<Logging> loggings = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), total, loggings);
    }
}
