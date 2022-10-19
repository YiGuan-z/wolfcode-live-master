package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Video;
import cn.wolfcode.qo.UserQuery;
import cn.wolfcode.service.IVideoService1;
import cn.wolfcode.vo.JsonResult;
import cn.wolfcode.vo.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/video")
public class VideoController {

    private final IVideoService1 videoService;

    public VideoController(IVideoService1 videoService) {
        this.videoService = videoService;
    }

    @RequestMapping("/list")
    public JsonResult<Page<Video>> query(UserQuery qo) {
        return JsonResult.success(videoService.query(qo));
    }

    @RequestMapping("/changeStatus")
    public JsonResult<?> changeStatus(Long id, String status) {
        videoService.changeStatus(id, status);
        return JsonResult.success();
    }

    @RequestMapping("/saveOrUpdate")
    public JsonResult<?> saveOrUpdate(@RequestBody Video video) {
        if (video.getId() != null) {
            videoService.updateById(video);
        } else {
            videoService.save(video);
        }

        return JsonResult.success();
    }

    @RequestMapping("/delete")
    public JsonResult<?> delete(Long id, boolean deleted) {
        if (id == null) {
            return JsonResult.failed("删除的 id 不能为空!");
        }

        videoService.deleteById(id, deleted);
        return JsonResult.success();
    }
}
