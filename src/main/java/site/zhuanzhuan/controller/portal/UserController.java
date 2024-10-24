package site.zhuanzhuan.controller.portal;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.zhuanzhuan.common.Const;
import site.zhuanzhuan.common.ServerResponse;
import site.zhuanzhuan.entity.User;
import site.zhuanzhuan.service.UserService;

/**
 * @author: xbronze
 * @date: 2024-10-18 15:31
 * @description: 前台用户管理Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      HttpSession session) {
        ServerResponse<User> loginResult = userService.login(username, password);
        if (loginResult.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, loginResult);
        }
        return loginResult;
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 获取登陆用户的信息
     * @param session HttpSession
     * @return
     */
    @RequestMapping(value = "get_user_info",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        ServerResponse<User> response = (ServerResponse<User>) session.getAttribute(Const.CURRENT_USER);
        if(response != null){
            User user = response.getData();
            if(user != null){
                return response;
            }
        }
        return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息");
    }
}
