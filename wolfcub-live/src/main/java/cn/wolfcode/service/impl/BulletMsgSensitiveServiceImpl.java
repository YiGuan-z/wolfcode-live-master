package cn.wolfcode.service.impl;

import cn.wolfcode.domain.BulletMsgSensitive;
import cn.wolfcode.mapper.BulletMsgSensitiveMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IBulletMsgSensitiveService;
import cn.wolfcode.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletMsgSensitiveServiceImpl implements IBulletMsgSensitiveService {

    private final BulletMsgSensitiveMapper mapper;

    public BulletMsgSensitiveServiceImpl(BulletMsgSensitiveMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Page<BulletMsgSensitive> query(QueryObject qo) {
        Long count = mapper.selectForCount(qo);
        if (count == null) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<BulletMsgSensitive> bulletMsgSensitives = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), count, bulletMsgSensitives);
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
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public BulletMsgSensitive findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
