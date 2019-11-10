package demo.advice;

import com.alibaba.fastjson.JSON;
import demo.dto.ResultDTO;
import demo.exception.CustomErrorCode;
import demo.exception.CustomException;
import net.minidev.json.JSONUtil;
import org.json.JSONString;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/4 15:34
 * <p>
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        if ("application/json".equals(request.getContentType())) {
            //返回json
            ResultDTO resultDTO;
            if (e instanceof CustomException) {
                resultDTO = ResultDTO.errorOf((CustomException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomErrorCode.SYSTEM_ERROR);
            }
            //为了把json信息传到前端，使用HttpServletResponse
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ex) {
            }
            return null;
        } else {
            //错误页跳转
            //判断是否属于自定义异常
            if (e instanceof CustomException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }//
}
