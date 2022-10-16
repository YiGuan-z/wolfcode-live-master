package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.vo.Page;
import cn.wolfcode.vo.TreeData;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private final DepartmentMapper mapper;

    public DepartmentServiceImpl(DepartmentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Department obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(Department obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> findAll() {
        return mapper.selectAll();
    }

    @Override
    public Page<Department> query(QueryObject qo) {
        Long total = mapper.selectForCount(qo);
        if (total == 0) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<Department> list = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), total, list);
    }

    @Override
    public List<TreeData> queryTreeList() {
        // 1. 先查询出所有的部门对象
        List<Department> departments = mapper.selectAll();
        // 2. 将所有部门转换为 TreeData 对象, 并将其存入 map, key=id, value=TreeData 对象
        Map<Long, TreeData> cache = departments.stream() // Stream<Department>
                .map(d -> new TreeData(d.getId(), d.getName())) // Stream<TreeData>
                .collect(Collectors.toMap(TreeData::getId, t -> t)); // key=id, value=TreeData

        // 3. 遍历整个部门集合
        return departments.stream() // Stream<Department>
                .filter(d -> {
                    // 判断当前部门是否有父部门
                    if (d.getParent() != null) {
                        // 获取到当前部门的父节点
                        TreeData parent = cache.get(d.getParent().getId());
                        // 将当前节点加入到父部门的 children 中
                        parent.getChildren().add(cache.get(d.getId()));
                    }

                    return d.getParent() == null;
                }) // Stream<Department> 只有 根节点
                .map(d -> cache.get(d.getId())) // Stream<TreeData>
                .collect(Collectors.toList());
    }

    public List<TreeData> queryTreeList2() {
        List<TreeData> treeList = new ArrayList<>();
        Map<Long, TreeData> cache = new HashMap<>();

        // 1. 先查询出所有的部门对象
        List<Department> departments = mapper.selectAll();
        // 2. 将所有部门转换为 TreeData 对象, 并将其存入 map, key=id, value=TreeData 对象
        TreeData data = null;
        for (Department department : departments) {
            data = new TreeData(department.getId(), department.getName());
            // 如果没有父部门, 说明就是根元素
            if (department.getParent() == null) {
                // 将根元素存入最终的结果
                treeList.add(data);
            }
            // 将当前转换后的数据存入 map
            cache.put(data.getId(), data);
        }
        // 3. 遍历整个部门集合
        for (Department child : departments) {
            // 3.1 获取到当前部门, 用当前部门的 id, 从 map 中查找父元素
            Department parent = child.getParent();
            if (parent != null) {
                TreeData parentData = cache.get(parent.getId());
                // 3.2 如果父元素存在, 就直接将自己加入到父元素的 children 中去
                parentData.getChildren().add(cache.get(child.getId()));
            }
        }
        return treeList;
    }

    public List<TreeData> queryTreeList1() {
        // 1. 创建 TreeData 集合
        List<TreeData> treeList = new ArrayList<>();
        // 2. 获取所有根元素
        // 狼码教育
        List<Department> roots = mapper.selectChildrenByParentId(null);

        TreeData treeData = null;
        // [狼码教育]
        for (Department root : roots) {
            // 将根节点的部门对象转换为 树对象
            treeData = new TreeData(root.getId(), root.getName());
            // 并将其存入最终的集合中
            treeList.add(treeData);

            // 将当前树对象作为根节点, 并且用当前节点的 id 作为初始 id 用于查询是否有子元素
            this.treeListRecursion(treeData, root.getId());
        }
        return treeList;
    }

    private void treeListRecursion(TreeData treeData, Long parentId) {
        // 先基于当前根元素 id 来查询子元素
        // 1 轮: 查狼码教育下所有的子部门
        // 2 轮: 经过总经办/产品部/销售部各自进行递归查询
        //  总经办与产品部没有子节点
        //  销售部有子节点, 因此销售部继续往下执行
        List<Department> departments = mapper.selectChildrenByParentId(parentId);
        // 递归出口, 如果当前部门没有子部门了, 就不再递归了
        if (departments == null || departments.size() == 0) {
            return;
        }

        // 将当前元素的子元素作为递归数据
        TreeData child = null;
        // 遍历当前查到的子部门集合, 并将每个对象转换为树对象
        for (Department department : departments) {
            // 将每个部门对象转换为树对象
            child = new TreeData(department.getId(), department.getName());
            // 将当前树对象加入到当前这棵树的根节点中
            treeData.getChildren().add(child);


            this.treeListRecursion(child, child.getId());
        }
    }
}
