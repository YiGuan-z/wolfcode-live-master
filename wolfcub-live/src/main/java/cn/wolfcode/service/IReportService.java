package cn.wolfcode.service;

import cn.wolfcode.domain.Report;

public interface IReportService extends IService<Report, Long> {

    void reportSave(Report obj);

    void reportStatus(Long id, Integer status);
    //通过id获取审批数据
     Report selectInfoByPrimaryKey(Long id);
}
