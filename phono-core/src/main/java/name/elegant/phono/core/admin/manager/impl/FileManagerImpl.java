package name.elegant.phono.core.admin.manager.impl;

import name.elegant.phono.core.admin.manager.FileManager;
import name.elegant.phono.core.util.text.FileReaderUtil;

import java.util.Date;

/**
 * Author: Garry King
 * Date: 12-12-6 上午12:57
 * E-mail:flyhzq@sina.com
 */
public class FileManagerImpl implements FileManager {

    @Override
    public String readApacheAccessLog(Date begin, Date end) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        String accessContent = FileReaderUtil.readTextFile("D:\\360data\\重要数据\\桌面\\access.log");
        String[] lines = accessContent.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] sections = lines[i].split(" ");
            System.out.println(sections[0]);
        }
    }
}
