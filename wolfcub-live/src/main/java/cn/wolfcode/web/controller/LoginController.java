package cn.wolfcode.web.controller;

import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.utils.TokenManager;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.LoginInfo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
public class LoginController {
	
	private final IEmployeeService employeeService;
	
	public LoginController(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/login")
	public JsonResult<String> login(String username, String password) {
		try {
			// 登录业务
			String token = employeeService.login(username, password);
			return JsonResult.success(token);
		} catch (Exception e) {
			return JsonResult.failed(400, e.getMessage());
		}
	}
	
	@RequestMapping("/logout")
	public JsonResult<?> logout(@RequestHeader(value = TokenManager.TOKEN_NAME, required = false) String token) {
		TokenManager.removeInfo(token);
		return JsonResult.success();
	}
	
	@PostMapping("/resetPassword")
	public JsonResult<?> reset(@RequestHeader(value = TokenManager.TOKEN_NAME, required = false) String token,
							   @RequestBody HashMap<String, String> map) {
		try {
			final var info = TokenManager.getInfo(token);
			if (Objects.isNull(info)) {
				return JsonResult.failed("请登陆账户");
			}
			final var password = map.get("password");
			final var userId = map.get("userId");
			if (password == null || password.length() == 0) {
				return JsonResult.success("请输入密码");
			}
			if (userId == null || userId.length() == 0) {
				return JsonResult.success("需要用户Id");
			}
			employeeService.resetPassword(userId, password, info);
			return JsonResult.success();
		} catch (Exception e) {
			return JsonResult.failed(e.getMessage());
		}
	}
}
