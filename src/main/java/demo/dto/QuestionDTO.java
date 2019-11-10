package demo.dto;

import demo.model.User;
import lombok.Data;
import lombok.ToString;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/13 16:26
 * Class description：传输层的pojo
 *
 *      不是在model的Question中加入User对象属性，而是分离处理到DTO中
 *
 *      因为要在Question中加入User对象的话，还需要改question表的结构，复杂
 */

@Data
@ToString
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Integer creator;
    private Integer commentCount;
    private Integer browseCount;
    private Integer likeCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    //这里加入User对象属性，用于得到avatar
    private User user;
}
