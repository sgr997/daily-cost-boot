package top.goku.dailycost.exception;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/6 21:11
 */
public enum ErrorEnum {

    PARAMS_ERROR(100, "参数不符合约定"),

    DATA_NOT_EXIST(101, "数据不存在"),

    TOKEN_INVALID(200, "请登录后重试~~"),

    REMOTE_ERROR(300, "远程访问异常~~"),

    PERMISSIONS_ERROR(400, "权限不足"),

    SYS_ERR(500, "系统异常~~"),

    ;

    private final int code;

    private final String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
