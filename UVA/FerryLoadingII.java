import java.io.*;
import java.util.*;
public class FerryLoadingII {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int cases=0; cases<T; ++cases ){

            int[] tokens = lineToIntArray(br.readLine());
            int n = tokens[0]; //capacity
            int t = tokens[1]; //time for single trip
            int m = tokens[2]; // total number of cars

            int[] schedule = new int[m];

            for(int i=0; i<m; ++i){
                schedule[i] = Integer.parseInt(br.readLine());
            }

            //greedy thoughts: it's guarenteed that time is the shortest
            // if we reach max capacity and then ship out for the last car

            int time=0;
            int trips=0;
            int p=0;

            if(m%n==0){
                time += schedule[n-1] + 2*t;
                p=n;
                ++trips;
            }else{
                time += schedule[m%n-1] + 2*t;
                ++trips;
                p=m%n;
            }

            for(int i=p; i<m; i+=n){


                if(i+n >=m){
                    if(time<schedule[m-1]) time = (schedule[m-1]+2*t);
                    else time+=2*t;
                    ++trips;
                }else{
                    if(time<schedule[i+n-1]) time = (schedule[i+n-1] + 2*t);
                    else time+= 2*t;

                    ++trips;
                }


            }

            time = time -t; //no need to go back last trip

            pw.printf("%d %d\n", time, trips);
            pw.flush();


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