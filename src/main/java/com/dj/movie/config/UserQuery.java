package com.dj.movie.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author CYS
 * 连表分页查询
 */
@Data
public class UserQuery implements Serializable {

    private Integer pageNo = 1;

    private Integer pageSize = 2;

    private Integer pages;

}
