package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.BaseDataMapper;
import com.dj.movie.pojo.BaseData;
import com.dj.movie.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDataServiceImpl extends ServiceImpl<BaseDataMapper, BaseData> implements BaseDataService {

    @Autowired
    private BaseDataMapper baseDataMapper;

    @Override
    public List<BaseData> findAllByPId(Integer parentId) throws Exception {
        return baseDataMapper.findAllByPId(parentId);
    }
}
