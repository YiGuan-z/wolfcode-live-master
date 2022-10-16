package cn.wolfcode.web.controller.user;

import cn.wolfcode.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class VerifyCodeController {

    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 生成长度为 5 的验证码
            String code = VerifyCodeUtils.generateVerifyCode(2);
            // 将验证码存入 SESSION
            req.getSession().setAttribute("verifyCode", code);
            // 设置响应类型为图片
            resp.setContentType("image/png");
            // 将验证码输出到响应流(浏览器能看到)
            VerifyCodeUtils.outputImage(110, 40, resp.getOutputStream(), code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
