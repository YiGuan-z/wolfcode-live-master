package cn.wolfcode.web.controller.user;

import cn.wolfcode.domain.User;
import cn.wolfcode.service.IUserService;
import cn.wolfcode.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代星宇
 * @content 用户注册
 * @date 2022/10/16 17:06
 * @Version cn.wolfcode.web.controller
 */
@RestController("/register")
public class RegisterController {
    private final IUserService userService;
    
    public RegisterController(IUserService userService) {
        this.userService = userService;
    }
    
    /**
     * 查重
     */
    @RequestMapping("/repetition")
    public JsonResult<?> register(String username){
        Boolean repetition = userService.repetition(username);
        return JsonResult.success(repetition);
    }

    /**
     * 注册
     * @param user 用户对象
     * @return 修改成功
     */
    @RequestMapping("/save")
    public JsonResult<?> save(@RequestBody User user){
        userService.save(user);
        return JsonResult.success();
    }
}
