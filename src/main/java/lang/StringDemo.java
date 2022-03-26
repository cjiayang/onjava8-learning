package lang;

/**
 * @author Joey Chen
 * @created 2022/3/26 15:54
 */
public class StringDemo {
    public static void main(String[] args) {
        System.out.println("===== 格式化字符串 ======");


        System.out.println("===== 字符串与数字转换 ======");
        float a = Float.parseFloat("6.18");
        float b = Float.valueOf("3.15");
        // do some arithmetic
        System.out.println("a + b = " +
                (a + b));
        System.out.println("a - b = " +
                (a - b));
        System.out.println("a * b = " +
                (a * b));
        System.out.println("a / b = " +
                (a / b));
        System.out.println("a % b = " +
                (a % b));

        System.out.println("===== 字符串示例 ======");
        final String FPATH = "/home/user/index.html";
        Filename myHomePage = new Filename(FPATH, '/', '.');
        System.out.println("Extension = " + myHomePage.extension());
        System.out.println("Filename = " + myHomePage.filename());
        System.out.println("Path = " + myHomePage.path());

        System.out.println("===== StringBuilder示例 ======");
        // 使用String
        String reverseString = reverseString("Dot saw I was Tod");
        System.out.println(reverseString);
        // 使用StringBuilder
        StringBuilder sb = new StringBuilder("Temorrow");
        System.out.println(sb.reverse());

    }


    /**
     * 反向字符串
     *
     * @param palindrome 回文字符串
     * @return {@link String}
     */
    static public String reverseString(String palindrome) {
        // "Dot saw I was Tod"
        char[] tempCharArray  = palindrome.toCharArray();
        char[] charArray = new char[tempCharArray .length];
        int length = tempCharArray .length;
        for (int i = 0; i < length; i++) {
            charArray[length - 1 - i] = tempCharArray [i];
        }
        return String.valueOf(charArray);
    }

    static class Filename {
        private final String fullPath;
        private final char pathSeparator;
        private final char extensionSeparator;

        public Filename(String str, char sep, char ext) {
            this.fullPath = str;
            this.pathSeparator = sep;
            this.extensionSeparator = ext;
        }

        public String extension() {
            int dot = fullPath.lastIndexOf(extensionSeparator + 1);
            return fullPath.substring(dot);
        }

        public String filename() {
            int sep = fullPath.lastIndexOf(pathSeparator) ;
            int dot = fullPath.lastIndexOf(extensionSeparator);
            return fullPath.substring(sep + 1, dot);
        }

        public String path() {
            int dot = fullPath.lastIndexOf(pathSeparator);
            return fullPath.substring(0, dot);
        }
    }
}
