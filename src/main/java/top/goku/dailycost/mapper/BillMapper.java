package top.goku.dailycost.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.goku.dailycost.entity.Bill;
import top.goku.dailycost.req.BillReq;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
public interface BillMapper extends BaseMapper<Bill> {

    List<Bill> queryForList(BillReq req);

}
