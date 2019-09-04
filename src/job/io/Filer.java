package job.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Filer {

    public void copy(String source, String target) throws IOException {
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);
        byte[] buffer = new byte[4 * 1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
    }

    public void copy(String source, String target, boolean nio) throws IOException {
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);
        FileChannel inC = in.getChannel();
        FileChannel outC = out.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4 * 1024);
        while (inC.read(buffer) != -1) {
            outC.write(buffer);
            buffer.clear();
        }
        in.close();
        inC.close();
        out.close();
        outC.close();
    }
}
