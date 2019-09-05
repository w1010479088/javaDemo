package job;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

public class StringCounter {
    public static void main(String[] args) {
        try {
            int count = new StringCounter().count("~/Documents/Projects/Java/javaDemo/src/job/reader.txt", "while");
            LogUtil.log("Count = " + count);
        } catch (FileNotFoundException e) {
            LogUtil.log("Error Main = " + e.getMessage());
        }
    }

    public int count(String path, String target) throws FileNotFoundException {
        int count = 0;
        FileReader fr = null;
        BufferedReader bf = null;
        String line;
        try {
            fr = new FileReader(path);
            bf = new BufferedReader(fr);
            while ((line = bf.readLine()) != null) {
                int index;
                while (line.length() >= target.length() && (index = line.indexOf(target)) != -1) {
                    count++;
                    line = line.substring(index + target.length());
                }
            }
        } catch (Exception ex) {
            error(ex);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception ex) {
                error(ex);
            }

            try {
                if (bf != null) {
                    bf.close();
                }
            } catch (Exception ex) {
                error(ex);
            }
        }
        return count;
    }

    public int count(String path, String target, boolean tokener) {
        int count = 0;
        StringTokenizer token = new StringTokenizer(path);
        while (token.hasMoreTokens()) {
            if (target.equals(token.nextToken())) {
                count++;
            }
        }
        return count;
    }

    private void error(Exception ex) {
        LogUtil.log("Error = " + ex.getMessage());
    }
}
