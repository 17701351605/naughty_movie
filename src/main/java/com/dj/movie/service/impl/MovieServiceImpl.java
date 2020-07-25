package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.pojo.query.MovieQuery;
import com.dj.movie.mapper.MovieMapper;
import com.dj.movie.pojo.Movie;
import com.dj.movie.service.MovieService;
import org.apache.ibatis.annotations.Param;
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
    public List<Movie> findMovieAll(MovieQuery query,String movieName ,Integer status, Integer[] movieType) {
        IPage<Movie> page1 = new Page<>(query.getPageNo(), query.getPageSize());
        // 分页后的信息
        IPage<Movie> pageInfo = getBaseMapper().findMovieAll(page1, query, movieName, status, movieType);
        query.setPages((int) pageInfo.getPages());
        List<Movie> movieList = pageInfo.getRecords();
        return movieList;
    }

    @Override
    public Movie findMovieByMovieId(Integer id) throws Exception {
        return movieMapper.findMovieByMovieId(id);
    }

    @Override
    public Movie findMovieById(Integer id) throws Exception {
        return movieMapper.findMovieById(id);
    }
}
