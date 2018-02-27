package pers.bolin.androidutils.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 金额显示工具
 * <p>
 * Created by H-Bolin on 2018/2/27.
 */
public class MyMoneyUtil {

    /**
     * 将int格式的金额转换为0.00格式的金额，例如2000转化为20.00
     *
     * @param money
     * @return
     */
    public static String intToMoneyFormat(int money) {
        BigDecimal bigDecimal = new BigDecimal(money);
        // BigDecimal除法,并保留小数后两位
        BigDecimal result = bigDecimal.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        DecimalFormat format = new DecimalFormat("##0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return format.format(result);//format 返回的是字符串
    }
}
