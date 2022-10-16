package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.vo.TreeData;

import java.util.List;

public interface IDepartmentService extends IService<Department, Long> {

    /**
     * 查找部门树结构数据
     *
     * @return 返回树结构数据
     */
    List<TreeData> queryTreeList();
}
