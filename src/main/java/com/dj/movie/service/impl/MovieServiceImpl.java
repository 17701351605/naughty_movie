package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.mapper.MovieMapper;
import com.dj.movie.pojo.Movie;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> findMovieAll(Movie movie) throws Exception {
        return movieMapper.findMovieAll(movie);
    }
}
