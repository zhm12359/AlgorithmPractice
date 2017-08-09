
import java.io.*;
import java.util.*;

//reference : http://mypaper.pchome.com.tw/zerojudge/post/1322959006
public class LargestBlock {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){
            int size = Integer.parseInt(br.readLine());
            int B = Integer.parseInt(br.readLine());

            int[][] board = new int[size+1][size+1];

            for(int b=0; b<B; ++b){
                int[] tks = lineToIntArray(br.readLine());
                int x1 = tks[0];
                int y1 = tks[1];
                int x2 = tks[2];
                int y2 = tks[3];

                for(int i=x1; i<=x2; ++i){
                    for(int j=y1; j<=y2; ++j){

                        board[i][j] = 1;
                    }
                }
            }

            //print2d(board);

            int max = 0;
            int tmp = 0 ;
            int length = 0;
            int width = 0;


            for(int i=1; i<=size; ++i){
                int[] sum = new int[size+1];
                for(int j=i; j<=size; ++j){
                    for(int k=1; k<=size; ++k){
                        //System.out.printf("(%d,%d)\n", j, k);
                        if(board[j][k]==0) sum[k]+=1;
                        if(k==1 || tmp!= length*width){
                            tmp=0;
                            length=0;
                        }
                        tmp += sum[k];
                        length +=1;
                        width = j -i +1;
                        if(tmp == length * width){
                            //if(k==size) System.out.println(max);
                            //if(k==size) System.out.printf("length %d  width %d\n", length, width);
                            max = tmp > max ? tmp : max ;

                        }
                    }
                }
                //System.out.println(Arrays.toString(sum));

            }

            pw.println(max);

        }

        pw.flush();
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

    public static void print2d(int[][] a){
        for(int i=0; i<a.length; ++i){
            System.out.println(Arrays.toString(a[i]));
        }
    }
}



/*
#include <stdio.h>

int main() {
    int p, s, b;
    int r1, r2, c1, c2, i, j, k;
    scanf("%d", &p);
    while(p--) {
        scanf("%d %d", &s, &b);
        char map[101][101] = {};
        while(b--) {
            scanf("%d %d %d %d", &r1, &c1, &r2, &c2);
            for(i = r1; i <= r2; i++)
                for(j = c1; j <= c2; j++)
                    map[i][j] = 1;
        }
        int length, width, tmp = 0, ans = 0;
        for(i = 1; i <= s; i++) {
            int sum[101] = {};
            for(j = i; j <= s; j++) {
                for(k = 1; k <= s; k++) {
                    sum[k] += !map[j][k];
                    if(k == 1 || tmp != length*width)
                        tmp = 0, length = 0;
                    tmp += sum[k];
                    length++, width = j-i+1;
                    if(tmp == length*width) {
                        if(tmp > ans)
                            ans = tmp;
                    }
                }
            }
        }
        printf("%d\n", ans);
    }
    return 0;
}
 */