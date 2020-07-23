package com.dj.movie.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.movie.pojo.Movie;
import com.dj.movie.pojo.MovieComment;
import com.dj.movie.pojo.MovieOffice;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.service.MovieCommentService;
import com.dj.movie.service.MovieOfficeService;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieCommentService movieCommentService;

    @Autowired
    private MovieOfficeService movieOfficeService;


    @RequestMapping("show")
    public ResultModel show(Movie movie){
        try{
            //IPage<Movie> page = new Page<>(pageNo, 2);
         //   IPage<Movie> pageInfo = movieService.page(page);
            List<Movie> movieList = movieService.findMovieAll(movie);
          //  Map<String, Object> map = new HashMap<>();
         //   map.put("movieList", pageInfo.getRecords());
          //  map.put("pages", pageInfo.getPages());
            return new ResultModel().success(movieList);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("异常");
        }

    }

    /**
     * 电影详情页面展示
     * @author: zby
     * @date: 2020年7月23日
     * @return
     */
    @RequestMapping("evaluatShow")
    public ResultModel<Object> evaluatShow(Integer mId) {
        try {
            //条件构造器
            QueryWrapper<MovieComment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("movie_id", 1);
            List<MovieComment> movieComments = movieCommentService.list(queryWrapper);
            return new ResultModel<Object>().success(movieComments);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器处理异常，请稍后重试");
        }
    }
    /**
     * 删除
     * @param movie
     * @return
     * @autor hwk
     */
    @RequestMapping("del")
    public ResultModel del(Movie movie) {
        try {
            movieService.updateById(movie);
            return new ResultModel().success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }

        /**
         * 电影场次展示
         * @author: zby
         * @date: 2020年7月23日
         * @return
         */
        @RequestMapping("movieOfficeShow")
        public ResultModel<Object> movieOfficeShow(Integer mId, String startingTime, String endTime) {
            Map<String, Object> map = new HashMap<String, Object>();
            try {
                // 时间日期格式转化器
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                //条件构造器
                QueryWrapper<MovieOffice> queryWrapper = new QueryWrapper<>();
                //电影开始时间正序
                queryWrapper.orderByAsc("start_time");

                queryWrapper.eq("movie_id", 1);

                queryWrapper.eq("is_del", 1);
                queryWrapper.ge("start_time", LocalDateTime.now());
                if (!StringUtils.isEmpty(startingTime)){
                    // String -> LocalDateTime
                    LocalDateTime startingTime1 = LocalDateTime.parse(startingTime, dateTimeFormatter);
                    queryWrapper.ge("start_time", startingTime1);
                }
                if (!StringUtils.isEmpty(endTime)){
                    // String -> LocalDateTime
                    LocalDateTime endTime1 = LocalDateTime.parse(endTime, dateTimeFormatter);
                    queryWrapper.le("start_time", endTime1);
                }
                List<MovieOffice> movieOffice = movieOfficeService.list(queryWrapper);
                // List<MovieOffice> movieOffice = movieOfficeService.findMovieOfficeByMovieId(1);
                return new ResultModel<Object>().success(movieOffice);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultModel<Object>().error("服务器处理异常，请稍后重试");
            }
        }

    /**
     * 修改
     * @param
     * @return
     * @autor hwk
     */
    @RequestMapping("updateMovie")
    public ResultModel updateMovie(Movie movie) {
        try {
            movieService.updateById(movie);
            return new ResultModel().success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }
    }


    /**
     * 电影点赞
     * @author: zby
     * @date: 2020年7月23日
     * @return
     */
    @RequestMapping("toLike/{id}")
    public ResultModel<Object> toLike(@PathVariable Integer id) {
        try {
            MovieComment movieComment = movieCommentService.findMovieCommentByUserId(id);
            if (movieComment.getIsLike() == 1){
                return new ResultModel<Object>().error("为了保证信息的正确性，每个用户只能点赞一次，感谢您的理解和支持");
            }
            movieCommentService.updateMovieCommentIsLike(id);
            return new ResultModel<Object>().success("感谢您的支持");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器处理异常，请稍后重试");
        }
    }
}
