package com.dj.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("movie_office")
public class MovieOffice {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 电影UUID*/
    private String movieId;

    /** 电影名称*/
    private String movieName;

    /** 播放厅*/
    private Integer playHall;

    /** 价格*/
    private BigDecimal price;

    /** 座位数*/
    private Integer seating;

    /** 电影开始时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //接收前台日期格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") //返回前台格式
    private LocalDateTime startTime;

    /** 0删除，1未删除*/
    private Integer isDel;

}
