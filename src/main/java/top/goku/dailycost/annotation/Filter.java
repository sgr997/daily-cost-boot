package top.goku.dailycost.annotation;

import org.springframework.core.annotation.AliasFor;
import top.goku.dailycost.common.Logic;
import top.goku.dailycost.common.Operator;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/18 20:28
 */
public @interface Filter {

    @AliasFor("column")
    String value();

    @AliasFor("value")
    String column();

    Logic logic() default Logic.AND;

    Operator operator() default Operator.EQ;
}
