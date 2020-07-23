package com.dj.movie.web;

import com.dj.movie.pojo.ResultModel;
import com.dj.movie.pojo.User;
import com.dj.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description: 用户
 * @Author chj
 * @Date 2020/7/23 9:27
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @Param user
     * @Return
     * @Author Caohj
     * @Date
     */
    @RequestMapping("login")
    public ResultModel login(User user){
        try {
            User user1 = userService.findNameAndPwd(user);
            if (null == user1){
                return new ResultModel().error(0,"账户或密码有误，请重新输入");
            }
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


}
