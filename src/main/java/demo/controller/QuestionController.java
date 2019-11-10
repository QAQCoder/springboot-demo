package demo.controller;

import demo.dto.QuestionDTO;
import demo.mapper.QuestionMapper;
import demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/30 15:39
 * Class description：
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {

        //根据问题id去查询该问题是否存在
        QuestionDTO questionDTO = questionService.findById(id);
        //若该问题存在，那么浏览问题的同时，增加阅读数
        if (questionDTO != null) {
            questionService.incrementViewCount(id);
        }
        //将问题展示前端
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
