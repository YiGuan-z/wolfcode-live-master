package cn.wolfcode.web.controller;


import cn.wolfcode.domain.BulletMsg;
import cn.wolfcode.domain.Report;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IReportService;
import cn.wolfcode.utils.Log;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping("/list")
    private JsonResult<Page<Report>> list(QueryObject qo) {
        return JsonResult.success(reportService.query(qo));
    }

    @RequestMapping("/update")
    private JsonResult<?> update(@RequestBody Report report) {
        if (report.getId() == null) {
            return JsonResult.failed("ID不能为空");
        } else {
            reportService.updateById(report);
        }
        return JsonResult.success();
    }
    @Log(value = "更改用户信息",level = Log.Level.info)
    @RequestMapping("/reportStatus")
    public JsonResult<?> reportStatus(Long id, Integer status) {
        reportService.reportStatus(id, status);
        return JsonResult.success();
    }

    @RequestMapping("/save")
    public JsonResult<?> save(@RequestBody Report report) {
        System.out.println(report);
        reportService.reportSave(report);
        return JsonResult.success();
    }
}
