
import java.io.*;
import java.util.*;
public class OneChicken {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int[] tokens = lineToIntArray(br.readLine());
        int n1 = tokens[0];
        int n2 = tokens[1];

        if(n1>n2){
            if(n1-n2==1){
                System.out.println("Dr. Chaz needs 1 more piece of chicken!");
            }else{
                System.out.printf("Dr. Chaz needs %d more pieces of chicken!\n", n1-n2);
            }
        }else if(n2>n1){
            if(n2-n1==1){
                System.out.println("Dr. Chaz will have 1 piece of chicken left over!");
            }else{
                System.out.printf("Dr. Chaz will have %d pieces of chicken left over!\n", n2-n1);
            }
        }

    }

    public static int[] lineToIntArray(String line){
        String[] token = line.split("\\s+");
        int len = token.length;
        int[] res = new int[len];
        for(int i=0; i<len; ++i ){
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }
}