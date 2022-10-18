package cn.wolfcode.service.impl;


import cn.wolfcode.domain.Report;
import cn.wolfcode.mapper.ReportMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IReportService;
import cn.wolfcode.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {

    private final ReportMapper mapper;

    public ReportServiceImpl(ReportMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Report obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(Report obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Report findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Report> findAll() {
        return mapper.selectAll();
    }

    @Override
    public Page<Report> query(QueryObject qo) {
        Long count = mapper.selectForCount(qo);
        if (count == null) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<Report> reports = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), count, reports);
    }
    @Override
    public void reportSave(Report obj) {
        mapper.save(obj);
    }

    @Override
    public void reportStatus(Long id, Integer status) {
        mapper.reportStatus(id, status);
    }
}
