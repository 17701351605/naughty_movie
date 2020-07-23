package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.MovieComment;
import java.util.zip.DataFormatException;

public interface MovieCommentMapper extends BaseMapper<MovieComment> {

    MovieComment findMovieCommentByUserId(Integer id) throws DataFormatException;

    void updateMovieCommentIsLike(Integer id) throws DataFormatException;
}
