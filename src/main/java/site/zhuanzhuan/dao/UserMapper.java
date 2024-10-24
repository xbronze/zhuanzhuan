package site.zhuanzhuan.dao;

import site.zhuanzhuan.entity.User;
import site.zhuanzhuan.entity.vo.UserVO;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-10-18 13:22
 * @description: TODO
 */
public interface UserMapper {

     public User getUserByUsername(String username);

     public List<UserVO> getUserList(User user);

     public int insertUser(User user);
}
