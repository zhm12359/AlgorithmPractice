//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.*;
public class Ants {

    //static BufferedReader br;

    public static void main(String[] args) throws Exception {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for(int t=0; t<T; ++t){


            int ropeLen = sc.nextInt();
            int numAnts = sc.nextInt();
            sc.nextLine();

            int min = -1;
            int max = -1;

            for(int i=0; i<numAnts; ++i){

                int pos = sc.nextInt();

                min = Math.max(min, Math.min(pos, ropeLen-pos) );
                max = Math.max(max, Math.max(pos, ropeLen-pos) );

            }
            System.out.printf("%d %d\n", min, max);
        }


    }


}