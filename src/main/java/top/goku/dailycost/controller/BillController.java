package top.goku.dailycost.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.goku.dailycost.annotation.ApiController;
import top.goku.dailycost.common.UserContext;
import top.goku.dailycost.entity.Bill;
import top.goku.dailycost.service.IBillService;
import top.goku.dailycost.vo.BillDayOfMonth;
import top.goku.dailycost.vo.BillMonth;
import top.goku.dailycost.vo.BillVO;
import top.goku.dailycost.vo.JwtUser;
import top.goku.dailycost.vo.R;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
@ApiController
@RequestMapping("/bill")
public class BillController extends BaseController<Bill> {

    @Autowired
    IBillService billService;

    @Override
    R<List> list(Bill entity) {
        JwtUser currentUser = UserContext.getCurrentUser();
        LambdaQueryWrapper<Bill> wrapper = Wrappers.lambdaQuery(Bill.class).eq(Bill::getUserId, currentUser.getId());
        return R.success(billService.list(wrapper));
    }

    @ApiOperation("月统计")
    @RequestMapping("/month")
    R<BillMonth> month(String month) {
        return R.success(billService.month(month));
    }

    @ApiOperation("月明细")
    @RequestMapping("/dayByMonth")
    R<BillDayOfMonth> dayByMonth(String month, Long typeId) {
        return R.success(billService.dayOfMonth(month, typeId));
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    R<BillVO> getById(@PathVariable("id") Long id) {
        return R.success(billService.getVOById(id));
    }

    @PostMapping
    @Override
    R add(@RequestBody @Valid Bill request) {
        request.setUserId(UserContext.getCurrentUser().getId());
        return super.add(request);
    }
}
