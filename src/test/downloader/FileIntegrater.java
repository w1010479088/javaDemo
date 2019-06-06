package test.downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileIntegrater implements Runnable {
    private File mSourceFile;
    private List<File> mCacheFiles;
    private IntegrateListener mListener;

    public FileIntegrater(File sourceFile, List<File> cacheFiles, IntegrateListener listener) {
        this.mSourceFile = sourceFile;
        this.mCacheFiles = cacheFiles;
        this.mListener = listener;
    }

    private File sourceFile() {
        return mSourceFile;
    }

    private List<File> cacheFiles() {
        return mCacheFiles;
    }

    private IntegrateListener listener() {
        return mListener;
    }

    @Override
    public void run() {
        long length = 0;
        for (File file : cacheFiles()) {
            length += file.length();
        }
        if (sourceFile().length() == length) {
            FileOutputStream os = null;
            List<FileInputStream> iss = new ArrayList<>();
            int len;
            byte[] bytes = new byte[4096];
            try {
                os = new FileOutputStream(sourceFile());
                for (File file : cacheFiles()) {
                    FileInputStream is = new FileInputStream(file);
                    iss.add(is);
                    while ((len = is.read(bytes)) != -1) {
                        os.write(bytes, 0, len);
                    }
                }
                listener().complete();
            } catch (Exception ex) {
                listener().onError(ex);
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                    for (FileInputStream is : iss) {
                        if (is != null) {
                            is.close();
                        }
                    }
                } catch (Exception ex) {
                    listener().onError(ex);
                }
            }
        } else {
            listener().log("文件大小校验不正确,取消操作!正常文件大小为:" + sourceFile().length() + "-- 实际碎片整合之后大小为: " + length);
        }
    }

}
