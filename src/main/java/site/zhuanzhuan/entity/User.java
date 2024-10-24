package site.zhuanzhuan.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-10-18 11:34
 * @description: 用户实体类
 */
@Data
public class User {

    // 主键
    private Integer id;
    // 用户名
    private String username;
    // 昵称
    private String nickname;
    // 密码
    private String password;
    // 邮箱
    private String email;
    // 电话
    private String phone;
    // 角色 0=管理员  1=普通用户
    private Integer role;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;


}
