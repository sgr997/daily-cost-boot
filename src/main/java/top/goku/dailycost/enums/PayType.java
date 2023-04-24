package top.goku.dailycost.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/22 16:58
 */
@Getter
@AllArgsConstructor
public enum PayType {

    EXPENSE(1, "支出"),

    INCOME(2, "收入"),

    ;

    private final int code;

    private final String desc;


}
