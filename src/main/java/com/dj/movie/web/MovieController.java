package com.dj.movie.web;

import com.dj.movie.config.UserQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.movie.pojo.*;
import com.dj.movie.service.MovieCommentService;
import com.dj.movie.service.MovieLikeService;
import com.dj.movie.service.MovieOfficeService;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 基础列表展示
 */


@RestController
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieCommentService movieCommentService;

    @Autowired
    private MovieOfficeService movieOfficeService;

    @Autowired
    private MovieLikeService movieLikeService;


    @RequestMapping("show")
    public ResultModel show(Movie movie, UserQuery query/*, @SessionAttribute("user")User user*/){
        try{
          /*  //如果登录的是普通用户查状态为上架的
            if (user.getLevel() == 0) {
                query.setStatus(1);
            } else {
                query.setStatus(0);
            }*/
            Map<String,Object> map = new HashMap<>();
            List<Movie> movieList = movieService.findMovieAll(query);
            map.put("pages",query.getPages());
            map.put("movieList", movieList);
            return new ResultModel().success(map);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel().error("异常");
        }

    }

    /**
     * 1.根据电影id查询进入电影详情页面
     * 电影详情页面展示
     *
     * @return
     * @author: zby
     * @date: 2020年7月23日
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
     *
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
    }

    /**
     * 电影场次展示
     * 1.根据电影id查询进入电影场次页面。
     * 2.展示当前时间后的电影场次，可以进行场次搜索。
     *
     * @return
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
            //电影id
            queryWrapper.eq("movie_id", 1);
            //查询未删除的
            queryWrapper.eq("is_del", 1);
            //展示当前时间后的电影场次
            queryWrapper.ge("start_time", LocalDateTime.now());
            //场次搜索
            if (!StringUtils.isEmpty(startingTime)) {
                // String -> LocalDateTime
                LocalDateTime startingTime1 = LocalDateTime.parse(startingTime, dateTimeFormatter);
                queryWrapper.ge("start_time", startingTime1);
            }
            if (!StringUtils.isEmpty(endTime)) {
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
     * 电影点赞
     * 1.登陆用户可以对其选择的电影进行点赞
     * 2.判断登陆用户是否为该电影点赞
     *
     * @return
     * @author: zby
     * @date: 2020年7月23日
     */
    @RequestMapping("toLike")
    public ResultModel<Object> toLike(Integer movieId/*, @SessionAttribute("user") User user*/) {
        try {
            //根据登陆获取的用户id进行查询
            MovieLike movieLike = movieLikeService.findMovieLikeByUserIdAndMovieId(1, 1);
            //判断用户是否点赞
            if (movieLike.getIsLike() == 1) {
                movieLikeService.updateMovieLikeIsLike(1,1,0);
                return new ResultModel<Object>().success("取消成功,感谢您的支持");
            }
            movieLikeService.updateMovieLikeIsLike(1,1,1);
            return new ResultModel<Object>().success("点赞成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器处理异常，请稍后重试");
        }
    }

    /**
     * 电影评分
     * 1.登陆用户可以对其选择的电影进行评分
     * 2.判断登陆用户是否为该电影评分
     * @return
     * @author: zby
     * @date: 2020年7月24日
     */
    @RequestMapping("updateMovieLikeScore")
    public ResultModel<Object> updateMovieLikeScore(Integer score, Integer movieId/*, @SessionAttribute("user") User user*/) {
        try {
            //根据登陆获取的用户id进行查询
            MovieLike movieLike = movieLikeService.findMovieLikeByUserIdAndMovieId(1, 1);
            //若查询为空则进行新增
            if (movieLike == null){
                movieLikeService.addMovieLike(1, 1, score);
            }
            //若查询不为空并且未进行评分则进行修改添加评分
            if (movieLike != null && movieLike.getScore()==null) {
                movieLikeService.updateMovieLikeScore(1, 1, score);
            }
            return new ResultModel<Object>().success();
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
     * 增加电影
     * @autor hwk
     * @param movie
     * @return
     */
    @RequestMapping("addMovie")
    public ResultModel addMovie(Movie movie) {
        try {
            UUID uuid = UUID.randomUUID();
            movie.setMovieId(uuid.toString().replace("-",""));
            movieService.save(movie);
            return new ResultModel().success("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }

    }

    /**
     * 删除
     * @param
     * @return
     * @autor hwk
     */
    @RequestMapping("delOffice")
    public ResultModel delOffice(MovieOffice movieOffice) {
        try {
            movieOfficeService.updateById(movieOffice);
            return new ResultModel().success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }
    }

    /**
     * 场次的修改
     * @return
     */
    @RequestMapping("updateOffice")
    public ResultModel updateOffice(MovieOffice movieOffice) {
        try {
            movieOfficeService.updateById(movieOffice);
            return new ResultModel().success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }
    }

    /**
     * 场次的增加
     * @param movieOffice
     * @return
     */
    @RequestMapping("addMovieOffice")
    public ResultModel addMovieOffice(MovieOffice movieOffice) {
        try {
            movieOfficeService.save(movieOffice);
            return new ResultModel().success("增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }
    }
}

