package cn.wolfcode.service;

import cn.wolfcode.vo.TreeData;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void treeList() throws Exception {
        List<TreeData> treeList = departmentService.queryTreeList();
        String s = JSON.toJSONString(treeList);
        System.out.println(s);
    }
}
