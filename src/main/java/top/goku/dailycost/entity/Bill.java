package top.goku.dailycost.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dc_bill")
public class Bill extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("pay_type")
    private Integer payType;

    @TableField("type_id")
    private Long typeId;

    @TableField("remark")
    private String remark;

    @TableField("user_id")
    private Long userId;

    @TableField("amount")
    private Long amount;

    @TableField("date")
    private Long date;

}
