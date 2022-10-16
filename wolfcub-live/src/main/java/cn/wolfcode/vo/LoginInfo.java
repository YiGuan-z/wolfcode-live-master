package cn.wolfcode.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 专门用于封装登录后返回用户信息的对象
 */
@Getter
@Setter
public class LoginInfo {

    private Long id;
    private String name;
    private String username;
    private String avatar;
}
