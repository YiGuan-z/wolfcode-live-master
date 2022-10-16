package cn.wolfcode.web.controller;

import cn.wolfcode.domain.BulletMsg;
import cn.wolfcode.qo.BulletMsgQuery;
import cn.wolfcode.service.IBulletMsgService;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bulletMsg")
public class BulletMsgController {

    private final IBulletMsgService bulletMsgService;

    public BulletMsgController(IBulletMsgService bulletMsgService) {
        this.bulletMsgService = bulletMsgService;
    }

    @RequestMapping("/list")
    public JsonResult<Page<BulletMsg>> query(BulletMsgQuery qo) {
        return JsonResult.success(bulletMsgService.query(qo));
    }

    @RequestMapping("/changeStatus")
    public JsonResult<?> changeStatus(Long id, Integer status) {
        bulletMsgService.changeStatus(id, status);
        return JsonResult.success();
    }

    @RequestMapping("/saveOrUpdate")
    public JsonResult<?> saveOrUpdate(@RequestBody BulletMsg bulletMsg) {
        if (bulletMsg.getId() != null) {
            bulletMsgService.updateById(bulletMsg);
        } else {
            bulletMsgService.save(bulletMsg);
        }

        return JsonResult.success();
    }

    @RequestMapping("/delete")
    public JsonResult<?> delete(Long id) {
        if (id == null) {
            return JsonResult.failed("删除的 id 不能为空!");
        }

        bulletMsgService.deleteById(id);
        return JsonResult.success();
    }
}
