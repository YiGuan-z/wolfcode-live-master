package cn.wolfcode.service.impl;

import cn.wolfcode.domain.User;
import cn.wolfcode.mapper.UserMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IUserService;
import cn.wolfcode.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Page<User> query(QueryObject qo) {
        Long total = mapper.selectForCount(qo);
        if (total == 0) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<User> users = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), total, users);
    }

    @Override
    public void save(User obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(User obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public void deleteById(Long id, boolean deleted) {
        mapper.deleteByPrimaryKey(id, deleted);
    }

    @Override
    public User findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void changeStatus(Long id, String status) {
        mapper.changeStatus(id, status);
    }
}
