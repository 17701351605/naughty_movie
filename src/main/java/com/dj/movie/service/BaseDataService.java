package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.BaseData;

import java.util.List;

public interface BaseDataService extends IService<BaseData> {

    List<BaseData> findAllByPId(Integer parentId) throws Exception;
}
