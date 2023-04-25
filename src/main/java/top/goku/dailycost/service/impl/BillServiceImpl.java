package top.goku.dailycost.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.goku.dailycost.common.UserContext;
import top.goku.dailycost.entity.Bill;
import top.goku.dailycost.entity.Type;
import top.goku.dailycost.enums.PayType;
import top.goku.dailycost.mapper.BillMapper;
import top.goku.dailycost.req.BillReq;
import top.goku.dailycost.service.IBillService;
import top.goku.dailycost.service.ITypeService;
import top.goku.dailycost.utils.AssertUtils;
import top.goku.dailycost.utils.TimeUtils;
import top.goku.dailycost.vo.BillDayOfMonth;
import top.goku.dailycost.vo.BillMonth;
import top.goku.dailycost.vo.BillVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZXP
 * @since 2023-04-17
 */
@Service
@AllArgsConstructor
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    ITypeService typeService;

    @Override
    public List<BillVO> list(BillReq billReq) {
        billReq.setUserId(UserContext.getCurrentUser().getId());
        List<Bill> bills = getBaseMapper().queryForList(billReq);
        if (CollectionUtil.isEmpty(bills)) {
            return new ArrayList<>();
        }
        // 获取类型名
        Map<Long, Type> typeById = typeService.typeById(bills.stream().map(Bill::getTypeId).collect(Collectors.toList()));
        return bills.stream().map(bill -> {
            BillVO billVO = Convert.convert(BillVO.class, bill);
            billVO.setType(typeById.get(billVO.getTypeId()));
            return billVO;
        }).collect(Collectors.toList());
    }

    @Override
    public BillVO getVOById(Long id) {
        Bill bill = getById(id);
        AssertUtils.notNull(bill, "账单数据不存在");
        Map<Long, Type> typeById = typeService.typeById(Arrays.asList(bill.getTypeId()));
        BillVO billVO = Convert.convert(BillVO.class, bill);
        billVO.setType(typeById.get(billVO.getTypeId()));
        return billVO;
    }

    @Override
    public BillMonth month(String month) {
        BillReq billReq = BillReq.builder()
                .month(month)
                .userId(UserContext.getCurrentUser().getId())
                .build();
        List<BillVO> bills = list(billReq);
        if (CollectionUtil.isEmpty(bills)) {
            return BillMonth.empty();
        }
        // 按照类型分组
        Map<Type, List<BillVO>> billByType = bills.stream().collect(Collectors.groupingBy(BillVO::getType));
        return BillMonth.builder()
                .totalExpense(bills.stream().filter(bill -> bill.getPayType() == PayType.EXPENSE.getCode()).mapToInt(BillVO::getAmount).sum())
                .totalIncome(bills.stream().filter(bill -> bill.getPayType() == PayType.INCOME.getCode()).mapToInt(BillVO::getAmount).sum())
                .records(billByType.keySet().stream().map(type -> {
                    BillMonth.StatisticMonth statisticMonth = new BillMonth.StatisticMonth();
                    statisticMonth.setType(type);
                    statisticMonth.setPayType(type.getPayType());
                    statisticMonth.setAmount(billByType.get(type).stream()
                            .filter(bill -> bill.getPayType().equals(type.getPayType())).mapToInt(BillVO::getAmount).sum());
                    return statisticMonth;
                }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public BillDayOfMonth dayOfMonth(String month, Long typeId) {
        BillReq billReq = BillReq.builder()
                .month(month)
                .typeId(typeId)
                .userId(UserContext.getCurrentUser().getId())
                .build();
        List<BillVO> bills = list(billReq);
        if (CollectionUtil.isEmpty(bills)) {
            return BillDayOfMonth.empty();
        }
        // 根据yyyy-MM-dd分组
        Map<String, List<BillVO>> byMonth = bills.stream().collect(Collectors.groupingBy(
                bill -> TimeUtils.getYMD(bill.getDate())));
        BillDayOfMonth res = new BillDayOfMonth();
        res.setTotalExpense(bills.stream().filter(bill ->
                bill.getPayType() == PayType.EXPENSE.getCode()).mapToInt(BillVO::getAmount).sum());
        res.setTotalIncome(bills.stream().filter(bill ->
                bill.getPayType() == PayType.INCOME.getCode()).mapToInt(BillVO::getAmount).sum());
        res.setRecords(
                byMonth.keySet().stream().map(dayOfMonth -> {
                    BillDayOfMonth.Detail detail = new BillDayOfMonth.Detail();
                    detail.setDate(dayOfMonth);
                    detail.setBills(byMonth.get(dayOfMonth));
                    return detail;
                }).sorted(Comparator.comparing(BillDayOfMonth.Detail::getDate).reversed())
                        .collect(Collectors.toList()));
        return res;
    }
}
