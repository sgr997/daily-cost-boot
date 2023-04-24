package top.goku.dailycost.req;

import lombok.Data;
import top.goku.dailycost.vo.PageRequest;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/17 21:32
 */
@Data
public class BillPageReq extends PageRequest {

    private String date;

    private String typeId;
}
