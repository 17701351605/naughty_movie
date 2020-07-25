package com.dj.movie.pojo.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuery implements Serializable {

    private String username;

    private String phoneNumber;

    private String email;

    private Integer code;

    private Integer pageNo = 1;

    private Integer pageSize = 2;

}
