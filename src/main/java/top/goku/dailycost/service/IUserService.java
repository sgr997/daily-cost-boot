package top.goku.dailycost.service;

import top.goku.dailycost.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.goku.dailycost.req.UserLoginReq;
import top.goku.dailycost.req.UserModifyPassReq;
import top.goku.dailycost.vo.JwtUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
public interface IUserService extends IService<User> {

    String login(UserLoginReq req);

    boolean register(UserLoginReq req);

    User getUserInfo();

    void modifyPass(UserModifyPassReq req);
}
