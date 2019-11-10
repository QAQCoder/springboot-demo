package demo.dto;

import demo.exception.CustomErrorCode;
import demo.exception.CustomException;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/7 15:09
 * <p>
 * 结果实体类
 * 发表回复后的结果
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    private static ResultDTO whatOf(Integer code, String msg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(msg);
        return resultDTO;
    }

    public static ResultDTO errorOf(Integer code, String msg) {
        return whatOf(code, msg);
    }//

    public static ResultDTO errorOf(CustomErrorCode hadNotLogin) {
        return errorOf(hadNotLogin.getCode(), hadNotLogin.getMessage());
    }//

    public static ResultDTO okOf() {
        return whatOf(200, "成功");
    }

    public static ResultDTO errorOf(CustomException e) {
        return ResultDTO.errorOf(e.getCode(), e.getMessage());
    }
}
