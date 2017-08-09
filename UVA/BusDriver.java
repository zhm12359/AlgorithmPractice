
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BusDriver {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;


        while(true){
            line = br.readLine();
            String[] token = line.split(" ");
            int n = Integer.parseInt(token[0]);
            int d = Integer.parseInt(token[1]);
            int r = Integer.parseInt(token[2]);

            if(n==0 && d==0 && r==0) break;

            String[] zao = br.readLine().split(" ");
            String[] wan = br.readLine().split(" ");

            int[] left = new int[n];
            int[] night = new int[n];

            for(int i=0; i<n; i++){
                left[i] = d - Integer.parseInt(zao[i]);
                night[i] = Integer.parseInt(wan[i]);
            }

            Arrays.sort(left);
            Arrays.sort(night);

            int min=0;
            for(int i=0; i<n; ++i){

                int needTo = night[i] - left[i];
                if(needTo<0) continue;

                min = min + (needTo)*r;
            }

            System.out.println(min);



        }

    }
}
