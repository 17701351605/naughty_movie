package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.MovieMapper;
import com.dj.movie.pojo.Movie;
import com.dj.movie.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

}
