package com.dj.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName("movie")
public class Movie {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 电影UUID*/
    private String movieId;

    /** 电影名称*/
    private String movieName;

    /** 演员名称*/
    private String actorName;

    /** 电影类型*/
    private Integer movieType;

    /** 电影时长*/
    private String longTime;

    /** 上线时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //接收前台日期格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") //返回前台格式
    private LocalDateTime topTime;

    /** 上下架*/
    private Integer status;

    /** 0删除，1不删除*/
    private Integer isDel;


}
