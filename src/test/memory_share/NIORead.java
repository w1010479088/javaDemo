package test.memory_share;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class NIORead {

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(NIOWrite.FILE_PATH, "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, NIOWrite.FILE_SHARE_SIZE);
        int lastIndex = 0;

        for (int i = 1; i < 27; i++) {
            int flag = mbb.get(0);                      //取读写数据的标志
            int index = mbb.get(1);                     //读取数据的位置,2为可读
            if (flag != 2 || index == lastIndex) {      //假如不可读，或未写入新数据时重复循环
                i--;
                continue;
            }

            lastIndex = index;
            System.out.println(System.currentTimeMillis() + ":position:" + index + "read:" + (char) mbb.get(index));

            mbb.put(0, (byte) 0);                //置第一个字节为可读标志为 0
            if (index == 27) {                         //读完数据后退出
                break;
            }
        }
    }
}
