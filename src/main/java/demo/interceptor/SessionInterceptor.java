package demo.interceptor;

import demo.mapper.UserMapper;
import demo.model.User;
import demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/28 15:04
 * Class description：session拦截器，每次请求都要经过这个拦截器，检查用户是否已经登录
 */

@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("SessionInterceptor---------preHandle-----------");
        //获取浏览器传过来的cookie，取出token，去数据中查找该token是否存在，若存在，表示用户合法，已登录成功
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<User> userList = userMapper.selectByExample(userExample);
                    if (userList.size() > 0) {
                        request.getSession().setAttribute("user", userList.get(0));
                    }
                    break;
                } /*else {
                    request.getSession().removeAttribute("user");
                }*/
            }/*else {
            System.out.println("cookie 为空");
            request.getSession().removeAttribute("user");
        }*/
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
