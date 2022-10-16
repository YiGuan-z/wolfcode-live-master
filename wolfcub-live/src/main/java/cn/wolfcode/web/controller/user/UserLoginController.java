package cn.wolfcode.web.controller.user;

import cn.wolfcode.service.IUserService;
import cn.wolfcode.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author 代星宇
 * @date 2022/10/16 17:20
 * @Version cn.wolfcode.web.controller
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession session;

    private static final Integer ERR_NUM=3;

    @RequestMapping("/login")
    public JsonResult<?> login(String username, String password, String verifyCode) {
        Integer num = (Integer) session.getAttribute("num");
        if (num == null) {
            session.setAttribute("num", 0);
        }
        num = (Integer) session.getAttribute("num");
        session.setAttribute("num", num+1);
        try {
            if(num>ERR_NUM){
                throw new RuntimeException("登录错误次数超过"+ERR_NUM+"次");
            }
            String code = (String) session.getAttribute("verifyCode");
            // 登录业务
            String token = userService.login(username, password, verifyCode, code);
            session.setAttribute("num",0);
            return JsonResult.success(token);
        } catch (Exception e) {
            return JsonResult.failed(400, e.getMessage());
        }
    }
}
