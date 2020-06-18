package util;

import java.text.DecimalFormat;

public class Util {
    public static String numberToCommaFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }
}
