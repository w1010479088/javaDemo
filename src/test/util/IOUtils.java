package test.util;

import java.io.File;
import java.io.IOException;

/**
 * IO流工具类
 * Created by mac on 2017/4/14.
 */

public class IOUtils {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File newFile(String filePath, String fileName) throws IOException {
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(fileDir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void delFile(String pathName) {
        File file = new File(pathName);
        delFile(file);
    }

    public static void delFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
}
