package name.elegant.phono.core.util.console;

/**
 * Created with IntelliJ IDEA.
 * User: zeqing.hzq
 * Date: 12-8-30
 * Time: ÏÂÎç9:35
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleDebuger {

    private static boolean outPutSwitch = false;

    public static void print(Object object) {
        if (outPutSwitch)
            System.out.print(object);
    }

    public static void println(Object object) {
        if (outPutSwitch)
            System.out.println(object);
    }

    public static boolean isOutPutSwitch() {
        return outPutSwitch;
    }

    public static void setOutPutSwitch(boolean outPutSwitch) {
        ConsoleDebuger.outPutSwitch = outPutSwitch;
    }
}
