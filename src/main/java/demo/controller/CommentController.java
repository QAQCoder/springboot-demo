package demo.controller;

import demo.dto.CommentDTO;
import demo.dto.ResultDTO;
import demo.exception.CustomErrorCode;
import demo.mapper.CommentMapper;
import demo.model.Comment;
import demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/6 17:02
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //响应json
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        //先判断是否处于登录状态
        if (request.getSession().getAttribute("user") == null) {
            return ResultDTO.errorOf(CustomErrorCode.HAD_NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setTypeId(commentDTO.getTypeId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(Long.valueOf(10));
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

}
