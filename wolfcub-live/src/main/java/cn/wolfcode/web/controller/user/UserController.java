package cn.wolfcode.web.controller.user;

import cn.wolfcode.domain.User;
import cn.wolfcode.qo.UserQuery;
import cn.wolfcode.service.IUserService;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public JsonResult<Page<User>> query(UserQuery qo) {
        return JsonResult.success(userService.query(qo));
    }

    @RequestMapping("/changeStatus")
    public JsonResult<?> changeStatus(Long id, String status) {
        userService.changeStatus(id, status);
        return JsonResult.success();
    }

    @RequestMapping("/saveOrUpdate")
    public JsonResult<?> saveOrUpdate(@RequestBody User user) {
        if (user.getId() != null) {
            userService.updateById(user);
        } else {
            userService.save(user);
        }

        return JsonResult.success();
    }

    @RequestMapping("/delete")
    public JsonResult<?> delete(Long id, boolean deleted) {
        if (id == null) {
            return JsonResult.failed("删除的 id 不能为空!");
        }

        userService.deleteById(id, deleted);
        return JsonResult.success();
    }
}
