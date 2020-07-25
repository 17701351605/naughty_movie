package com.dj.movie.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.movie.pojo.ResultModel;
import com.dj.movie.pojo.User;
import com.dj.movie.service.UserService;
import com.dj.movie.utils.JavaEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    /**
     * 判断用户是否存在
     * @Param user
     * @Return boolean
     * @Author Caohj
     * @Date
     */
    @RequestMapping("findUserByName")
    public Boolean findUserByName(User user) {
        try {
            User user1 = userService.findUserByName(user);
            return user1 == null ? true : false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 注册用户
     * @Param
     * @Return
     * @Author Caohj
     * @Date
     */
    @RequestMapping("register")
    public ResultModel register(User user){
        userService.save(user);
        return new ResultModel().success(true);
    }

    /**
     * 发送邮箱验证码
     * @Param
     * @Return
     * @Author Caohj
     * @Date
     */
    @RequestMapping("sendEmail")
    public ResultModel sendEmail(User user){
        try {
            User user1 = userService.findUserByName(user);
            if (null == user1){
                return new ResultModel().success(0, "该用户不存在请重新输入");
            }
            //生成验证码
            int code = (int) ((Math.random() * 9 + 1) * 100000);
            user1.setCode(String.valueOf(code));
            //当前时间加一分钟做有效时间校验
            LocalDateTime time = LocalDateTime.now().plusMinutes(1);
            user1.setCodeTime(time);
            //存入用户表
            userService.updateById(user1);
            JavaEmailUtils.sendEmail(user.getEmail(), "验证码", String.valueOf(code));
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @RequestMapping("codeLogin")
    public ResultModel codeLogin(User user){
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", user.getEmail());
            queryWrapper.eq("code", user.getCode());
            User user1 = userService.getOne(queryWrapper);
            if (null == user1){
                return new ResultModel().error(0,"邮箱或验证码有误，请重新输入");
            }
            if (System.currentTimeMillis() > user1.getCodeTime().toInstant(ZoneOffset.of("+8")).toEpochMilli()){
                return new ResultModel().error(0,"验证码已失效，请重新获取");
            }
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

}
