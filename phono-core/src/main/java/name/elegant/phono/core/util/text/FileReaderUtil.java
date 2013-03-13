package name.elegant.phono.core.util.text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Author: Garry King
 * Date: 12-12-5 ÏÂÎç11:13
 * E-mail:flyhzq@sina.com
 */
public class FileReaderUtil {

    public static String readTextFile(String path) {
        String result = "";
        BufferedReader inputFile = null;
        try {
            String tempLine = "";
            inputFile = new BufferedReader(new FileReader(path));
            while ((tempLine = inputFile.readLine()) != null) {
                result += tempLine + "\n";
            }
            inputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
