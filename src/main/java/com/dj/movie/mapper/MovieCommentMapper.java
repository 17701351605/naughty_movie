package com.dj.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.movie.pojo.MovieComment;
import org.apache.ibatis.annotations.Param;
import java.util.zip.DataFormatException;

public interface MovieCommentMapper extends BaseMapper<MovieComment> {

    void addMovieComment(@Param("userId") Integer userId, @Param("MovieId") Integer MovieId, @Param("remark") String remark) throws DataFormatException;
}
