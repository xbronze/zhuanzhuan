package site.zhuanzhuan.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import site.zhuanzhuan.entity.User;

import java.util.Date;

/**
 * @author: xbronze
 * @date: 2024-10-24 20:26
 * @description: TODO
 */
@Data
public class UserVO extends User {

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createTime;
    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date updateTime;
}
