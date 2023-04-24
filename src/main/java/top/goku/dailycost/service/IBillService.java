package top.goku.dailycost.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.goku.dailycost.entity.Bill;
import top.goku.dailycost.req.BillReq;
import top.goku.dailycost.vo.BillDayOfMonth;
import top.goku.dailycost.vo.BillMonth;
import top.goku.dailycost.vo.BillVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
public interface IBillService extends IService<Bill> {

    List<BillVO> list(BillReq billReq);

    BillVO getVOById(Long id);

    BillMonth month(String month);

    BillDayOfMonth dayOfMonth(String month, Long typeId);
}
