package com.dj.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {

    /** 主键id*/
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户名*/
    private String username;

    /** 密码*/
    private String password;

    /** 手机号*/
    private String phoneNumber;

    /** 邮箱*/
    private String email;

    /** 验证码*/
    private String code;

    /** 失效时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //接收前台日期格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") //返回前台格式
    private LocalDateTime codeTime;

    /** 级别 */
    private Integer level;

    /** 0未激活，1激活*/
    private Integer status;


}
