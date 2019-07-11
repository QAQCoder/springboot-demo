package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/11 10:37
 * Class description：
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHeello(@RequestParam(name = "name", defaultValue = "Springboot") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("date", LocalTime.now().toString());
        return "hello";
    }
}
