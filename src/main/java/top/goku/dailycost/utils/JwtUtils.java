package top.goku.dailycost.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.alibaba.fastjson.JSONObject;
import top.goku.dailycost.common.Const;
import top.goku.dailycost.vo.JwtUser;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/7 14:26
 */
public class JwtUtils {

    private static final String KEY = "ayJSklo4878uwetv13";

    private static final String USER_KEY = "user";


    public static String createToken(JwtUser user) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(USER_KEY, JSONObject.toJSONString(user));
        payload.put(JWT.ISSUED_AT, DateUtil.date());
        payload.put(JWT.EXPIRES_AT, DateUtil.date().offset(DateField.DAY_OF_MONTH, 7));
        return JWTUtil.createToken(payload, KEY.getBytes(StandardCharsets.UTF_8));
    }


    public static String refreshToken(String token) {
        JwtUser verify = verify(token);
        return createToken(verify);
    }

    public static JwtUser verify(String token) {
        if (StrUtil.isBlank(token)) {
            throw new JWTException("No token to verify!");
        }
        JWT jwt = JWTUtil.parseToken(token);
        jwt.setKey(KEY.getBytes(StandardCharsets.UTF_8)).verify();
        JWTValidator.of(jwt).validateDate(DateUtil.date());
        return JSONObject.parseObject(jwt.getPayload(USER_KEY).toString(), JwtUser.class);
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(Const.TOKEN_KEY);
        if (StrUtil.isBlank(token)) {
            token = StrUtil.toStringOrNull(request.getSession().getAttribute(Const.TOKEN_KEY));
        }
        return token;
    }
}
