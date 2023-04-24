package top.goku.dailycost.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/7 14:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;
}
