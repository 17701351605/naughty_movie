package com.dj.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("base_data")
public class BaseData {

    /**主键ID*/
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 名称*/
    private String baseName;

    /** 父级ID*/
    private Integer parentId;

    /** 是否删除*/
    private Integer isDel;

}
