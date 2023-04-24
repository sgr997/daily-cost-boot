package top.goku.dailycost.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/6 21:26
 */
@Setter
@Getter
public class DailyCostException extends RuntimeException{

    private ErrorEnum errorEnum;

    public DailyCostException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.errorEnum = errorEnum;
    }

    public DailyCostException(ErrorEnum errorEnum, String message) {
        super(message);
        this.errorEnum = errorEnum;
    }
}
