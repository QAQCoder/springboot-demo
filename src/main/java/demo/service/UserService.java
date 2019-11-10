package demo.service;

import demo.mapper.UserMapper;
import demo.model.User;
import demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/31 11:13
 * Class description：
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User createOrUpdateUser(User user) {
        //检查数据库中是否已经存在该用户，避免多次insert同一个用户信息
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> resultUser = userMapper.selectByExample(userExample);
        if (resultUser.size() > 0) {
            System.out.println("user 已存在，那么更新即可");
            //已存在，那么更新即可
            user.setGmtCreate(resultUser.get(0).getGmtCreate());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateByPrimaryKey(user);
        } else {
            //插入
            System.out.println("user 不存在，insert");
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
        return user;
    }//

    public User findUserByGithubId(String accountId) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(accountId);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() <= 0) {
            System.out.println("error msg : UserService--findUserByGithubId--userList.size() <= 0");
        }
        return userList.get(0);
    }
}
