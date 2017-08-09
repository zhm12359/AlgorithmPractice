import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class FillTheContainers {

    static BufferedReader br;
    public static int[] answer = new int[100000000];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        String line = br.readLine();

        while(line!=null){
            int[] tokens = lineToIntArray(line);
            int n = tokens[0];
            int c = tokens[1];

            int[] belt = lineToIntArray(br.readLine());

            int l=0;
            int r=answer.length-1;
            int ans = Integer.MAX_VALUE;

            while(l<=r){
                int mid = (l+r)/2;
                //System.out.println(mid);


                if(fit(mid, c, belt)){
                    r = mid - 1;
                    ans = ans > mid ? mid : ans;
                }else{
                    l = mid + 1;
                }
            }

            System.out.println(ans);


            line=br.readLine();
        }

        //test fit function:
        //int[] belt = {8,83 ,51, 81, 82, 61, 31, 55, 66, 27, 27, 67, 52, 86, 50, 44, 78, 99};
        //System.out.println( fit(140, 9, belt));

    }


    public static boolean fit(int min, int c, int[] belt){

        int p = 0;
        int sum=0;
        int total = 0;


        while(p<belt.length){



            int cur = belt[p];
            //System.out.println("cur "+cur);

            if(sum+cur<=min){
                sum += cur;
                p++;
                if(p==belt.length-1) total++;
            }else{

                total++;
                //System.out.println("sum " + sum);
                sum=0;
                //System.out.println("p "+p);
                //System.out.println("total "+ total);


            }

            if(total>c) break;


        }


        if(total<=c) return true;
        else return false;

    }

    public static void init(){

        for(int i=0; i<100000000; ++i){
            answer[i] = i;
        }

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