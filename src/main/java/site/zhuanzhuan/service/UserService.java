package site.zhuanzhuan.service;

import site.zhuanzhuan.common.ServerResponse;
import site.zhuanzhuan.entity.User;

/**
 * @author: xbronze
 * @date: 2024-10-18 13:36
 * @description: TODO
 */
public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<User> login(String username, String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user);
}
