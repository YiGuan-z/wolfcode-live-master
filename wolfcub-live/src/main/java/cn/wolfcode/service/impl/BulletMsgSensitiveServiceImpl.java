package cn.wolfcode.service.impl;

import cn.wolfcode.domain.BulletMsgSensitive;
import cn.wolfcode.mapper.BulletMsgSensitiveMapper;
import cn.wolfcode.service.IBulletMsgSensitiveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletMsgSensitiveServiceImpl implements IBulletMsgSensitiveService {

    private final BulletMsgSensitiveMapper mapper;

    public BulletMsgSensitiveServiceImpl(BulletMsgSensitiveMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<BulletMsgSensitive> findAll() {
        return mapper.selectAll();
    }

    @Override
    public void save(BulletMsgSensitive obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(BulletMsgSensitive obj) {
        mapper.insert(obj);
    }

    @Override
    public BulletMsgSensitive findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
