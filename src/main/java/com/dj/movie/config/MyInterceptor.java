package com.dj.movie.config;

import com.dj.movie.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 方法前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (null == user) {
            response.sendRedirect(request.getContextPath() + "/user/toLogin");
            return false;
        }
        return true;
    }

}
