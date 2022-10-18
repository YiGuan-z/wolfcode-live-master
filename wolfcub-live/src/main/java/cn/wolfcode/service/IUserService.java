package cn.wolfcode.service;

import cn.wolfcode.domain.User;
import cn.wolfcode.vo.Today;

public interface IUserService extends IService<User, Long> {

    void changeStatus(Long id, String status);

    String login(String username, String password, String verifyCode, String code);

    Boolean repetition(String username);

    Today selectByCount();

    void updateByFile(String pathJsp, Long id);
}
