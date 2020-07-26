package com.dj.movie.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserPageController {

    /**
     * 去登录页面
     * @author Caohj
     * @date
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "user/login";
    }

    /**
     *去邮箱验证码登录页面
     * @Author Caohj
     * @Date
     */
    @RequestMapping("toEmail")
    public String toEmail(){
        return "user/email_login";
    }

    /**
     * 去注册页面
     * @author Caohj
     * @date
     */
    @RequestMapping("toRegister")
    public String toRegister(){
        return "user/register";
    }

    @RequestMapping("toHome")
    public String toHome(){
        return "user/home";
    }




}
