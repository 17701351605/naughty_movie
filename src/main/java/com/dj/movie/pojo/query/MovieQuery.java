package com.dj.movie.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author CYS
 * 连表分页查询
 */
@Data
public class MovieQuery implements Serializable {

    private Integer pageNo = 1;

    private Integer pageSize = 2;

    private Integer pages;

}
