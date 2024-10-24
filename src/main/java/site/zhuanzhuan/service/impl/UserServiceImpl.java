package site.zhuanzhuan.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.zhuanzhuan.common.ServerResponse;
import site.zhuanzhuan.dao.UserMapper;
import site.zhuanzhuan.entity.User;
import site.zhuanzhuan.entity.vo.UserVO;
import site.zhuanzhuan.service.UserService;
import site.zhuanzhuan.utils.MD5Util;

import java.util.Date;
import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-10-18 13:37
 * @description: TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        User result = userMapper.getUserByUsername(username);
        if(result == null){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        //密码要进行MD5加密再进行比较
        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User paramUser = new User();
        paramUser.setUsername(username);
        paramUser.setPassword(md5Password);
        List<UserVO> list = userMapper.getUserList(paramUser);
        if(list == null || list.size() == 0){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        UserVO userVO = list.get(0);
        userVO.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", userVO);

    }

    @Override
    public ServerResponse<String> register(User user) {
        // 校验用户名是否已存在
        User resultUser = userMapper.getUserByUsername(user.getUsername());
        if (resultUser != null) {
            return ServerResponse.createByErrorMessage("用户名["+user.getUsername()+"]已存在");
        }
        user.setRole(1); // 注册的都是普通用户
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        Date currentDate = new Date();
        user.setCreateTime(currentDate);
        user.setUpdateTime(currentDate);
        int resultInsert = userMapper.insertUser(user);
        if (resultInsert == 0) {
            return ServerResponse.createByErrorMessage("用户注册失败！");
        }
        return ServerResponse.createBySuccessMessage("用户注册成功！");
    }

}
