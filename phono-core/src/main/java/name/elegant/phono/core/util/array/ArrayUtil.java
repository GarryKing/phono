package name.elegant.phono.core.util.array;

/**
 * Company:tmall.com
 * User: zeqing.hzq
 * Date: 12-9-4 ионГ10:57
 */
public class ArrayUtil {

    public static String[] expandStringArray(String[] array) {
        String[] newArrays = new String[array.length * 2];
        System.arraycopy(array, 0, newArrays, 0, array.length);
        return newArrays;
    }

}
