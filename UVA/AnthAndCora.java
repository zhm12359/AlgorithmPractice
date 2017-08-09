import java.io.*;
import java.util.*;
public class AnthAndCora {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static double[] probs;
    static HashMap<String, Double> dp = new HashMap<>();

    public static void main(String[] args) throws Exception {

        PrintWriter pw = new PrintWriter(System.out);

        int[] tokens = lineToIntArray(br.readLine());
        int anth = tokens[0];
        int carol = tokens[1];

        probs = new double[anth+carol-1];

        for(int i=0; i<anth+carol-1; ++i){
            probs[i] = Double.parseDouble(br.readLine());
        }

        System.out.printf("%.6f\n", solve(anth, carol, 0 ));


    }

    public static double solve(int A, int C, int turn) throws Exception{

        if(C==0) return 1;
        if(A==0) return 0;

        double prob = probs[turn];
        String k = A+ "-"+ "C" + "-" + turn;

        if(dp.containsKey(k)) return dp.get(k);


        int newTurn = turn + 1;

        double res = prob*solve(A, C-1, newTurn) + (1-prob)*solve(A-1, C, newTurn);

        dp.put(k, res);



        return res;


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