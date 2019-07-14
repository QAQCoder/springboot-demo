package demo.mapper;

import demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/7/13 21:55
 * Class description：
 */

@Mapper
@Component
public interface UserMapper {

    @Insert("INSERT INTO table_user(ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED) VALUES(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    @Select("SELECT * FROM table_user WHERE token = #{token}")
    User findByToken(@Param("token") String token);
}
