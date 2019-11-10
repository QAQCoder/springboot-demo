package demo.service;

import demo.enums.CommentTypeEnum;
import demo.exception.CustomErrorCode;
import demo.exception.CustomException;
import demo.mapper.CommentMapper;
import demo.mapper.QuestionExtendMapper;
import demo.mapper.QuestionMapper;
import demo.model.Comment;
import demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/7 15:20
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    @Transactional
    public void insert(Comment comment) {
        //先判断comment是否合法，不能凭空评论
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomException(CustomErrorCode.TARGET_PARENT_NOT_FOUND);
        }
        //判断评论类型
        if (comment.getTypeId() == null || !CommentTypeEnum.isExist(comment.getTypeId())) {
            throw new CustomException(CustomErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getTypeId() == CommentTypeEnum.QUESTION.getType()) {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            //先判断问题是否存在
            if (question == null) throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            commentMapper.insert(comment);
            //该问题的评论数加一
            question.setCommentCount(1);
            questionExtendMapper.incrementCommentCount(question);
        } else {
            //回复评论
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            //先判断它的父评论是否存在
            if (parentComment == null) throw new CustomException(CustomErrorCode.COMMENT_NOT_EXIST);
            commentMapper.insert(comment);
        }
    }//
}
