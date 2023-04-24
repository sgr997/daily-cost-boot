package top.goku.dailycost.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2023/4/22 16:52
 */
public class TimeUtils {

    public static final String YMD = "yyyy-MM-dd";

    public static String getYMD(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD);
        return sdf.format(new Timestamp(timestamp));
    }
}
