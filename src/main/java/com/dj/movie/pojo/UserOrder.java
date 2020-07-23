package com.dj.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("user_order")
public class UserOrder {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户id*/
    private Integer userId;

    /** 电影UUID*/
    private String movieId;

    /** 电影名称*/
    private String movieName;

    /** 购买总价*/
    private BigDecimal prices;

    /** 购买数量*/
    private Integer buyNumber;

    /** 下单时间*/
    private LocalDateTime createTime;

    /** 是否退票，0退票，1未退票*/
    private Integer isDel;
}
