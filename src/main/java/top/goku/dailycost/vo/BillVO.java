package top.goku.dailycost.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.goku.dailycost.entity.Type;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/22 12:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BillVO {

    /**
     * id
     */
    private Long id;

    /**
     * 账单类型：1支出，2收入
     */
    private Integer payType;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 类型
     */
    private Type type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 金额：分
     */
    private Integer amount;

    /**
     * 时间
     */
    private Long date;

    /**
     * 创建时间
     */
    private Long createTime;
}
