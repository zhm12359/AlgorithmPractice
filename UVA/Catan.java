import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Catan {

    static BufferedReader br;
    static int[][] edges;
    static int[][] visited;
    static int numOfNodes;
    static int numOfEdges;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line=br.readLine();

        while(true){
            int[] tokens = lineToIntArray(line);
            numOfNodes = tokens[0];
            numOfEdges = tokens[1];

            if(numOfNodes==0&&numOfEdges==0) break;

            edges = new int[numOfNodes][numOfNodes];
            visited = new int[numOfNodes][numOfNodes];

            for(int i=0; i<numOfEdges; ++i){
                line=br.readLine();
                tokens = lineToIntArray(line);
                int x = tokens[0];
                int y = tokens[1];
                edges[x][y]=1;
                edges[y][x]=1;
                //System.out.println(x+" "+y);
            }

            int longestRoad=0;

            for(int i=0; i<numOfNodes; ++i){
                int longestFromCurrent = dfsFrom(i);
                longestRoad = longestRoad > longestFromCurrent? longestRoad: longestFromCurrent;
            }

            line=br.readLine();

            System.out.println(longestRoad);
        }



    }

    public static int dfsFrom(int n){
        int longestFromN = 0;

        for(int i=0; i<numOfNodes; ++i){

            if(edges[n][i]!=0 && visited[n][i]==0){
                visited[n][i]=1;
                visited[i][n]=1;
                longestFromN = Math.max(longestFromN, dfsFrom(i)+1);
                visited[n][i]=0;
                visited[i][n]=0;


            }


        }
        return longestFromN;


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