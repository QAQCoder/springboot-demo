package demo.exception;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/4 15:56
 *
 * 异常信息枚举类
 *
 */
public enum CustomErrorCode implements ICustomErrorCode {

    QUESTION_NOT_FOUND(2001, "该问题不存在或者已经被删除了！"),
    TARGET_PARENT_NOT_FOUND(2002, "未选中任何问题或评论，您不能凭空回复，回复总得有个对象"),
    HAD_NOT_LOGIN(2003, "未登录，不支持该操作！"),
    SYSTEM_ERROR(2004, "服务器好累啊，要不等会儿再试试···"),
    TYPE_PARAM_WRONG(2005, "评论类型不存在"),
    COMMENT_NOT_EXIST(2006, "要回复的评论不存在");

    private String message;
    private Integer code;

    CustomErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public Integer getCode() {
        return code;
    }
}
