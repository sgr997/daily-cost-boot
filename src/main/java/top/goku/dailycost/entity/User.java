package top.goku.dailycost.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dc_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("last_login_time")
    private String lastLoginTime;

    @TableField("role")
    private String role;

    @TableField("email")
    private String email;

    @TableField("signature")
    private String signature;


}
