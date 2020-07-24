package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.BaseData;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BaseDataMapper extends BaseMapper<BaseData> {

    List<BaseData> findAllByPId(Integer parentId) throws DataAccessException;
}
