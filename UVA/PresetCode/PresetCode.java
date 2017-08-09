package PresetCode;

import java.io.*;
import java.util.*;
public class PresetCode {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());
    }

    public static int[] lineToIntArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }

    public static double[] lineToDoubleArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }

}
//
//    public static boolean isWhitespace(int c) {
//        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
//    }
//
//    static int readInt() throws Exception {
//        int c = br.read();
//        while (isWhitespace(c)) {
//            c = br.read();
//        }
//        int sgn = 1;
//        if (c == '-') {
//            sgn = -1;
//            c = br.read();
//        }
//        int res = 0;
//        do {
//            res *= 10;
//            res += c - '0';
//            c = br.read();
//        } while (!isWhitespace(c));
//        return res * sgn;
//    }
//
//    public static void main2(String[] args) throws Exception {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        int N = readInt();
//    }
//
//}