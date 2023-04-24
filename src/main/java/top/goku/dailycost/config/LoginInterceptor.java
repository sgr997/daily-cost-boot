package top.goku.dailycost.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.goku.dailycost.common.Const;
import top.goku.dailycost.common.UserContext;
import top.goku.dailycost.utils.JwtUtils;
import top.goku.dailycost.vo.JwtUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/7 14:17
 */
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String token = JwtUtils.getToken(request);
        JwtUser user = JwtUtils.verify(token);
        UserContext.set(user);
        request.getSession().setAttribute(Const.TOKEN_KEY, token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clear();
    }
}
