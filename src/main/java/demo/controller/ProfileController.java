package demo.controller;

import demo.dto.PaginationDTO;
import demo.mapper.UserMapper;
import demo.model.User;
import demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/17 16:56
 * Class description：
 */

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable("action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "3") Integer size) {

        User user = (User) request.getSession().getAttribute("user");

        //若用户没有登录，那就跳转到首页
        if (user == null) return "redirect:/";

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        //根据当前登录用户的id，去查询其所有的questions
        System.out.println(user + " - " + page + " - " + size);
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("questions", paginationDTO);

        return "profile";
    }//
}
