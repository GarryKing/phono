package name.elegant.phono.core.util.net;

import name.elegant.phono.core.util.console.ConsoleDebuger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * User: zeqing.hzq
 * Date: 12-8-30
 * Time: ÏÂÎç10:12
 */
public class NetConnection {

    public static String getURLContent(String link) {
        String result = "";
        URL url = null;
        InputStream inPutStream = null;
        if (link == null) {
            return "";
        }
        try {
            url = new URL(link);
            inPutStream = url.openConnection().getInputStream();
            InputStreamReader inReader = new InputStreamReader(inPutStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inReader);
            int n = 0;
            String tempLine;
            StringBuilder sb = new StringBuilder();
            while ((tempLine = bufferedReader.readLine()) != null) {
                sb.append(tempLine).append("\n");
            }
            ConsoleDebuger.println("======Begin to Out put======");
            ConsoleDebuger.println(sb.toString());
            ConsoleDebuger.println("======End to Out put======");
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getSubStringWithTag(String source, String begin, String end) {
        ConsoleDebuger.println("source:" + source);
        ConsoleDebuger.println("begin:" + begin + " , index:" + source.indexOf(begin));
        ConsoleDebuger.println("end:" + end + " , index:" + source.indexOf(end));
        return source.substring(source.indexOf(begin) + 1, source.indexOf(end));
    }

}
