package test.memory_share;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class NIOWrite {
    public static final String FILE_PATH = "/Users/mac/Desktop/test.txt";
    public static final int FILE_SHARE_SIZE = 1024;

    public static void main(String[] args) throws Exception {
        //建立文件和内存的映射，即时双向同步
        RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, FILE_SHARE_SIZE);

        //清除文件内容 ，对 MappedByteBuffer就是对文件的操作
        for (int i = 0; i < FILE_SHARE_SIZE; i++) {
            mbb.put(i, (byte) 0);
        }

        //从文件的第二个字节开始，依次写入 A-Z 字母，第一个字节指明了当前操作的位置
        for (int i = 65; i < 91; i++) {
            int index = i - 63;
            int flag = mbb.get(0);              //可读标置第一个字节为 0
            if (flag != 0) {                    //不是可写标示 0，则重复循环，等待
                i--;
                continue;
            }
            mbb.put(0, (byte) 1);        //正在写数据，标志第一个字节为 1
            mbb.put(1, (byte) (index));  //文件第二个字节说明，写数据的位置

            mbb.put(index, (byte) i);          //index 位置写入数据
            mbb.put(0, (byte) 2);        //置可读数据标志第一个字节为 2

            System.out.println(System.currentTimeMillis() + ":position:" + index + "write:" + (char) i);
            Thread.sleep(3000);
        }
    }
}
