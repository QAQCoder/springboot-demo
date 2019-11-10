package demo.controller;

import demo.dto.QuestionDTO;
import demo.model.Question;
import demo.model.User;
import demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/15 11:30
 * Class description：
 */

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        QuestionDTO question = questionService.findById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", id);
        return "/publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model) {

        //判断用户是否已经登录
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            //把错误信息传送到前端
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }

        if (title == null || "".equals(title.trim())) {
            model.addAttribute("error", "title为空！");
            model.addAttribute("description", description);
            model.addAttribute("tag", tag);
            return "publish";
        }
        if (description == null || "".equals(description.trim())) {
            model.addAttribute("error", "description为空！");
            model.addAttribute("title", title);
            model.addAttribute("tag", tag);
            return "publish";
        }
        if (tag == null || "".equals(tag.trim())) {
            model.addAttribute("error", "tag为空！");
            model.addAttribute("description", description);
            model.addAttribute("title", title);
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);

        return "redirect:/";
    }
}
