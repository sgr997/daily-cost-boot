package top.goku.dailycost.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/22 12:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDayOfMonth {

    private Integer totalExpense;

    private Integer totalIncome;

    private List<Detail> records;

    public static BillDayOfMonth empty() {
        BillDayOfMonth res = new BillDayOfMonth();
        res.setTotalExpense(0);
        res.setTotalExpense(0);
        res.setRecords(new ArrayList<>());
        return res;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {

        private String date;

        private List<BillVO> bills;
    }
}
