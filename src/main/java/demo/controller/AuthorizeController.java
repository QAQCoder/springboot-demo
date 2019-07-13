package demo.controller;

import demo.dto.AccessTokenDTO;
import demo.dto.GithubUser;
import demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/github_callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id(clientId);
        dto.setRedirect_uri(redirectUrl);
        dto.setClient_secret(clientSecret);
        dto.setCode(code);
        dto.setState(state);
        String accessToken = githubProvider.getAccessToken(dto);

        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.toString());

        return "index";
    }
}
