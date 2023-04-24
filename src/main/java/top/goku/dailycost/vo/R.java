package top.goku.dailycost.vo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import top.goku.dailycost.exception.ErrorEnum;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/6 21:08
 */
@Data
public class R<T> {

    private String msg;

    private T data;

    private int code;

    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.setData(data);
        r.setCode(0);
        r.setMsg("ok");
        return r;
    }

    public static <T> R<T> emptySuccess() {
        R<T> r = new R<>();
        r.setCode(0);
        r.setMsg("ok");
        return r;
    }

    public static <T> R<T> err(ErrorEnum errorEnum) {
        return err(errorEnum, null);
    }

    public static <T> R<T> err(ErrorEnum errorEnum, String msg) {
        R<T> r = new R<>();
        r.setCode(errorEnum.getCode());
        r.setMsg(StrUtil.isBlank(msg) ? errorEnum.getMsg() : msg);
        return r;
    }
}
