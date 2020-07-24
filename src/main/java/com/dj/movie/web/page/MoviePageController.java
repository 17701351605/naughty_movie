package com.dj.movie.web.page;

import com.dj.movie.pojo.BaseData;
import com.dj.movie.pojo.Movie;
import com.dj.movie.service.BaseDataService;
import com.dj.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movie/")
public class MoviePageController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private BaseDataService baseDataService;


    /**
     * 去电影详情页面
     * @author: zby
     * @throws Exception
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieDetail")
    public String toMovieDetail(Integer movieId, Model model) throws Exception {
        Movie movie = movieService.findMovieByMovieId(1);
        model.addAttribute("movie",movie);
        return "Movie/movie_comment";
    }

    /**
     * 去电影场次页面
     * @author: zby
     * @throws Exception
     * @date: 2020年7月23日
     */
    @GetMapping("toMovieOffice")
    public String toMovieOffice() throws Exception {
        return "Movie/movie_office";
    }

    /**
     * 跳转初次登陆展示页面
     * @author: CYS
     */
    @RequestMapping("toMovieShow")
    private String toMovieShow(){
        return "movie/show";
    }
    /**
     *去增加
     * @author: hwk
     */
    @RequestMapping("toAdd")
    private String toAdd(Model model) throws Exception{
        List<BaseData> list = baseDataService.findAllByPId(1);
        model.addAttribute("list",list);
        return "movie/add";
    }

    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Integer id, Model model) throws Exception{
        Movie movie = movieService.findMovieById(id);
        if (movie.getTopTimeShow() !=null) {
            String substring = movie.getTopTimeShow().substring(0, 19);
            movie.setTopTimeShow(substring);
        }

        model.addAttribute("movie",movie);
        List<BaseData> list = baseDataService.findAllByPId(1);

        model.addAttribute("list",list);
        return "movie/update";
    }
}
