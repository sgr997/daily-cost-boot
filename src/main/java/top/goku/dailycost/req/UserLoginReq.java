package top.goku.dailycost.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/17 20:39
 */
@Data
public class UserLoginReq {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
