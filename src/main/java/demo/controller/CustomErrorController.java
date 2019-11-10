package demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/4 16:22
 *
 * 为了覆盖 error.html页面message=null时显示的默认信息No message available
 *
 * 先定位到No message available信息所在的源代码位置，看他的实现逻辑
 *
 * 然后覆写，覆盖掉它的，下面的代码就是参考了源代码的写法
 *
 * 视频地址：https://www.bilibili.com/video/av50200264/?p=35
 *
 */

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }//

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model) {
        HttpStatus status = getStatus(request);
        if (status.is4xxClientError()) {
            model.addAttribute("message", "您请求姿势不对呢，请换个姿势~");
        }
        if (status.is5xxServerError()) {
            model.addAttribute("message", "服务器开小差了，等会儿再试试~");
        }
        return new ModelAndView("error");
    }//

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try { return HttpStatus.valueOf(statusCode); }
        catch (Exception ex) { return HttpStatus.INTERNAL_SERVER_ERROR; }
    }//
}
