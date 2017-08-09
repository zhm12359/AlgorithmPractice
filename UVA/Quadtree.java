//import java.io.BufferedInputStream;
//import java.util.Scanner;
//
//public class Quadtree {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(new BufferedInputStream(System.in));
//        final int len = 32;
//        int T = input.nextInt();
//        for(int i = 0; i < T; i++) {
//            int[] cnt = new int[1];
//            cnt[0] = 0;
//            int[][] buf = new int[len][len];
//
//            String s;
//            for(int j = 0; j < 2; j++) {
//                s = input.next();
//                int[] p = new int[1];
//                p[0] = 0;
//                draw(s, p, 0, 0, len, buf, cnt);
//            }
//            System.out.println("There are " + cnt[0] + " black pixels.");
//        }
//    }
//
//    public static void draw(String s, int[] p, int r, int c, int w, int[][] buf, int[] cnt) {
//        char ch = s.charAt(p[0]++);
//        if(ch == 'p') {
//            draw(s, p, r, c + w / 2, w / 2, buf, cnt);
//            draw(s, p, r, c, w / 2, buf, cnt);
//            draw(s, p, r + w / 2, c, w / 2, buf, cnt);
//            draw(s, p, r + w / 2, c + w / 2, w / 2, buf, cnt);
//        }
//        else if(ch == 'f') {
//            for(int i = r; i < r + w; i++)
//                for(int j = c; j < c + w; j++)
//                    if(buf[i][j] == 0) {
//                        buf[i][j] = 1;
//                        cnt[0]++;
//                    }
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Quadtree {
    static BufferedReader br;

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    static int readInt() throws Exception {
        int c = br.read();
        while (isWhitespace(c)) {
            c = br.read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = br.read();
        }
        int res = 0;
        do {
            res *= 10;
            res += c - '0';
            c = br.read();
        } while (!isWhitespace(c));
        return res * sgn;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int N = readInt();
        String line;

        for(int i=0; i<N; ++i){

            int[][] board = new int[32][32];

            for(int j=0; j<2; j++){
                line = br.readLine();
                int[] p = {0};
                color(line, board, 0, 0, 32, p);
            }

            int count=0;
            for(int k=0; k<32; ++k){
                for(int j=0; j<32; ++j){
                    if(board[k][j]==1){
                        count++;
                    }
                }
            }
            System.out.println("There are " + count + " black pixels.");
        }



    }

    public static void color(String s, int[][] b, int r, int c, int bl, int[] p ){

        char letter = s.charAt(p[0]++);

        if(letter == 'p'){
            color(s, b,  r, c + bl/2, bl/2, p ); // lower left corner
            color(s, b,  r, c , bl/2, p ); // upper left corner
            color(s, b,  r+ bl/2, c , bl/2, p ); // upper right corner
            color(s, b,  r+ bl/2, c+ bl/2 , bl/2, p ); //lower right corner

        }else if(letter == 'f'){

            for(int i=r; i<r+bl; ++i){
                for(int j=c; j<c+bl; ++j){
                    b[i][j]=1;
                }
            }
        }
    }

}