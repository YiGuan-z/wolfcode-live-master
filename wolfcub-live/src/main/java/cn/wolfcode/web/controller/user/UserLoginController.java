package cn.wolfcode.web.controller.user;

import cn.wolfcode.service.IUserService;
import cn.wolfcode.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author 代星宇&cheng
 * @date 2022/10/16 17:20
 * @Version cn.wolfcode.web.controller
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserLoginController {
	private final IUserService userService;
	private final HttpSession session;
	
	private static final Integer MAX_ERR_NUM = 3;
	
	public UserLoginController(IUserService userService, HttpSession session) {
		this.userService = userService;
		this.session = session;
	}
	
	@RequestMapping("/login")
	public JsonResult<?> login1(String username, String password, String verifyCode) {
		var errNum = (Integer) session.getAttribute("errNum");
		try {
			if (Objects.isNull(username)) throw new RuntimeException("未输入用户名");
			final var code = (String) session.getAttribute("code");
			final var token = userService.login(username, password, verifyCode, code);
			log.info("{}已成功登陆", username);
			return JsonResult.success(token);
		} catch (Exception e) {
			log.error("{}登陆失败，原因是{}", username, e.getMessage());
			if (Objects.isNull(errNum)) {
				session.setAttribute("errNum", 0);
				log.warn("{}第一次登陆失败", username);
				//如果登陆失败次数大于等于错误次数
			} else if (errNum >= MAX_ERR_NUM) {
				return JsonResult.failed("登陆错误，你已被拉黑");
			}
			errNum = (Integer) session.getAttribute("errNum");
			session.setAttribute("errNum", errNum + 1);
			errNum = (Integer) session.getAttribute("errNum");
			log.error("{}登陆失败次数{}", username, session.getAttribute("errNum"));
			return JsonResult.failed(400, e.getMessage() + "失败" + errNum + "次");
		}
	}
}
