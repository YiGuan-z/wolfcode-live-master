package cn.wolfcode.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

}