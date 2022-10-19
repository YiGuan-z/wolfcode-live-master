package cn.wolfcode.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Logging {
    /** */
    private Long id;

    /** 用户id用于判断用户状态*/
    private User userId;

    /** 日志等级*/
    private String level;

    /** */
    private String msg;

    /** 日志创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

}