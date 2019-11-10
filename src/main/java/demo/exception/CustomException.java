package demo.exception;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/4 15:36
 *
 * 自定义异常类
 */
public class CustomException extends RuntimeException {
    private String message;
    private Integer code;

    //通过异常枚举类得到异常信息
    public CustomException(CustomErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }
    public Integer getCode() {
        return code;
    }
}
