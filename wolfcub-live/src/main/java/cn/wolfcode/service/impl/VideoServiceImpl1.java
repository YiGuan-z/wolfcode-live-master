package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Video;
import cn.wolfcode.mapper.VideoMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IVideoService1;
import cn.wolfcode.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 20463
 */
@Service
public class VideoServiceImpl1 implements IVideoService1 {

    private final VideoMapper mapper;

    public VideoServiceImpl1(VideoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Page<Video> query(QueryObject qo) {
        Long total = mapper.selectForCount(qo);
        if (total == 0) {
            return Page.empty(qo.getCurrent(), qo.getLimit());
        }
        List<Video> videos = mapper.selectForList(qo);
        return new Page<>(qo.getCurrent(), qo.getLimit(), total, videos);
    }

    @Override
    public void save(Video obj) {
        if (obj.getDeleted() == null) {
            obj.setDeleted(false);
        }
        mapper.insert(obj);
    }

    @Override
    public void updateById(Video obj) {
        mapper.updateByPrimaryKey(obj);
    }

    @Override
    public void deleteById(Long id, boolean deleted) {
        mapper.deleteByPrimaryKey(id, deleted);
    }

    @Override
    public Video findById(Long id) {
        return mapper.selectByPrimaryKey(String.valueOf(id));
    }

    @Override
    public void changeStatus(Long id, String status) {
        mapper.changeStatus(id, status);
    }
}
