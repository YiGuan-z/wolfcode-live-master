package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {

    private Long id;
    private String name;
    private String sn;
    private Department parent;
}