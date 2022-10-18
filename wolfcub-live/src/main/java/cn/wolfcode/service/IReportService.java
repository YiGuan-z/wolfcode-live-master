package cn.wolfcode.service;

import cn.wolfcode.domain.Report;

public interface IReportService extends IService<Report, Long> {

    void reportSave(Report obj);

    void reportStatus(Long id, Integer status);
    Report selectInfoByPrimaryKey(Long id);
}
