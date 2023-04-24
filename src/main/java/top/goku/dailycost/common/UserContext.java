package top.goku.dailycost.common;

import top.goku.dailycost.vo.JwtUser;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/7 14:47
 */
public class UserContext {

    private static final ThreadLocal<JwtUser> CONTEXT = new InheritableThreadLocal<>();

    /**
     * set当前用户
     *
     * @param user
     */
    public static void set(JwtUser user) {
        CONTEXT.set(user);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static JwtUser getCurrentUser() {
        return CONTEXT.get();
    }

    /**
     * 清除
     */
    public static void clear() {
        CONTEXT.remove();
    }

}
