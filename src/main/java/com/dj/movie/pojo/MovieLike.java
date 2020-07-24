package com.dj.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName("movie_like")
public class MovieLike {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 电影UUID*/
    private String movieId;

    /** 用户id*/
    private Integer userId;

    /** 评分*/
    private Integer score;

    /** 是否点赞*/
    private Integer isLike;

}
