package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.EmployeeQuery;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.utils.Log;
import cn.wolfcode.utils.TokenManager;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.LoginInfo;
import cn.wolfcode.vo.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private final IEmployeeService employeeService;
	
	public EmployeeController(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/list")
	public JsonResult<Page<Employee>> query(EmployeeQuery qo) {
		return JsonResult.success(employeeService.query(qo));
	}
	
	@RequestMapping("/getInfo")
	public JsonResult<LoginInfo> getInfo(@RequestHeader(value = TokenManager.TOKEN_NAME, required = false) String token) {
		// 从请求头中获取 token
		if (!StringUtils.hasText(token)) {
			return JsonResult.failed(400, "token 不能为空");
		}
		
		LoginInfo info = TokenManager.getInfo(token);
		return JsonResult.success(info);
	}
	
	@RequestMapping("/saveOrUpdate")
	public JsonResult<?> saveOrUpdate(@RequestBody Employee employee) {
		if (employee.getId() != null) {
			employeeService.updateById(employee);
		} else {
			employeeService.save(employee);
		}
		
		return JsonResult.success();
	}
	
	@PostMapping("/updateInfo")
	@Log("更新用户信息")
	public JsonResult<?> updateByInfo(@RequestBody LoginInfo loginInfo) {
		try {
			final var employee = Employee.of(loginInfo);
			employeeService.updateById(employee);
			return JsonResult.success(loginInfo);
		} catch (Exception e) {
			return JsonResult.success(e.getMessage());
		}
	}
	
	@RequestMapping("/delete")
	public JsonResult<?> delete(Long id) {
		if (id == null) {
			return JsonResult.failed("删除的 id 不能为空!");
		}
		
		employeeService.deleteById(id);
		return JsonResult.success();
	}
}
