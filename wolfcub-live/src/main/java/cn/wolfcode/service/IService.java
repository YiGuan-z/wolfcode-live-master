package cn.wolfcode.service;

import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.vo.Page;

import java.util.Collections;
import java.util.List;

public interface IService<T, PK> {

    /**
     * 新增对象
     *
     * @param obj 要保存的对象
     */
    void save(T obj);

    /**
     * 基于 id 更新
     *
     * @param obj 要更新的对象, 需要有 id
     */
    void updateById(T obj);

    /**
     * 基于主键 id 删除
     *
     * @param id 主键 id 的值
     */
    default void deleteById(PK id){}

    /**
     * 逻辑删除
     *
     * @param id      要删除的 id
     * @param deleted 是否删除
     */
    default void deleteById(PK id, boolean deleted) {}

    /**
     * 基于 id 查找数据
     *
     * @param id 要查找数据的 id
     * @return 查找到的数据
     */
    T findById(PK id);

    /**
     * 查找所有
     *
     * @return 返回所有列表
     */
    default List<T> findAll() {
        return Collections.emptyList();
    }

    /**
     * 分页查询
     *
     * @param qo 查询对象
     * @return 返回分页结果
     */
    default Page<T> query(QueryObject qo) {
        return Page.empty(qo.getCurrent(), qo.getLimit());
    }
}
