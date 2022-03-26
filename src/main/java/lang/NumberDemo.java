package lang;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Joey Chen
 * @created 2022/3/26 14:06
 */
public class NumberDemo {
    public static void main(String[] args) {
        Integer num = 4;
        String stringVar = num.toString();
        int intVar = Integer.parseInt("1");
        float floatVar = 3.14f;
        int compare = num.compareTo(3);

        System.out.format("The value of " + "the float variable is " +
                "%f,\n while the value of the " + "integer variable is %d,\n " +
                "and the string is %s", floatVar, intVar, stringVar);


        System.out.println("\n===== 整数格式化 =====");
        long n = 123456;
        System.out.format("%d%n", n);
        System.out.printf("%8d%n", n);
        System.out.printf("%08d%n", n);
        System.out.printf("%+8d%n", n);
        System.out.printf("%,8d%n", n);
        System.out.printf("%+,8d%n", n);

        System.out.println("===== 浮点数格式化 =====");

        double pi = Math.PI;
        System.out.printf("%f%n", pi);
        System.out.printf("%.3f%n", pi);
        System.out.printf("%10.3f%n", pi);
        System.out.printf("%-10.3f%n", pi);
        System.out.printf(Locale.FRANCE, "%-10.4f%n", pi);

        System.out.println("===== 日期格式化 =====");

        Calendar c = Calendar.getInstance();
        System.out.format("%tB %te, %tY%n", c, c, c); // -->  "May 29, 2006"
        System.out.format("%tl:%tM %tp%n", c, c, c);  // -->  "2:34 am"
        System.out.format("%tD%n", c);

        System.out.println("===== DecimalFormat =====");

        customFormat("###,###.###", 12345.23);
        customFormat("###.##", 12345.234);
        customFormat("000000.00", 12345.234);
        customFormat("$###.###", 12345.234);

    }

    static public void customFormat(String pattern, double value) {
        DecimalFormat df = new DecimalFormat(pattern);
        String output = df.format(value);
        System.out.println(value + " " + pattern + " " + output);
    }


}
