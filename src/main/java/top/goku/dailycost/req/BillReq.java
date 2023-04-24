package top.goku.dailycost.req;

import lombok.Builder;
import lombok.Data;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/22 16:08
 */
@Data
@Builder
public class BillReq {

    private Long id;

    private Integer payType;

    private Long typeId;

    private String remark;

    private Long userId;

    private Integer amount;

    private String month;
}
