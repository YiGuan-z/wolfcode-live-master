package cn.wolfcode.domain;


import cn.hutool.system.UserInfo;
import cn.wolfcode.vo.LoginInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Beans;
import java.util.Date;

@Getter
@Setter
public class Employee {
	
	private Long id;
	private String avatar;
	private String username;
	private String name;
	@JsonIgnore
	private String password;
	private String email;
	private Integer age;
	private Boolean admin;
	private Department dept;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	
	public static Employee of(LoginInfo info) {
		final var employee = new Employee();
		employee.dept = new Department();
		if (info == null) {
			return employee;
		}
		BeanUtils.copyProperties(info, employee);
		return employee;
	}
	
}