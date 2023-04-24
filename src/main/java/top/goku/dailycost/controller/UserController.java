package top.goku.dailycost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.goku.dailycost.annotation.ApiController;
import top.goku.dailycost.common.Const;
import top.goku.dailycost.entity.User;
import top.goku.dailycost.req.UserLoginReq;
import top.goku.dailycost.req.UserModifyPassReq;
import top.goku.dailycost.service.IUserService;
import top.goku.dailycost.vo.R;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
@ApiController
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    R login(@RequestBody @Valid UserLoginReq req, HttpServletRequest request) {
        String token = userService.login(req);
        request.getSession().setAttribute(Const.TOKEN_KEY, token);
        return R.success(token);
    }

    @RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
    R modifyPass(@RequestBody @Valid UserModifyPassReq req) {
        userService.modifyPass(req);
        return R.success(Boolean.TRUE);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    R register(@RequestBody @Valid UserLoginReq req) {
        return R.success(userService.register(req));
    }

    @GetMapping(value = "/getUserInfo")
    R getUserInfo() {
        return R.success(userService.getUserInfo());
    }

}
