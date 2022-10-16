package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BulletMsgSensitive {

    private Long id;
    private String sensitiveMsg;
    private String showMsg;
    private Date createTime;
    private Date updateTime;
}
