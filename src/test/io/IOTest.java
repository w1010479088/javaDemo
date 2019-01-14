package test.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringBufferInputStream;
import java.io.StringReader;

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

    public class InFile extends DataInputStream {

        public InFile(String fileName) throws IOException {
            super(new BufferedInputStream(new FileInputStream(fileName)));
        }

        public InFile(File file) throws IOException {
            this(file.getPath());
        }
    }

    public class PrintFile extends PrintStream {

        public PrintFile(String fileName) throws IOException {
            super(new BufferedOutputStream(new FileOutputStream(fileName)));
        }

        public PrintFile(File file) throws IOException {
            this(file.getPath());
        }
    }

    public class OutFile extends DataOutputStream {

        public OutFile(String fileName) throws IOException {
            super(new BufferedOutputStream(new FileOutputStream(fileName)));
        }

        public OutFile(File file) throws IOException {
            this(file.getPath());
        }
    }
}