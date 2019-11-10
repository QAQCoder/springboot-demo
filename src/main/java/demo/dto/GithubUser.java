package demo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/13 12:12
 * Class description：
 */

@Data
@ToString
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
