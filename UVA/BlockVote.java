import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BlockVote {

    public static int threshold;
    public static int N;
    public static int[] vote = new int[21];
    public static int[] power = new int[21];

    static BufferedReader br;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Cases = Integer.parseInt(br.readLine());
        String line[];
        for(int c=0; c<Cases; ++c){

            line = br.readLine().split(" ");
            Arrays.fill(vote, 0); //clear vote
            Arrays.fill(power,0); //clear
            threshold = 0;
            int N = Integer.parseInt(line[0]);
            for(int i=0; i<N; ++i){
                vote[i] = Integer.parseInt(line[i+1]);
                threshold += vote[i];
            }
            threshold = threshold/2;

            for (int j = 0; j < (1 << N); j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    if ((j & (1 << k)) != 0) { //use kth element in this mask j
                        sum += vote[k];
                    }
                }
                if(sum > threshold) continue;

                else{
                    for (int k = 0; k < N; k++){
                        if((j & (1 << k)) == 0 && sum + vote[k] > threshold)
                            power[k]++;
                    }
                }
            }

            for(int m=0; m<N; ++m){
                System.out.printf("party %d has power index %d\n", m+1, power[m]);
            }

            System.out.println();

        }

    }

}
