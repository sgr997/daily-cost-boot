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
 * @since 2023-04-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dc_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("dict_type")
    private String dictType;

    @TableField("dict_name")
    private String dictName;

    @TableField("dict_val")
    private String dictVal;

    @TableField("status")
    private String status;

    @TableField("weight")
    private Integer weight;

    @TableField("dict_desc")
    private String dictDesc;


}
