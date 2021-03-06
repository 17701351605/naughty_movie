package com.dj.movie.web.page;

import com.dj.movie.pojo.*;
import com.dj.movie.service.MovieLikeService;
import com.dj.movie.service.BaseDataService;
import com.dj.movie.service.MovieOfficeService;
import com.dj.movie.service.MovieService;
import com.sun.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/movie/")
public class MoviePageController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private BaseDataService baseDataService;
    @Autowired
    private MovieOfficeService movieOfficeService;
    @Autowired
    private MovieLikeService movieLikeService;

    /**
     * 去电影详情页面
     *
     * @throws Exception
     * @author: zby
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieDetail/{id}")
    public String toMovieDetail(@PathVariable Integer id, Model model, @SessionAttribute("user") User user) throws Exception {
        Movie movie = movieService.findMovieById(id);
        MovieLike movieLike = movieLikeService.findMovieLikeByUserIdAndMovieId(user.getId(), String.valueOf(id));
        if (movieLike != null && movieLike.getScore() != null) {
            if (movieLike.getScore() != null) {
                model.addAttribute("score", movieLike.getScore());
            } else {
                model.addAttribute("score", null);
            }
        } else {
            model.addAttribute("score", null);
        }
        //评分
        BigDecimal grade = movieLikeService.markGrade(id);
        if (grade == null) {
            model.addAttribute("grade", "还未进行评分");
        } else {
            model.addAttribute("grade", grade.setScale(1, BigDecimal.ROUND_HALF_UP));
        }
        //点赞数
        Integer like = movieLikeService.isLike(id);
        if (grade == null) {
            model.addAttribute("like", "暂未点赞");
        } else {
            model.addAttribute("like", like);
        }
        model.addAttribute("movie", movie);
        model.addAttribute("user", user);
        return "movie/movie_comment";
    }

    /**
     * 去电影场次页面
     *
     * @throws Exception
     * @author: zby
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieOffice")
    public String toMovieOffice(Integer id, Model model) throws Exception {
        model.addAttribute("movieId", id);
        return "movie/movie_office";
    }

    /**
     * 跳转初次登陆展示页面
     *
     * @author: CYS
     */
    @RequestMapping("toMovieShow")
    private String toMovieShow(Model model, @SessionAttribute("user") User user) throws Exception {
        List<BaseData> list = baseDataService.findAllByPId(1);
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "movie/show";
    }

    /**
     * 去增加
     *
     * @author: hwk
     */
    @RequestMapping("toAdd")
    private String toAdd(Model model) throws Exception {
        List<BaseData> list = baseDataService.findAllByPId(1);
        model.addAttribute("list", list);
        return "movie/add";
    }

    /**
     * 电影的去修改
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     * @acthor :hwk
     */
    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Integer id, Model model) throws Exception {
        Movie movie = movieService.findMovieById(id);
        if (movie.getTopTimeShow() != null) {
            String substring = movie.getTopTimeShow().substring(0, 19);
            movie.setTopTimeShow(substring);
        }
        model.addAttribute("movie", movie);
        List<BaseData> list = baseDataService.findAllByPId(1);
        model.addAttribute("list", list);
        return "movie/update";
    }

    /**
     * @param id
     * @param model
     * @return
     * @throws Exception
     * @acthor :hwk
     * 场次的去修改
     */
    @RequestMapping("toUpdateById/{id}")
    public String toUpdateById(@PathVariable Integer id, Model model) throws Exception {
        MovieOffice movieOffice = movieOfficeService.findMovieOficeById(id);
        Movie movie = movieService.findMovieById(Integer.valueOf(movieOffice.getMovieId()));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = timeFormatter.format(movieOffice.getStartTime());
        model.addAttribute("officeList", baseDataService.findAllByPId(9));
        model.addAttribute("startTime", format);
        model.addAttribute("movie", movie);
        model.addAttribute("movieOffice", movieOffice);
        return "movie/update_movie_office";
    }

    /**
     * 去增加场次
     *
     * @param id
     * @return
     */
    @RequestMapping("toMovieOfficeAdd")
    public String toAdd(Integer id, Model model) throws Exception {
        Movie movie = movieService.findMovieById(id);
        //电影信息
        model.addAttribute("movie", movie);
        //播放厅
        model.addAttribute("officeList", baseDataService.findAllByPId(9));
        return "movie/add_movie_office";
    }

}
