package cn.wolfcode.web.controller;

import cn.wolfcode.domain.BulletMsgSensitive;
import cn.wolfcode.qo.BulletMsgSensitiveQuery;
import cn.wolfcode.service.IBulletMsgSensitiveService;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/bulletMsgSensitive")
public class BulletMsgSensitiveController {

    private final IBulletMsgSensitiveService bulletMsgSensitiveService;

    public BulletMsgSensitiveController(IBulletMsgSensitiveService bulletMsgSensitiveService) {
        this.bulletMsgSensitiveService = bulletMsgSensitiveService;
    }

    @RequestMapping("/list")
    private JsonResult<Page<BulletMsgSensitive>> list(BulletMsgSensitiveQuery bu) {
        return JsonResult.success(bulletMsgSensitiveService.query(bu));
    }

    @RequestMapping("/delete")
    private JsonResult<?> delete(Long id) {
        if (id == null) {
            return JsonResult.failed("删除操作Id不能为空");
        } else {
            bulletMsgSensitiveService.deleteById(id);
        }
        return JsonResult.success();
    }

    @RequestMapping("/saveOrUpdate")
    private JsonResult<?> saveOrUpdate(@RequestBody BulletMsgSensitive bulletMsgSensitive) {
        if (bulletMsgSensitive.getId() == null) {
            bulletMsgSensitiveService.save(bulletMsgSensitive);
        } else {
            bulletMsgSensitive.setUpdateTime(new Date());
            bulletMsgSensitiveService.updateById(bulletMsgSensitive);
        }
        return JsonResult.success();
    }
}
