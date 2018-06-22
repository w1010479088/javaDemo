package test.memory_share;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.FileChannel.MapMode;

public class NIOReadLock {

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(NIOWrite.FILE_PATH, "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, NIOWrite.FILE_SHARE_SIZE);
        FileLock flock = null;

        for (int i = 0; i < 26; i++) {
            flock = fc.lock();//上锁
            System.out.println(System.currentTimeMillis() + ":read:" + (char) mbb.get(i));
            flock.release();//释放锁
            Thread.sleep(3000);
        }
    }
}
