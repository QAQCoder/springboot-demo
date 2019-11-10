package demo.mapper;

import demo.model.Question;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/7 17:27
 *
 * 拓展Mapper，定义一些非MGB自动生成的接口
 */
public interface QuestionExtendMapper {
    //增加阅读数
    void incrementViewCount(Question question);
    //增加回复数
    void incrementCommentCount(Question question);
}
