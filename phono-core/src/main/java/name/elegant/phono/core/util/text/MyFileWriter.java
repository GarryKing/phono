package name.elegant.phono.core.util.text;

import name.elegant.phono.core.util.console.ConsoleDebuger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zeqing.hzq
 * Date: 12-9-1
 * Time: ����1:35
 * To change this template use File | Settings | File Templates.
 */
public class MyFileWriter {

    public static void coverStringToLocalFile(String content, String filePath) {
        File file = new File(filePath);
        try {
            if (file.exists()) {
                ConsoleDebuger.println("�ļ�����");
            } else {
                ConsoleDebuger.println("�ļ�������");
                file.createNewFile();
            }
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(content);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
