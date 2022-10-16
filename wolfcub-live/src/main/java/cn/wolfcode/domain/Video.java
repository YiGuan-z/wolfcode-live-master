package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Video {
    private String id;
    private String title;
    private String subTitle;
    private String description;
    private String coverUrl;
    private String videoUrl;
    private Long authorId;
    private Integer likes;
    private Integer coins;
    private Integer shares;
    private Integer comments;
    private Integer views;
    private Integer status;
    private Date releaseTime;
    private Date createdTime;
    private Date updatedTime;
}
