package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/11 10:37
 * Class description：
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
