package demo.controller;

import demo.dto.AccessTokenDTO;
import demo.dto.GithubUser;
import demo.mapper.UserMapper;
import demo.model.User;
import demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/13 11:42
 * Class description：
 */

@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    //通过Value注解，得到配置文件中的值
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.url}")
    private String redirectUrl;
    @Value("${github.client.secret}")
    private String clientSecret;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/github_callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state,
            HttpServletRequest request,
            HttpServletResponse response) {   //这里的request 和 response 是Spring自动识别并将上下文中的request传递进来

        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id(clientId);
        dto.setRedirect_uri(redirectUrl);
        dto.setClient_secret(clientSecret);
        dto.setCode(code);
        dto.setState(state);
        String accessToken = githubProvider.getAccessToken(dto);

        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //登录成功，将信息保存到session中
            request.getSession().setAttribute("user", githubUser);

            //保存用户信息到数据库
            String token = UUID.randomUUID().toString();    //使用UUID生成token
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);

            //把token以cookie的形式传到客户端
            response.addCookie(new Cookie("token", token));

            //重定向到index，注意不能写成redirect:index
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }//
}
