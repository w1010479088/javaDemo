package test.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import test.util.LogUtil;

public class IOTest {
    private static final String FILE_PATH = "C:/user/mac/test.txt";

    public static void main(String[] args) {
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(FILE_PATH)));
//            StringBufferInputStream in2 = new StringBufferInputStream(FILE_PATH);
            DataInputStream in3 = new DataInputStream(new StringBufferInputStream(FILE_PATH));
//            LineNumberInputStream in3 = new LineNumberInputStream(new StringBufferInputStream(FILE_PATH));
            PrintStream in4 = new PrintStream(new BufferedOutputStream(new FileOutputStream(FILE_PATH)));
            DataOutputStream out1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_PATH)));
            RandomAccessFile rf = new RandomAccessFile(FILE_PATH, "rw");
            rf.seek(100);


            BufferedReader in01 = new BufferedReader(new FileReader(FILE_PATH));
            BufferedReader in02 = new BufferedReader(new InputStreamReader(System.in));
            StringReader in03 = new StringReader(FILE_PATH);
            DataInputStream in04 = new DataInputStream(new StringBufferInputStream(FILE_PATH));
            LineNumberReader in05 = new LineNumberReader(new StringReader(FILE_PATH));
            PrintWriter in06 = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH)));

        } catch (Exception ex) {
            LogUtil.log(ex.getMessage());
        }
    }

    public void gzip() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_PATH));
            BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(FILE_PATH)));

            BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(FILE_PATH))));
        } catch (Exception ex) {
            //ignore
        }
    }

    public void zip() {
        try {
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new CheckedOutputStream(new FileOutputStream(FILE_PATH), new Adler32())));
            out.setComment("A test of Java Zipping");

            BufferedReader in = new BufferedReader(new FileReader(FILE_PATH));
            out.putNextEntry(new ZipEntry(FILE_PATH));

            ZipInputStream in2 = new ZipInputStream(new BufferedInputStream(new CheckedInputStream(new FileInputStream(FILE_PATH), new Adler32())));

            ZipFile zf = new ZipFile(FILE_PATH);
            Enumeration<? extends ZipEntry> entries = zf.entries();
            while (entries.hasMoreElements()) {
                entries.nextElement();
            }
        } catch (Exception ex) {
            //ignore
        }
    }

    private void seriziable() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm out"));
            out.writeObject("Worm Storage");
            out.writeObject(new Object());

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm out"));
            Object o = in.readObject();

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out2 = new ObjectOutputStream(bout);
            out2.writeObject("Worm Storage");
            out2.writeObject(new Object());

            ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
            in2.readObject();
            in2.readObject();
        } catch (Exception ex) {
            //ignore
        }
    }

    public class InFile extends DataInputStream {

        InFile(String fileName) throws IOException {
            super(new BufferedInputStream(new FileInputStream(fileName)));
        }

        public InFile(File file) throws IOException {
            this(file.getPath());
        }
    }

    public class PrintFile extends PrintStream {

        PrintFile(String fileName) throws IOException {
            super(new BufferedOutputStream(new FileOutputStream(fileName)));
        }

        public PrintFile(File file) throws IOException {
            this(file.getPath());
        }
    }

    public class OutFile extends DataOutputStream {

        OutFile(String fileName) throws IOException {
            super(new BufferedOutputStream(new FileOutputStream(fileName)));
        }

        public OutFile(File file) throws IOException {
            this(file.getPath());
        }
    }

    public class External implements Externalizable{

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {

        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        }
    }
}