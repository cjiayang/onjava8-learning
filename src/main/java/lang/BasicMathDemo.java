package lang;

/**
 * @author Joey Chen
 * @created 2022/3/26 15:37
 */
public class BasicMathDemo {
    public static void main(String[] args) {
        System.out.println("===== 基础运算 =====");
        double pi = -Math.PI;
        double e = Math.E;
        double b = 43.74;
        int c = 16, d = 45;
        System.out.printf("The absolute value " + "of %.5f is %.5f%n",
                pi, Math.abs(pi)); // 191.635

        System.out.printf("The ceiling of " + "%.2f is %.0f%n",
                b, Math.ceil(b));

        System.out.printf("The floor of " + "%.2f is %.0f%n",
                b, Math.floor(b));

        System.out.printf("The rint of %.2f " + "is %.0f%n",
                b, Math.rint(b));

        System.out.printf("The max of %d and " + "%d is %d%n",
                c, d, Math.max(c, d));

        System.out.printf("The min of of %d " + "and %d is %d%n",
                c, d, Math.min(c, d));



    }
}
