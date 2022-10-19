package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Logging;
import cn.wolfcode.qo.UserQuery;
import cn.wolfcode.service.ILoggingService;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/logging")
public class LoggingController {

    private final ILoggingService loggingService;

    public LoggingController(ILoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @RequestMapping("/list")
    public JsonResult<Page<Logging>> query(UserQuery qo) {
        return JsonResult.success(loggingService.query(qo));
    }

}
