package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import cn.wolfcode.vo.TreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/listAll")
    public JsonResult<List<Department>> listAll() {
        return JsonResult.success(departmentService.findAll());
    }

    @RequestMapping("/list")
    public JsonResult<Page<Department>> query(QueryObject qo) {
        return JsonResult.success(departmentService.query(qo));
    }

    @RequestMapping("/treeList")
    public JsonResult<List<TreeData>> treeList() {
        // [{id:1, label: '', children: [{id:1, label: ''}]},{...}]
        List<TreeData> treeList = departmentService.queryTreeList();
        return JsonResult.success(treeList);
    }

    @RequestMapping("/saveOrUpdate")
    public JsonResult<?> saveOrUpdate(@RequestBody Department department) {
        if (department.getId() != null) {
            departmentService.updateById(department);
        } else {
            departmentService.save(department);
        }

        return JsonResult.success();
    }

    @RequestMapping("/delete")
    public JsonResult<?> delete(Long id) {
        if (id == null) {
            return JsonResult.failed("删除的 id 不能为空!");
        }

        departmentService.deleteById(id);
        return JsonResult.success();
    }
}
