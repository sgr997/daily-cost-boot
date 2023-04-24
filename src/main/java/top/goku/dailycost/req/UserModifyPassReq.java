package top.goku.dailycost.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/17 20:39
 */
@Data
public class UserModifyPassReq {

    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
