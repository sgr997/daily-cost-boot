package top.goku.dailycost.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.goku.dailycost.common.UserContext;
import top.goku.dailycost.entity.User;
import top.goku.dailycost.exception.DailyCostException;
import top.goku.dailycost.exception.ErrorEnum;
import top.goku.dailycost.mapper.UserMapper;
import top.goku.dailycost.req.UserLoginReq;
import top.goku.dailycost.req.UserModifyPassReq;
import top.goku.dailycost.service.IUserService;
import top.goku.dailycost.utils.AssertUtils;
import top.goku.dailycost.utils.JwtUtils;
import top.goku.dailycost.vo.JwtUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public String login(UserLoginReq request) {
        Optional<User> optionalUser = super.lambdaQuery().eq(User::getUsername, request.getUsername().trim()).oneOpt();
        User user = optionalUser.orElseThrow(() -> new DailyCostException(ErrorEnum.DATA_NOT_EXIST, "用户不存在"));
        AssertUtils.isTrue(SecureUtil.md5(request.getPassword().trim()).equals(user.getPassword()), "用户名或密码错误");
        user.setLastLoginTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:dd")));
        super.updateById(user);
        return JwtUtils.createToken(Convert.convert(JwtUser.class, user));
    }

    @Override
    public boolean register(UserLoginReq request) {
        AssertUtils.isNull(super.lambdaQuery().eq(User::getUsername, request.getUsername().trim()).one()
                , "用户名已存在,请更换后重试~~");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(SecureUtil.md5(request.getPassword()));
        return super.save(user);
    }

    @Override
    public User getUserInfo() {
        JwtUser user = UserContext.getCurrentUser();
        return super.getById(user.getId());
    }

    @Override
    public void modifyPass(UserModifyPassReq req) {
        AssertUtils.isTrue(StrUtil.equals(req.getPassword(), req.getConfirmPassword()), "两次输入的密码不同");
        User user = super.getById(UserContext.getCurrentUser().getId());
        AssertUtils.isTrue(SecureUtil.md5(req.getOldPassword().trim()).equals(user.getPassword()), "旧密码错误");
        user.setPassword(SecureUtil.md5(req.getPassword()));
        super.updateById(user);
    }
}
