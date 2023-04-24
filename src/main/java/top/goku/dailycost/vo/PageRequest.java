package top.goku.dailycost.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/13 10:54
 */
@Setter
@Getter
@ApiModel("分页请求")
public class PageRequest {

    @Positive(message = "页码最小为1")
    @ApiModelProperty("页码")
    private Integer pageIndex = 1;

    @Positive(message = "每页数量最小为1")
    @ApiModelProperty("每页数量")
    private Integer pageSize = 20;

    private String value;
}
