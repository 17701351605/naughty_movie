package com.dj.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName("movie_comment")
public class MovieComment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 电影UUID*/
    private String movieId;

    /** 用户id*/
    private Integer userId;

    /** 用户id*/
    private String userName;

    /** 评价*/
    private String remark;

    /** 评价时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //接收前台日期格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") //返回前台格式
    private LocalDateTime createTime;
}
