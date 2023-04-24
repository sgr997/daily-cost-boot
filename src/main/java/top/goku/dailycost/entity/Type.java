package top.goku.dailycost.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import top.goku.dailycost.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZXP
 * @since 2023-04-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dc_type")
public class Type extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("type_name")
    private String typeName;

    @TableField("pay_type")
    private Integer payType;

    @TableField("user_id")
    private Long userId;

    @TableField("icon")
    private String icon;


}
