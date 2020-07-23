package com.dj.movie.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.movie.config.UserQuery;
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

    @Override
    public List<Movie> findMovieAll(UserQuery query) {
        IPage<Movie> page = new Page<>(query.getPageNo(), query.getPageSize());
        // 分页后的信息
        IPage<Movie> pageInfo = getBaseMapper().findMovieAll(page, query);
        // 总页数
        System.out.println(pageInfo.getPages());
        // 总条数
        System.out.println(pageInfo.getTotal());
        // 分页数据
        query.setPages((int) pageInfo.getPages());
        List<Movie> moviesList = pageInfo.getRecords();
        return moviesList;
    }
}
