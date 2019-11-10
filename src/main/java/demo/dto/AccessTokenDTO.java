package demo.dto;

import lombok.Data;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/13 11:51
 * Class description：
 */

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
