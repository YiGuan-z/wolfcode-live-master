package cn.wolfcode.service;

import cn.wolfcode.domain.Video;

/**
 * @author 20463
 */
public interface IVideoService1 extends IService<Video, Long> {

    void changeStatus(Long id, String status);
}
