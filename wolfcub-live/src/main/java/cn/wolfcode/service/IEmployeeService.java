package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.vo.LoginInfo;

public interface IEmployeeService extends IService<Employee, Long> {

    String login(String username, String password);
    
    int resetPassword(String userId, String password, LoginInfo info);
}
