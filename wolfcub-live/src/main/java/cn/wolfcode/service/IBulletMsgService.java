package cn.wolfcode.service;

import cn.wolfcode.domain.BulletMsg;

import java.util.List;

public interface IBulletMsgService extends IService<BulletMsg, Long> {

    void changeStatus(Long id, Integer status);

    int getMsgCountByVideoId(String videoId);

    List<BulletMsg> getListByVideoId(String videoId);

    List<BulletMsg> getRollingMessages(String videoId, int currentVideoTime);
}
