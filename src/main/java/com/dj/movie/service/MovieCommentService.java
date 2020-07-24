package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.MovieComment;

public interface MovieCommentService extends IService<MovieComment> {

    MovieComment findMovieCommentByUserId(Integer id) throws Exception;

    void updateMovieCommentIsLike(Integer id) throws Exception;
}
