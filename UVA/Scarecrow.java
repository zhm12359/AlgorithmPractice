import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Scarecrow {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int N = Integer.parseInt(br.readLine());
            String[] line = br.readLine().split("");

            int res=0;
            boolean left= false; //indicate if previous is fertile

            for(int i = 0; i < N; i ++)
            {
                if(left == true)
                {
                    left = false;
                    res++; //need at least one in this three cells
                    i++;
                }else if(line[i].equals("."))
                {
                    if(i + 1 == N) res++;
                    else left = true;
                }
            }

            System.out.println("Case " + (t+1) + ": " + res);


        }
    }
}

