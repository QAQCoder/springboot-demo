package demo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by QAQCoder, Email:QAQCoder@qq.com, Create time 2019/11/6 17:16
 */

@Data
@ToString
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer typeId;
}
