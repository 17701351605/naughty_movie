package com.dj.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.movie.pojo.MovieComment;
import org.apache.ibatis.annotations.Param;

import java.util.zip.DataFormatException;

public interface MovieCommentService extends IService<MovieComment> {

    void addMovieComment(Integer userId, String userName, Integer MovieId, String remark) throws Exception;
}
