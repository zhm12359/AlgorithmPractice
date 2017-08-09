import java.io.*;
import java.util.*;
public class Arbitrage {

    static BufferedReader br;
    static double[][] edgeCost;
    static double[][][] pathCost;
    static int[][][] kIntermediate;
    static StringBuilder out;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line;
        while((line=br.readLine())!=null){
            int n = Integer.parseInt(line.replace(" ",""));
            edgeCost = new double[n][n];
            pathCost = new double[n][n][n+1]; //save number of traders in the third thing...
            kIntermediate = new int[n][n][n+1];
            out = new StringBuilder();
            for(int i=0; i<n; ++i){
                double[] row = lineToDoubleArray(br.readLine());
                int ptr=0;
                for(int j=0; j<n; ++j){
                    edgeCost[i][j] = pathCost[i][j][1] = 1.0;
                    if(i!=j) {
                        edgeCost[i][j] = pathCost[i][j][1] = row[ptr];
                        ptr++;
                    }

                    kIntermediate[i][j][1] = i;
                }
            } // end of graph populating

            //for(int i=0; i<n; ++i) System.out.println(Arrays.toString(edgeCost[i]));


            boolean possible=false;
            for(int trade=2; trade <= n; trade++){ //num of trade
                for(int k=0; k<n; k++){
                    for(int i=0; i<n; i++){
                        for(int j=0; j<n; j++){

                            double temp = pathCost[i][k][trade-1]*edgeCost[k][j];
                            //System.out.println(temp);

                            if(pathCost[i][j][trade]<temp){
                                pathCost[i][j][trade] = temp;
                                kIntermediate[i][j][trade] = k;
                            }



                        }
                    }
                }



                for(int m=0; m<n; m++){
                    //System.out.println(pathCost[m][m][trade]);
                    if(pathCost[m][m][trade]>1.01){
                        buildString(m,m,trade);
                        System.out.println(out.toString());
                        possible = true;
                        break;
                    }
                }

                if(possible) break;


            }

            if(!possible) System.out.println("no arbitrage sequence exists");


        }
    }

    public static void buildString(int i, int j, int trade){

        if(trade < 1) {
            out.append(i+1);
        }else{
            int k = kIntermediate[i][j][trade];
            buildString(i, k, trade-1);
            out.append(" "+(j+1));
        }


    }


    public static double[] lineToDoubleArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }

}