package demo.controller;

import demo.dto.PaginationDTO;
import demo.dto.QuestionDTO;
import demo.mapper.UserMapper;
import demo.model.User;
import demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/11 10:37
 * Class description：
 */

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        //查询Question列表，返回到前端
        PaginationDTO paginationDTO = questionService.list(page, size);
        model.addAttribute("questions", paginationDTO);
        return "index";
    }//
}
