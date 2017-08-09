import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class WaterGate {

    static BufferedReader br;
    static int numGates;
    static int[] flowRate;
    static int[] costs;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numGates = Integer.parseInt(br.readLine());
        flowRate = new int[numGates];
        costs = new int[numGates];

        for(int i=0; i<numGates; ++i){
            int[] tokens = lineToIntArray(br.readLine());
            flowRate[i] = tokens[0];
            costs[i] = tokens[1];
        }


        int T = Integer.parseInt(br.readLine());

        for(int c=1; c<T+1; ++c){

            int[] tokens = lineToIntArray(br.readLine());
            int flows = tokens[0];
            int hours = tokens[1];

            int minCost = Integer.MAX_VALUE;
            for (int j = 0; j < (1 << numGates); j++) {
                int totalCost = 0;
                int totalFlow = 0;
                for (int k = 0; k < numGates; k++) {
                    if ((j & (1 << k)) != 0) { //use kth element in this mask j
                        totalCost += costs[k];
                        totalFlow += flowRate[k];
                    }
                }

                if(totalFlow*hours>=flows){
                    minCost = minCost < totalCost ? minCost : totalCost;
                }

            }

            if(minCost < Integer.MAX_VALUE){
                System.out.printf("Case %d: %d\n", c, minCost);
            }else{
                System.out.printf("Case %d: IMPOSSIBLE\n", c);
            }


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