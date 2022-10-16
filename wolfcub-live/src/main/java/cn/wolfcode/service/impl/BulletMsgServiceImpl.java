package cn.wolfcode.service.impl;

import cn.wolfcode.domain.BulletMsg;
import cn.wolfcode.domain.User;
import cn.wolfcode.mapper.BulletMsgMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IBulletMsgService;
import cn.wolfcode.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletMsgServiceImpl implements IBulletMsgService {

    private final BulletMsgMapper mapper;

    public BulletMsgServiceImpl(BulletMsgMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Page<BulletMsg> query(QueryObject qo) {
        Long total = mapper.selectForCount(qo);
        if (total == 0) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<BulletMsg> list = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), total, list);
    }

    @Override
    public void save(BulletMsg obj) {
        mapper.insert(obj);
    }

    @Override
    public void updateById(BulletMsg obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public BulletMsg findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BulletMsg> findAll() {
        return mapper.selectAll();
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        mapper.changeStatus(id, status);
    }

    @Override
    public int getMsgCountByVideoId(String videoId) {
        return mapper.selectMsgCountByVideoId(videoId);
    }

    @Override
    public List<BulletMsg> getListByVideoId(String videoId) {
        return mapper.selectByVideoId(videoId);
    }

    @Override
    public List<BulletMsg> getRollingMessages(String videoId, int currentVideoTime) {
        return mapper.selectRollingMessages(videoId, currentVideoTime);
    }
}
