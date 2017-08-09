import java.io.*;
import java.util.*;
public class JillRidesAgain {

    //static BufferedReader br;

    public static void main(String[] args) throws Exception {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int R = sc.nextInt();

        for(int r=1; r<=R; ++r){

            int s = sc.nextInt();

            int[] stops = new int[s];

            for(int i=1; i<s; ++i){
                stops[i] = sc.nextInt();
            }

            int sum=0;
            int max=0;

            int start=0;
            int end=0;
            int cut=0;

            for(int i=1; i<s; ++i){

                sum+=stops[i];
                if(sum>max){
                    max=sum;
                    end = i;
                    start = cut;
                }else if(sum==max){
                    if(i-cut > end - start){
                        end = i;
                        start = cut;
                    }
                }

                if(sum<0){
                    cut = i;
                    sum=0;
                }
            }

            if(max>0){
                pw.printf("The nicest part of route %d is between stops %d and %d\n", r, start + 1, end + 1);
            }else{
                pw.printf("Route %d has no nice parts\n", r);
            }


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
}