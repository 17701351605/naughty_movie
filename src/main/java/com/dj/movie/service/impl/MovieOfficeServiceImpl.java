package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.MovieOfficeMapper;
import com.dj.movie.pojo.MovieComment;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.service.MovieOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieOfficeServiceImpl extends ServiceImpl<MovieOfficeMapper, MovieOffice> implements MovieOfficeService {
    @Autowired
    private MovieOfficeMapper movieOfficeMapper;

}
