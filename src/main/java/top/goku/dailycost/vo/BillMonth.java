package top.goku.dailycost.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.goku.dailycost.entity.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/18 21:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillMonth {

    private Integer totalExpense;

    private Integer totalIncome;

    private List<StatisticMonth> records;

    public static BillMonth empty() {
        BillMonth res = new BillMonth();
        res.setTotalExpense(0);
        res.setTotalExpense(0);
        res.setRecords(new ArrayList<>());
        return res;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatisticMonth {

        private Type type;

        private Integer amount;

        private Integer payType;
    }
}
