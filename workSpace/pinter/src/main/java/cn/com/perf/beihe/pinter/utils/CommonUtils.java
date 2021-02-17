package cn.com.perf.beihe.pinter.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static String generateRandomMoney() {
        int num = (int)(Math.random() * 8.9999999E7D + 1.0E7D);
        BigDecimal bd = new BigDecimal(String.valueOf(num));
        DecimalFormat df = new DecimalFormat(",###,##0");
        return "$ " + df.format(bd);
    }

    public static String converTime(Long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp.longValue());
        return format.format(date);
    }

    public static void main(String[] args) {
        System.out.println(converTime(Long.valueOf(System.currentTimeMillis())));
    }
}
